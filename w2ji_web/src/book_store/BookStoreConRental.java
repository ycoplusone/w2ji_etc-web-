package book_store;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/rental")
public class BookStoreConRental extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("-----------------------------");

        BookStoreDAO dao = new BookStoreDAO();
        boolean a = dao.BookRental( request.getParameter("bookid") );
        System.out.println("BookStoreConBookAdd : "+a);
        response.sendRedirect("./bookstore/rental.jsp?chk="+a);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}