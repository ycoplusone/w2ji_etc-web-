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


@WebServlet("/userlotteradd")
public class userlotteradd extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("--------------userlotteradd---------------");        
        String id = request.getParameter("id");
        String nick = request.getParameter("nick");
        String num1 = request.getParameter("num1");
        String num2 = request.getParameter("num2");
        String num3 = request.getParameter("num3");
        String num4 = request.getParameter("num4");
        String num5 = request.getParameter("num5");
        String num6 = request.getParameter("num6");
        System.out.println("이쪽인가요? id : "+id+" nick : "+nick+" num1 : "+num1+" num2 : "+num2+" num3 : "+num3+" num4 : "+num4+" num5 : "+num5+" num6 : "+num6);
        
        DAO dao = new DAO();
        
        
        
        JsonObject jsonobject = new JsonObject();
        /*jsonobject.addProperty("id"		, dao_json[0] );
        jsonobject.addProperty("title"	, dao_json[1] );
        jsonobject.addProperty("d_day"	, dao_json[2] );
        jsonobject.addProperty("txt"	, dao_json[3] );*/

        List<String[]> dao_json = dao.setUserLottery(id, nick, num1, num2, num3, num4, num5, num6);
        JsonArray jsonarray = new JsonArray();        
        //List<String[]>  tt = dao.getList("%");
        for (String[] strings : dao_json) {
        	System.out.println(strings);
        	JsonObject tempjson = new JsonObject();
        	tempjson.addProperty("id", strings[0]);
        	tempjson.addProperty("info_id", strings[1]);
        	tempjson.addProperty("nick_nm", strings[2]);
        	tempjson.addProperty("mynum"	  , strings[3]);
        	tempjson.addProperty("goal"	  , strings[4]);
        	tempjson.addProperty("point"	  , strings[5]);
        	
        	jsonarray.add(tempjson);
		}
        jsonobject.add("list", jsonarray);
        
        //String json = new Gson().toJson("{ttt:bbb , bbb:4444}");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(jsonobject);
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}