package lottery;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/userlogin")
public class userlogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("--------------userlogin---------------");

        String id = "admin";
        String ps = "qwer1234";
        //DAO dao = new DAO();
        String _id = request.getParameter("id");
        String _ps = request.getParameter("ps");
        
        boolean a = false;
        if( id.equals(_id) && ps.equals(_ps) ) {
        	a = true;
        }
        
        response.sendRedirect("./lottery/login.jsp?chk="+a);       
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}