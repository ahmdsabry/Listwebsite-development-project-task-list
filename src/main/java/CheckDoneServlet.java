/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
 

/**
 *
 * @author PC
 */
@WebServlet(urlPatterns = {"/DeleteTaskServlet"})
public class CheckDoneServlet extends HttpServlet {

   private String dbPath = "jdbc:mysql://localhost/todolist";
    private  String dbunamee = "root";
    private String dbpass = "root123";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DeleteTaskServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteTaskServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                String noteId = request.getParameter("note_id");

 try {
           Class.forName("com.mysql.jdbc.Driver");
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
       }


        // Update the status in the database
        try (     java.sql.Connection con = DriverManager.getConnection(dbPath, dbunamee, dbpass);
             PreparedStatement pstmt = con.prepareStatement("update   notes set status=? WHERE id = ?")) {
                                  pstmt.setString(1, "Done");
                      pstmt.setString(2, noteId);
            
            // Execute the delete operation
            int rowsDeleted = pstmt.executeUpdate();
            
            if (rowsDeleted > 0) {
                // If the deletion was successful
                response.sendRedirect("addtask1.jsp"); // Redirect back to the task list page
               
            } else {

                // If no rows were updated
                response.getWriter().write("No task found with note ID: " + noteId);

            }
        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
            response.getWriter().write("Error occurred while updating status for note ID: " + noteId);
        }
        } 
    

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
