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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "signin", urlPatterns = {"/signin"})
public class signin extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String un=request.getParameter("username");
            String pw=request.getParameter("password");
            ServletContext sc=request.getServletContext();
            RequestDispatcher rd;
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Mainservlet</title>"); 
            out.println("</head>");
            out.println("<body>");
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con;
            con=DriverManager.getConnection("jdbc:derby://localhost:1527/Online Reservation","app","app");
            Statement st = con.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM SIGNUP");
            while(rs.next()){
                String u=rs.getString("USERNAME");
                String p=rs.getString("PASSWORD");
                
                if(u.equals(un)&&p.equals(pw)){
                    rd=sc.getRequestDispatcher("/Ticket.html");
                    rd.forward(request, response);
                }
            }
                    out.println("<h1 style='color:red;font-size:50px;text-align:center'>invalid username or password</h1>");
                    rd=sc.getRequestDispatcher("/signin.html");
                    rd.include(request, response);
            
               
            
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(signin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(signin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
