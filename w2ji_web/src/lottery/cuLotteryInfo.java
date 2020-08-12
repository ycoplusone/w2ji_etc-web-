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


@WebServlet("/cu_lotteryinfo")
public class cuLotteryInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("--------------cu_lotteryinfo---------------");        
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String d_day = request.getParameter("d_day").replaceAll("[^0-9]","");;
        
        String use_yn = "y";//request.getParameter("use_yn");
        String num1 = request.getParameter("num1");
        String num2 = request.getParameter("num2");
        String num3 = request.getParameter("num3");
        String num4 = request.getParameter("num4");
        String num5 = request.getParameter("num5");
        String num6 = request.getParameter("num6");
        LotteryInfo li = new LotteryInfo();
        li.setId(id);
        li.setTitle(title);
        li.setD_day(d_day);
        li.setUse_yn(use_yn);
        li.setNum1(num1);
        li.setNum2(num2);
        li.setNum3(num3);
        li.setNum4(num4);
        li.setNum5(num5);
        li.setNum6(num6);
        //System.out.println("이쪽인가요? id : "+id+" title : "+title+" d_day : "+d_day+" num1 : "+num1+" num2 : "+num2+" num3 : "+num3+" num4 : "+num4+" num5 : "+num5+" num6 : "+num6);
        
        DAO dao = new DAO();
        boolean a = dao.setLotteryInfo(li);
        
        System.out.println("cu_lotteryinfo : "+a);
        response.sendRedirect("./lottery/addlottery.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}