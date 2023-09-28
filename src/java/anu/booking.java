/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anu;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marel
 */
public class booking extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<style>");
            out.println("  body {");
            out.println("    background-image: url('tic.jpg');");
            out.println("    background-size: cover;"); // Adjust as needed
            out.println("  }");
            out.println(".card {");
            out.println("  max-width: 500px;");
            out.println("  border-radius: 20px;");
            out.println("  text-align: center;");
            out.println("  margin-top: 50px;");
            out.println("  font-family: 'Sofia', sans-serif;");
            out.println("  margin-right: auto;");
            out.println("  margin-left: auto;");
            out.println("  box-shadow: 0 15px 25px rgba(129, 124, 124, 0.2);");
            out.println("  backdrop-filter: blur(14px);");
            out.println("  background-color: rgba(255, 255, 255, 0.2);");
            out.println("  padding: 10px;");
            out.println("}");
            out.println("</style>");
            out.println("<title>Servlet Reg</title>");            
            out.println("</head>");
            out.println("<body>");
            String no=request.getParameter("no");
            String name=request.getParameter("name");
            String date=request.getParameter("DOJ");
            String from=request.getParameter("from");
            String to=request.getParameter("to");
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Online Reservation","app","app");
            PreparedStatement ps=con.prepareStatement("insert into Ticket values(?,?,?,?,?)");
            ps.setString(1,no);
            ps.setString(2,name);
            ps.setString(3,date);
            ps.setString(4,from);
            ps.setString(5,to);
            int i=ps.executeUpdate();
            if (i > 0) {
                out.print("<h2 style='text-align:center;'>Ticket Booked Successfully</h2>");
                PreparedStatement retrieveStatement = con.prepareStatement("SELECT * FROM Ticket WHERE TrainNo = ?");
                retrieveStatement.setString(1, no); 
                ResultSet resultSet = retrieveStatement.executeQuery();
                if (resultSet.next()) {
                    out.println("<div class='card'>");
                    out.print("<h3>Details of the Booked Ticket:</h3>");
                    out.print("<b>Train Number:</b> " + resultSet.getString("TrainNo") + "<br/><br/>");
                    out.print("<b>Train Name:</b>" + resultSet.getString("TrainName") + "<br/><br/>");
                    out.print("<b>Date of Journey:</b>" + resultSet.getString("Date") + "<br/><br/>");
                    out.print("<b>Source:</b>" + resultSet.getString("From") + "<br/><br/>");
                    out.print("<b>Destination:</b>" + resultSet.getString("To") + "<br/><br/>");
                    out.print("<a href='http://localhost:8080/Ticket_Reservation/index.html'>Logout..</a>");
                    out.println("</div>");
                }
                retrieveStatement.close();
                
            }    
            else{
                out.print("not insert");
            }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(booking.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(booking.class.getName()).log(Level.SEVERE, null, ex);
        }
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