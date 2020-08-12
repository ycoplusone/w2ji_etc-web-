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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


@WebServlet("/pastlottery")
public class pastlottery extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("--------------pastlottery---------------");        
        DAO dao = new DAO();
        JsonObject jsonobject = new JsonObject();
        /*jsonobject.addProperty("id"		, dao_json[0] );
        jsonobject.addProperty("title"	, dao_json[1] );
        jsonobject.addProperty("d_day"	, dao_json[2] );
        jsonobject.addProperty("txt"	, dao_json[3] );*/
        
        String notice = dao.getNotice();

        List<String[]> dao_json = dao.getPastLottery();
        JsonArray jsonarray = new JsonArray();        
        
        for (String[] strings : dao_json) {
        	System.out.println(strings);
        	JsonObject tempjson = new JsonObject();
        	tempjson.addProperty("id"		, strings[0]);
        	tempjson.addProperty("txt"		, strings[1]);
        	tempjson.addProperty("use_yn"		, strings[2]);
        	jsonarray.add(tempjson);
		}
        jsonobject.add("list", jsonarray);
        jsonobject.addProperty("notice", notice);
        
        
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