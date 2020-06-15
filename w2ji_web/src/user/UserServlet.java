package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("-----------------------------");

        UserVO vo = new UserVO();
        vo.setName(request.getParameter("name"));
        vo.setEmail(request.getParameter("email"));
        vo.setPwd(request.getParameter("pwd"));

        UserDAO dao = new UserDAO();
        dao.insert(vo);

        response.sendRedirect("/w2ji_web/form.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}