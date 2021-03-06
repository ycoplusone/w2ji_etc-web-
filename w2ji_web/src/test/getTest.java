package test;

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

//도서 추가
@WebServlet("/gettest")
public class getTest extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("--------------getTest doGet---------------");        
        String str = request.getParameter("test");
        System.out.println("str : "+str);
        
        
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("company", "tistory");
        jsonobject.addProperty("address", "seoul 1d5d15d15d");
        jsonobject.addProperty("number", "0212345678");
        jsonobject.addProperty("test", str);

        JsonArray jsonarray = new JsonArray();
        DAO dao = new DAO();
        List<String[]>  tt = dao.getList("%");
        for (String[] strings : tt) {
        	JsonObject tempjson = new JsonObject();
        	tempjson.addProperty("id", strings[0]);
        	tempjson.addProperty("title", strings[1]);
        	tempjson.addProperty("releasedate", strings[2]);
        	tempjson.addProperty("videoreleasedate", strings[3]);
        	tempjson.addProperty("imdburl", strings[4]);
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