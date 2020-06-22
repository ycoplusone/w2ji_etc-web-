package book_store;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//도서 추가
@WebServlet("/bookadd")
public class BookStoreConBookAdd extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("--------------BookStoreConBookAddBookStoreConBookAdd---------------");

        
        BookStoreDAO dao = new BookStoreDAO();
        BookStore bs = new BookStore();
        
        bs.setNm( request.getParameter("nm") );
        bs.setWriter( request.getParameter("writer") );
        bs.setPrice( Integer.parseInt( request.getParameter("price") ) );
        
        
        boolean a = dao.BookAdd( bs );
        System.out.println("BookStoreConBookAdd : "+a);
        response.sendRedirect("./bookstore/bookadd.jsp?chk="+a);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}