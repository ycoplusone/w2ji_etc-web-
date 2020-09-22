package music;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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


@WebServlet("/musicqlist")
public class getList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        DAO dao = new DAO();
        List<Musicgame> list = new ArrayList<Musicgame>();
        list = dao.getMusicList("%", "y");
        
        JsonObject jsonobject = new JsonObject();
        /*jsonobject.addProperty("id"		, dao_json[0] );
        jsonobject.addProperty("title"	, dao_json[1] );
        jsonobject.addProperty("d_day"	, dao_json[2] );
        jsonobject.addProperty("txt"	, dao_json[3] );*/
        
        //String notice = dao.getNotice();

        //List<String[]> dao_json = dao.getPastLottery();
        JsonArray jsonarray = new JsonArray();        
        
        for ( Musicgame mg : list) {        	
        	
        	JsonObject tempjson = new JsonObject();
        	JsonArray ta = new JsonArray();
        	ta.add(mg.getAns1());
        	ta.add(mg.getAns2());
        	ta.add(mg.getAns3());
        	ta.add(mg.getAns4());
        	tempjson.addProperty("ques"		, mg.getQues());
        	tempjson.add("ans"		, ta );
        	tempjson.addProperty("name"		, mg.getName()+"_"+mg.getId());
        	tempjson.addProperty("correct"		, mg.getCorrect());
        	tempjson.addProperty("divClass"		, "."+mg.getName()+"_"+mg.getId());
        	tempjson.addProperty("music"		, mg.getMusicFile());
        	tempjson.addProperty("image"		, mg.getImgFile());
        	jsonarray.add(tempjson);        	
		}
        jsonobject.add("list", jsonarray);
        //jsonobject.addProperty("notice", notice);
        
        
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(jsonarray);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}