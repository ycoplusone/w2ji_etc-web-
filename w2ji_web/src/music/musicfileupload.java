package music;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/musicfileupload")
public class musicfileupload extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("---------------------------fileupload------------------------------");
        DAO dao = new DAO();
        
        response.setContentType("text/html; charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        String savePath = "musicgame/upload";
        int uploadFileSizeLimit = 1024 * 1024 * 1024;
        String encType = "UTF-8";
        
        String uploadFilePath = request.getSession().getServletContext().getRealPath(savePath);
        System.out.println("upload path : "+uploadFilePath);
        try {
	         MultipartRequest multi = new MultipartRequest(request,uploadFilePath,uploadFileSizeLimit,encType,new FileRenamePolicy());
	         
	         String _id 	= multi.getParameter("id");
	         String _ques 	= multi.getParameter("ques");                
	         String _ans1	= multi.getParameter("ans1");
	         String _ans2 	= multi.getParameter("ans2");
	         String _ans3	= multi.getParameter("ans3");
	         String _ans4	= multi.getParameter("ans4");
	         String _correct 	= multi.getParameter("correct");
	         String _name 		= multi.getParameter("name");
	         String _use_yn 	= multi.getParameter("use_yn");
	         
	                  
	         String [] org_file_name = new String[2];
	         String [] chage_file_name = new String[2];  
	         
	         String music = "";
	         String img = "";
	         
	         
	         Enumeration files = multi.getFileNames();
	         int cnt = 0;	         
	         while (files.hasMoreElements()) {
	        	  String file = (String)files.nextElement();
		          String file_name = multi.getFilesystemName(file);
		          if( file_name != null ) {
			          String ext = file_name.substring(file_name.lastIndexOf(".") + 1).toLowerCase();
			          String ori_file_name = multi.getOriginalFileName(file);
			          org_file_name[cnt] = ori_file_name;
			          chage_file_name[cnt] = file_name;
			          if(ext.equals("gif") || ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png") || ext.equals("bmp") || ext.equals("raw") ) {
			        	  img = chage_file_name[cnt];
			          }else {
			        	  music = chage_file_name[cnt];
			          }
			          System.out.println("변경      업로드된 파일명 : " + chage_file_name[cnt] );
			          System.out.println("오리지널 업로드된 파일명 : " + org_file_name[cnt] );
			          System.out.println("---------------------------------------" );
			          cnt++;
		          }
	        	  
	        }
	        JsonObject jsonobject = new JsonObject();
	        
	        Musicgame mg = new Musicgame();
	        mg.setId(_id);
	        mg.setQues(_ques);
	        mg.setAns1(_ans1);
	        mg.setAns2(_ans2);
	        mg.setAns3(_ans3);
	        mg.setAns4(_ans4);
	        mg.setCorrect(_correct);
	        mg.setName(_name);
	        mg.setMusicFile( music );
	        mg.setImgFile( img );
	        mg.setUseYn(_use_yn);
	        
	        
	         boolean a = dao.setMusicgame( mg ); 
	        //jsonobject.addProperty("boolean", a);
	        
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        String json = gson.toJson(jsonobject);
	        
	        //response.setContentType("application/json");
	        //response.setCharacterEncoding("UTF-8");
	        response.sendRedirect("./musicgame/addmusic.jsp");	        
         
        } catch (Exception e) {
         System.out.print("예외 발생 : " + e);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}