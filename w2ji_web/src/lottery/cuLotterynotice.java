package lottery;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


@WebServlet("/cu_lotterynotice")
public class cuLotterynotice extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("--------------cu_lotterynotice---------------");        
        String seq = request.getParameter("seq");
        String txt = request.getParameter("txt");
        String use_yn = request.getParameter("use_yn");
        LotteryNotice li = new LotteryNotice();
        li.setSeq(seq);
        li.setTxt(txt);
        li.setUse_yn(use_yn);
        //System.out.println("이쪽인가요? id : "+id+" title : "+title+" d_day : "+d_day+" num1 : "+num1+" num2 : "+num2+" num3 : "+num3+" num4 : "+num4+" num5 : "+num5+" num6 : "+num6);
        
        DAO dao = new DAO();
        boolean a = dao.setLotteryNotice(li);
        
        System.out.println("cu_lotterynotice : "+a);
        response.sendRedirect("./lottery/notice.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}