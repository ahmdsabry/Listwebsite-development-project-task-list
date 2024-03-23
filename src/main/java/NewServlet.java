/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import com.google.protobuf.TextFormat.ParseException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.example.Summerization;
 

/**
 *
 * @author bm
 */
public class NewServlet extends HttpServlet {

   private String dbPath = "jdbc:mysql://localhost/todolist";
    private  String dbunamee = "root";
    private String dbpass = "root123";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *

     * @throws IOException if an I/O error occurs
     */        UserDao userDao = new UserDao();

    NoteDao noteDao=new NoteDao();
     int i=0;


              int userid;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *

     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String log = request.getParameter("log");
        if(log.equals("Login"))  {
            String username = request.getParameter("uname");
            String password = request.getParameter("pass");
        
             userid= userDao.Login(username, password);
             if(userid!=0) {
                 String fullname=userDao.getfullname();
                request.setAttribute("add",userid);
                request.setAttribute("fullname",fullname);
                request.getRequestDispatcher("/addtask1.jsp").forward(request, response);

             }
             else {
                 request.setAttribute("error", "your login invaild");
                 request.getRequestDispatcher("/Login.jsp").forward(request, response);


             }
        }
        else if(log.equals("Add Task")) {
          userid=0;
                          request.setAttribute("fullname","Guest");

          request.setAttribute("add", userid);
          request.getRequestDispatcher("/addtask1.jsp").forward(request, response);

            
            
        }
        
         else if(log.equals("View Summerize")) {
         
         String fullname=userDao.getfullname();
                   request.setAttribute("fullname", fullname);

                   request.setAttribute("add", userid);
          request.getRequestDispatcher("/ViewSummerize.jsp").forward(request, response);


            
            
        }
         
          else if(log.equals("Home")) {
         
         String fullname=userDao.getfullname();
                   request.setAttribute("fullname", fullname);

                   request.setAttribute("add", userid);
          request.getRequestDispatcher("/addtask1.jsp").forward(request, response);


            
            
        }
        
           else if (log.equals("view splited"))  {
               
               noteDao.viewsplit(userid);
                 request.setAttribute("notes", noteDao.getNotes());
        request.setAttribute("add", userid);

        request.getRequestDispatcher("/addtask1.jsp").forward(request, response);
         }
      
             else if (log.equals("Sortbydate")) {
    if (userid<=0) {
        userid = 1000;
        noteDao.fetchAndForwardTaskssortedbydate(userid);
        // Set the "notes" attribute and forward the request to the JSP page
        request.setAttribute("notes", noteDao.getNotes());
        request.setAttribute("add", userid);

        request.getRequestDispatcher("/addtask1.jsp").forward(request, response);

            }
            else {
                noteDao.fetchAndForwardTaskssortedbydate(userid);
        // Set the "notes" attribute and forward the request to the JSP page
        request.setAttribute("notes", noteDao.getNotes());
        request.setAttribute("add", userid);

        request.getRequestDispatcher("/addtask1.jsp").forward(request, response);}
               
               
               
               
                       }
     else if (log.equals("SaveChanges")) {
    // Assuming you have retrieved userid from somewhere

    String date = request.getParameter("date");
            LocalDate dates = LocalDate.parse(date);

    String taskname = request.getParameter("task");
    String priority = request.getParameter("priority");
    String status = "pending";
//    Note note = new Note(++i, taskname, status, priority);


 if (userid <= 0) {
     userid = 0;
     int rows = noteDao.saveChanges(dates,taskname,status,priority);
     if (rows > 0) {
         // Set the "notes" attribute and forward the request to the JSP page
         noteDao.fetchAndForwardTaskswithoutlogin();
         request.setAttribute("notess", noteDao.getNotes());
         request.setAttribute("add", userid);

         request.getRequestDispatcher("/addtask1.jsp").forward(request, response);
     }
}

    // Forward the request along with the Note object to the JSP page
     
    else {
            request.setAttribute("user_id", userid);

             int rows=noteDao.insertTaskwithlogin(taskname, status, priority, userid, dates);
                   if (rows>0) {
                       noteDao.fetchAndForwardTasks(userid);
                       request.setAttribute("notes", noteDao.getNotes());
                       request.setAttribute("add", userid);
                       request.getRequestDispatcher("/addtask1.jsp").forward(request, response);
                   }
             }
        }
     
     
     
     
     
     
     
      else if (log.equals("Group Similler")) {
//          String hid=request.getParameter("intent");
//          
//          if(hid.equals("hid")) {
//              hid="hidone";
//              
//                          request.setAttribute("intent", hid);
//
//          }
//         
          
 
      
      
      
     try (Connection connection = DriverManager.getConnection(dbPath, dbunamee, dbpass)) {
            // Fetch notes from the database
            List<Note> notes = noteDao.fetchAndForwardTasksggg(userid);

            // Group similar notes by content
           Map<String, List<Note>>  groupedNotes = groupNotesBySimilarContent(notes);
            request.setAttribute("add", userid);
request.setAttribute("log", "Group Similler"); // Corrected typo

            // Pass grouped notes data to the JSP page
            request.setAttribute("notes", groupedNotes);
 
            // Forward the request to your JSP page for rendering
                       request.getRequestDispatcher("/addtask1.jsp").forward(request, response);
         } catch (SQLException | ServletException | IOException e) {
            e.printStackTrace();
            // Handle exceptions
        }
}
      
      
     
     
     
     
     
     
     
     
     
    else if (log.equals("ExportToTxt")) {
    response.setContentType("text/plain");
    response.setHeader("Content-Disposition", "attachment; filename=note.txt");

    try (PrintWriter out = response.getWriter()) {
        // Write the table headers
out.printf("%-8s%-30s%-15s%-15s%-15s%n", "Note ID", "Note", "Status", "Priority", "Date");

// Iterate over the notes list and write each note to the text file
for (Note note : noteDao.getNotes()) {
    out.printf("%-8d%-30s%-15s%-15s%-15s%n", note.getNoteid(), note.getNote(), note.getStatus(), note.getPriority(), note.getDate());
}
    } catch (IOException e) {
        e.printStackTrace();
    }
}

     
        else if (log.equals("ViewTasks")) {
            
            if (userid<=0) {
                
                request.setAttribute("log", "view"); // Corrected typo

                noteDao.fetchAndForwardTaskswithoutlogin();
                request.setAttribute("notess", noteDao.getNotes());
                request.setAttribute("add", userid);
                
               
                request.getRequestDispatcher("/addtask1.jsp").forward(request, response);

            }
            else {
                
                noteDao.fetchAndForwardTasks(userid);
                 UserDao dao=new UserDao();
                String full=    dao.getfullname();
                request.setAttribute("notes", noteDao.getNotes());
                request.setAttribute("add", userid);
                request.setAttribute("fullname", full);
                request.getRequestDispatcher("/addtask1.jsp").forward(request, response);
            }
         }
        else if (log.equals("Register")) {
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String phone = request.getParameter("phone");
        String username = request.getParameter("uname");
        String password = request.getParameter("pass");

        // Assuming you have a UserDao to handle user registration
       int rows= userDao.insertuser(firstName, lastName, username,password, phone );
       request.setAttribute("row", rows);
        request.getRequestDispatcher("/Login.jsp").forward(request, response);   
        } else if (log.equals("Search"))  {
             String seachinput=request.getParameter("task");
             noteDao.search(seachinput,userid);
            // Set the "notes" attribute and forward the request to the JSP page
            request.setAttribute("notes", noteDao.getNotes());
            request.setAttribute("add", userid);
            request.getRequestDispatcher("/Search.jsp").forward(request, response);
        } else if (log.equals("Save Changes")) {
            String noteId = request.getParameter("note_id");
            String date = request.getParameter("date");
            String note = request.getParameter("note");
            String priority = request.getParameter("priority");
            int rowsUpdated = noteDao.saveUpdates(date, note, noteId, priority);
            if (rowsUpdated > 0) {
                
                request.setAttribute("rowsUpdated ", rowsUpdated);
                request.getRequestDispatcher("/addtask1.jsp").forward(request, response);
            }
        } else if (log.equals("Summerize")) {
            int quota = Integer.parseInt(request.getParameter("summerize"));
            noteDao.summerizeAndForwardTasks(userid, quota);
            request.setAttribute("notes", noteDao.getNotes());
            request.setAttribute("add", userid);
            
//          String s=  userDao.getfullname();
//            request.setAttribute("fullname", s);

            request.getRequestDispatcher("/ViewSummerize.jsp").forward(request, response);

        } 
                
   }
    
    
   private static List<Note> fetchNotes(Connection connection, int userId) throws SQLException {
        List<Note> notes = new ArrayList<>();
        // Execute SQL query to fetch notes based on user_id
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM notes WHERE user_id = ?")) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int noteid = resultSet.getInt("id");
                    String note = resultSet.getString("note");
                    String status = resultSet.getString("status");
                    String priority = resultSet.getString("priority");
                    Date date = resultSet.getDate("date");
                    
                    notes.add(new Note(noteid, note, status, priority, date));
                }
            }
        }
        return notes;
    }

  private static Map<String, List<Note>> groupNotesBySimilarContent(List<Note> notes) {
    Map<String, List<Note>> groupedNotes = new HashMap<>();

    for (Note note : notes) {
        boolean added = false;

        // Iterate through existing groups
        for (Map.Entry<String, List<Note>> entry : groupedNotes.entrySet()) {
            String groupText = entry.getKey();

            // Check similarity with existing group text
            if (areSimilar(note.getNote(), groupText)) {
                // Add note to existing group
                entry.getValue().add(note);
                added = true;
                break;
            }
        }

        // If note does not fit into any existing group, create a new group
        if (!added) {
            groupedNotes.put(note.getNote(), new ArrayList<>(Collections.singletonList(note)));
        }
    }

    return groupedNotes;
}

private static boolean areSimilar(String text1, String text2) {
    // Define a threshold for Levenshtein distance
    int threshold = 3; // Adjust as needed based on your requirements

    // Calculate Levenshtein distance between the texts
    int distance = calculateLevenshteinDistance(text1, text2);

    // Check if the distance is below the threshold
    return distance <= threshold;
}

private static int calculateLevenshteinDistance(String s1, String s2) {
    int[][] dp = new int[s1.length() + 1][s2.length() + 1];

    for (int i = 0; i <= s1.length(); i++) {
        dp[i][0] = i;
    }

    for (int j = 0; j <= s2.length(); j++) {
        dp[0][j] = j;
    }

    for (int i = 1; i <= s1.length(); i++) {
        for (int j = 1; j <= s2.length(); j++) {
            int substitutionCost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
            dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + substitutionCost);
        }
    }

    return dp[s1.length()][s2.length()];
}

     
     
     
     
     
  


 

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String button = request.getParameter("save");

        }
    }


 
