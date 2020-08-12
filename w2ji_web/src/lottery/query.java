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


@WebServlet("/sql")
public class query extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("-------------- query ---------------");        
        String div 		= request.getParameter("div");	//쿼리 종류 번호
        String p1 		= request.getParameter("p1");
        String p2 		= request.getParameter("p2");
        String p3 		= request.getParameter("p3");
        String p4 		= request.getParameter("p4");
        String p5 		= request.getParameter("p5");
        String p6 		= request.getParameter("p6");
        String p7 		= request.getParameter("p7");
        String p8 		= request.getParameter("p8");
        String p9 		= request.getParameter("p9");
        String p10 		= request.getParameter("p10");
        
        DAO dao = new DAO();
        
        JsonObject jsonobject = new JsonObject();
        List<String[]> dao_json = null;

        JsonArray jsonarray = new JsonArray();
        if( div.equals("id_check") ){	//아이디 체크
        	System.out.println("id_check");
        	dao_json = dao.id_check(p1);
        	for (String[] strings : dao_json) {
        		JsonObject tempjson = new JsonObject();
        		System.out.println(" strings.length "+strings.length);
        		for( int i = 0 ; strings.length > i ; i++ ){
        			tempjson.addProperty("c"+i, strings[i]);
        		}       	
            	jsonarray.add(tempjson);
    		}
            jsonobject.add("list", jsonarray);     
            
        }else if( div.equals("id_create") ){	//아이디 생성
        	System.out.println("id_create");
        	boolean a = dao.id_create(p1);
        	jsonobject.addProperty("boolean", a);
        	
        }else if( div.equals("select_lottery_gift") ){
        	System.out.println("select_lottery_gift : "+p1+" : "+p2);
        	dao_json = dao.select_lottery_gift(p1 , p2);
        	for (String[] strings : dao_json) {
        		JsonObject tempjson = new JsonObject();        		
        		for( int i = 0 ; strings.length > i ; i++ ){
        			tempjson.addProperty("c"+i, strings[i]);
        		}       	
            	jsonarray.add(tempjson);
    		}
            jsonobject.add("list", jsonarray); 
        	
        }
        
    
        
        
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