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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author G SAI TEJA
 */
public class UserDetails extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String tn=request.getParameter("tno");
            ServletContext sc=request.getServletContext();
            RequestDispatcher rd;
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserDetails</title>"); 
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
            out.println("</head>");
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con;
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/Online Reservation","app","app");
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM TICKET WHERE TRAINNO='"+tn+"'");
            String u1,p1,v,w,s,n,g;
            int flag=0;
            if(rs.next()){
                    out.println("<h1 style=\"text-align:center\">User Ticket Details</h1>");
                    n=rs.getString(1);
                    g=rs.getString(2);
                    v=rs.getString(3);
                    w=rs.getString(4);
                    s=rs.getString(5);
                    out.println("<div class='card'>");
                    out.println("<form >");
                    out.println("<b>Train No:</b>" + n + "<br/><br/>");
                    out.println("<b>Train Name:</b> " + g + "<br/><br/>");
                    out.println("<b>Date of Journey:</b>" + v + "<br/><br/>");
                    out.println("<b>From:</b>" + w + "<br/><br/>");
                    out.println("<b>TO:</b>" + s+"<br/><br/>");
                    out.println("<a href='http://localhost:8080/Ticket_Reservation/index.html'>Logout...</a>");
                    out.println("</form>");
                    out.println("</div>");
            }
            else{
                out.println("<h1>Invalid Username and password....</h1>");
                out.println("<h1>you are not registered....Please register</h1>");
                RequestDispatcher rd1=sc.getRequestDispatcher("/index.html");
                rd1.include(request, response);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDetails.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDetails.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDetails.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDetails.class.getName()).log(Level.SEVERE, null, ex);
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
