package book_store;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/return")
public class BookStoreConReturn extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("-----------------------------");

        BookStoreDAO dao = new BookStoreDAO();
        boolean a = dao.BookReturn( request.getParameter("bookid") );
        System.out.println("BookStoreConReturn : "+a);
        response.sendRedirect("./bookstore/return.jsp?chk="+a);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}