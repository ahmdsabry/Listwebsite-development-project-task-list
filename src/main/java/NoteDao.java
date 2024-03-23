
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Summerization;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NoteDao {
    private String dbPath = "jdbc:mysql://localhost/todolist";
    private  String dbunamee = "root";
    private String dbpass = "root123";
    private ArrayList<Note> notes;
    public int fetchAndForwardTaskswithoutlogin(){
        notes = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");

            java.sql.Connection con = DriverManager.getConnection(dbPath, dbunamee, dbpass);
            PreparedStatement st = con.prepareStatement("select * FROM notes where user_id = ? ");
            st.setInt(1, 1000);
            java.sql.ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int noteid = rs.getInt("id");
                String note = rs.getString("note");
                String status = rs.getString("status");
                String priority = rs.getString("priority");
                Date date = rs.getDate("date"); // Assuming "date" is the column name for dates

                // Create a new Note object and add it to the list
                notes.add(new Note(noteid, note, status, priority, date));
            }
            
        } catch (SQLException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1000;

    }
    
    
    
    
    
    public  void viewsplit(int userid) {
            notes = new ArrayList<>();
        int maxLength = 20; // Example: Maximum length of each note part

        try {
            Class.forName("com.mysql.jdbc.Driver");

            java.sql.Connection con = DriverManager.getConnection(dbPath, dbunamee, dbpass);
            PreparedStatement st = con.prepareStatement("SELECT * FROM notes WHERE user_id = ?");

            st.setInt(1, userid);

            java.sql.ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int noteid = rs.getInt("id");
                String fullNote = rs.getString("note");
                String status = rs.getString("status");
                String priority = rs.getString("priority");
                Date date = rs.getDate("date"); // Assuming "date" is the column name for dates

                // Check if the note length exceeds the predetermined maximum length
                if (fullNote.length() > maxLength) {
                    // Split the note into parts
                    List<String> noteParts = splitNote(fullNote, maxLength);

                    // Create a new Note object for each part of the splitted note and add it to the list
                    for (String notePart : noteParts) {
                        notes.add(new Note(noteid, notePart, status, priority, date));
                    }
                } else {
                    // Note length is within the predetermined limit, add it directly
                    notes.add(new Note(noteid, fullNote, status, priority, date));
                }
            }
        } catch (SQLException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Set the "notes" attribute and forward the request to the JSP page
      

            
    }
    
    private List<String> splitNote(String fullNote, int maxLength) {
        List<String> noteParts = new ArrayList<>();

        for (int i = 0; i < fullNote.length(); i += maxLength) {
            noteParts.add(fullNote.substring(i, Math.min(i + maxLength, fullNote.length())));
        }

        return noteParts;
    }
    
    public void fetchAndForwardTasks(int userid){
        notes = new ArrayList<>();
        try  {
            Class.forName("com.mysql.jdbc.Driver");

            java.sql.Connection con = DriverManager.getConnection(dbPath, dbunamee, dbpass);
            PreparedStatement st = con.prepareStatement("select * FROM notes where user_id = ? ");

            st.setInt(1, userid);


            java.sql.ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int noteid=rs.getInt("id");
                String note = rs.getString("note");
                String status = rs.getString("status");
                String priority = rs.getString("priority");
                Date currentDate = new Date();

                java.sql.Date currentSqlDate = new java.sql.Date(currentDate.getTime());

                Date date = rs.getDate("date");

                java.sql.Date dateFromDB = rs.getDate("date");

                if (dateFromDB != null && dateFromDB.before(currentDate)) {
                    notes.add(new Note(noteid, note, "Expired", priority, date));
                   

                }else{
                    notes.add(new Note(noteid, note, status, priority, date));

                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
      public    List<Note> fetchAndForwardTasksggg(int userid){
        List<Note> notes = new ArrayList<>();
        try  {
            Class.forName("com.mysql.jdbc.Driver");

           Connection con = DriverManager.getConnection(dbPath, dbunamee, dbpass);
            PreparedStatement st = con.prepareStatement("select * FROM notes where user_id = ? ");

            st.setInt(1, 4);


            java.sql.ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int noteid=rs.getInt("id");
                String note = rs.getString("note");
                String status = rs.getString("status");
                String priority = rs.getString("priority");
                Date currentDate = new Date();

                java.sql.Date currentSqlDate = new java.sql.Date(currentDate.getTime());

                Date date = rs.getDate("date");

                java.sql.Date dateFromDB = rs.getDate("date");

                if (dateFromDB != null && dateFromDB.before(currentDate)) {
                    notes.add(new Note(noteid, note, "Expired", priority, date));
                   

                }else{
                    notes.add(new Note(noteid, note, status, priority, date));

                }
                

            }
                                    return notes;

        } catch (SQLException | ClassNotFoundException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }
                                            return notes;

      }
    
    
    public int insertTaskwithlogin(String taskname, String status, String priority, int userid, LocalDate dates){
        int rows = 0;
        notes = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con =
                    DriverManager.getConnection(dbPath, dbunamee, dbpass);
            PreparedStatement st = con.prepareStatement("insert into notes(note,status,priority,user_id,date) values(?,?,?,?,?)");

            st.setString(1, taskname);
            st.setString(2, status);
            st.setString(3, priority);
            st.setInt(4, userid);
            st.setDate(5, java.sql.Date.valueOf(dates));

            rows=st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }
    public void search(String seachinput, int userid){
        java.sql.Date searchDate = null;
        if (seachinput.matches("\\d{4}-\\d{2}-\\d{2}")) {
        try {
            // Try to parse the date string
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(seachinput);
            searchDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }}
       
        // Handle parsing exception (e.g., invalid date format)
        try  {
            Class.forName("com.mysql.jdbc.Driver");
            String sqlQuery = "SELECT * FROM notes WHERE (note LIKE ?  OR date LIKE ? OR priority = ?) AND user_id = ?";

            java.sql.Connection con = DriverManager.getConnection(dbPath, dbunamee, dbpass);
            PreparedStatement st = con.prepareStatement(sqlQuery);

            st.setString(1, "%" + seachinput + "%"); // Using LIKE with wildcards to search for partial matches
            st.setDate(2, searchDate); // Assuming 'date_column' is a date column

            st.setString(3, seachinput);
            st.setInt(4, userid);

            java.sql.ResultSet rs = st.executeQuery();
            notes = new ArrayList<>();
            while (rs.next()) {
                int noteid=rs.getInt("id");
                String note = rs.getString("note");
                String status = rs.getString("status");
                String priority = rs.getString("priority");
                Date date = rs.getDate("date"); // Assuming "date" is the column name for dates

                // Create a new Note object and add it to the list
                notes.add(new Note(noteid, note, status, priority, date));            }
        } catch (SQLException | ClassNotFoundException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        }

    }
    public int saveChanges(LocalDate dates, String taskname, String status, String priority){
        int rows = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection(dbPath, dbunamee, dbpass);
            PreparedStatement st = con.prepareStatement("insert into notes(note,status,priority,user_id,date) values(?,?,?,?,?)");

            st.setString(1, taskname);
            st.setString(2, status);
            st.setString(3, priority);
            st.setInt(4, 1000);

            // Convert LocalDate to string in "yyyy-MM-dd" format
            String dateString = dates.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            st.setDate(5, java.sql.Date.valueOf(dateString));

            rows = st.executeUpdate();

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return rows;
    }
    public int saveUpdates(String date, String note, String noteId, String priority) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        LocalDate dates = LocalDate.parse(date);
        int rowsUpdated = 0;
        // Update the status in the database
        try (java.sql.Connection conn = DriverManager.getConnection(dbPath, dbunamee, dbpass);
             PreparedStatement pstmt = conn.prepareStatement("UPDATE notes SET note = ?, priority = ?, date = ? WHERE id = ?")) {

            // Set parameters
            pstmt.setString(1, note);
            pstmt.setString(2, priority);
            pstmt.setDate(3, java.sql.Date.valueOf(dates));
            pstmt.setString(4, noteId);

            // Execute update
            rowsUpdated = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowsUpdated;
    }
    public void summerizeAndForwardTasks(int userid, int quota) {

        try  {
            Class.forName("com.mysql.jdbc.Driver");

            java.sql.Connection con = DriverManager.getConnection(dbPath, dbunamee, dbpass);
            PreparedStatement st = con.prepareStatement("select * FROM notes where user_id = ? ");

            st.setInt(1, userid);


            java.sql.ResultSet rs = st.executeQuery();
            notes = new ArrayList<>();
            while (rs.next()) {
                int noteid=rs.getInt("id");
                String note = rs.getString("note");
                String status = rs.getString("status");
                String priority = rs.getString("priority");
                Date date = rs.getDate("date"); // Assuming "date" is the column name for dates
                StringBuilder newNote = Summerization.summerize(note,quota);
                note = newNote.toString();
                System.out.println(note);
                // Create a new Note object and add it to the list
                notes.add(new Note(noteid, note, status, priority, date));            }
        } catch (SQLException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void fetchAndForwardTaskssortedbydate(int userid) {


        try  {
            Class.forName("com.mysql.jdbc.Driver");

            java.sql.Connection con = DriverManager.getConnection(dbPath, dbunamee, dbpass);
            PreparedStatement st = con.prepareStatement("select * FROM notes where user_id = ? order by date desc ");

            st.setInt(1, userid);


            java.sql.ResultSet rs = st.executeQuery();
            notes = new ArrayList<>();
            while (rs.next()) {
                int noteid=rs.getInt("id");
                String note = rs.getString("note");
                String status = rs.getString("status");
                String priority = rs.getString("priority");
                Date date = rs.getDate("date"); // Assuming "date" is the column name for dates

                // Create a new Note object and add it to the list
                notes.add(new Note(noteid, note, status, priority, date));            }
        } catch (SQLException e) {
            // Handle exceptions appropriately
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        public ArrayList<Note> getNotes() {
        return notes;
    }
}
