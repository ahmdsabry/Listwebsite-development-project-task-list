package org.example;
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Summerization {
     public static StringBuilder summerize(String note, int quota)  {
         String paragraph="";
         paragraph+=note.trim().toLowerCase().replaceAll("\\s+"," ")
                .replaceAll("\\s\\.", ".")
                .replaceAll("\\.\\s", ".")
                .replaceAll("\\s\\.\\s ", ".");



         try {
             if (paragraph.matches("\\d+")){
                 System.out.println("enter string only");
                 throw new ParagraphException(ErrorParapraph.digitonly);
             }
         } catch (ParagraphException e) {
             System.out.println(e.getMessage());
             System.exit(1);
         }
         try {
             if(paragraph.isEmpty() ||paragraph.matches("\\s+")) {
                 throw new ParagraphException(ErrorParapraph.emptyfile);
             }
         } catch (ParagraphException  e) {
             System.out.println(e.getMessage());
             System.exit(1);
         }

         try {
             if (paragraph.split("[.,]").length==1) {
                 throw  new ParagraphException(ErrorParapraph.containonesentence);
             }
         } catch (ParagraphException e) {
             System.out.println(e.getMessage());
             System.exit(1);
         }

         LinkedHashSet<String> sentences = new LinkedHashSet<>(Arrays.asList(paragraph.trim().split("[.|,]"))); // ds 1 -> set
         List<String> stopWords = Arrays.asList("and", "or", "in", "a", "an", "the", "without", "with", "is", "to", "for"); // ds 2 ->list

         List<String> words = sentences.stream() // ds 3 -> list
                .map(s -> s.trim().split("[\\s|-]")).flatMap(Arrays::stream)
                .collect(Collectors.toList());

         System.out.println("////////////////////////key=unique sentence, value=sentence weight//////////////////////////////////////////////");
         Map<String, Integer> sums = sentences.stream() // ds 4 -> map
                .collect(Collectors.toMap(
                        s -> s,
                        s -> {
                            String[] wordsInSentence = s.trim().split("[\\s|-]"); // ds 5 ->arr
                            return  Arrays.stream(wordsInSentence)
                                    .filter(word -> !stopWords.contains(word))
                                    .mapToInt(word -> Collections.frequency(words,word))
                                    .sum();
                        },
                        (a,b)->a, LinkedHashMap::new
                ));

        System.out.println(sums);

        File outputsDir=new File("outputs");
        try {
        } catch (InputMismatchException  e) {
            deleteDirectory(outputsDir);
            System.out.println(ErrorParapraph.invaildquota);
            System.exit(1);
        }

        try {
            if(quota <= 0)
            {
                deleteDirectory(outputsDir);
                throw  new ParagraphException(ErrorParapraph.negativequota);
            }
            if (quota > 100){
                deleteDirectory(outputsDir);
                throw new ParagraphException(ErrorParapraph.invaildquota);
            }
            if (quota==100){
                sentences.forEach(System.out::println);
                deleteDirectory(outputsDir);
                System.exit(0);
            }
        } catch (ParagraphException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }


        int NumberOfSentences = sentences.size(); // var 2

             int count=0; // var 5
             int iterations =  (NumberOfSentences * quota)/100; // var 6
             StringBuilder output = new StringBuilder();

             for (Map.Entry<String, Integer> entry : sums.entrySet()) {
                 if (count == iterations){
                     return output;
                 }
                 ///////////////////////////////////////new file
                output.append(entry.getKey());
                 count++;
             }
         return output;
     }
    private static void isFileFound(File f) throws ParagraphException {
        if (!f.exists())  {
            throw new ParagraphException(ErrorParapraph.filenot);
        }
    }
    private static boolean  deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles(); // ds 6 ->arr
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }
}