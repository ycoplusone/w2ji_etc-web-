package lottery;

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


@WebServlet("/fileupload")
public class fileupload extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("---------------------------fileupload------------------------------");
        DAO dao = new DAO();
        
        response.setContentType("text/html; charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        String savePath = "upload";
        int uploadFileSizeLimit = 1024 * 1024 * 1024;
        String encType = "UTF-8";
        
        String uploadFilePath = request.getSession().getServletContext().getRealPath(savePath);
        System.out.println("upload path : "+uploadFilePath);
        try {
	         MultipartRequest multi = new MultipartRequest(request,uploadFilePath,uploadFileSizeLimit,encType,new FileRenamePolicy());
	         
	         String _nickname 	= multi.getParameter("nickname");                
	         String _local 		= multi.getParameter("local");
	         String _rankgift 	= multi.getParameter("rankgift");
	         String _tel 		= multi.getParameter("tel");
	         String _kakao		= multi.getParameter("kakao");
	         String _facebook 	= multi.getParameter("facebook");
	         String _teletc 	= multi.getParameter("teletc");
	         String _photo_comment 	= multi.getParameter("photo_comment");
	         String _photo_etc 		= multi.getParameter("photo_etc");         
	         String _amt 			= multi.getParameter("amt");
	         String _prodct 		= multi.getParameter("prodct");
	                  
	         String [] org_file_name = new String[3];
	         String [] chage_file_name = new String[3];         
	         
	         
	         Enumeration files = multi.getFileNames();
	         int cnt = 0;
	         while (files.hasMoreElements()) {
		          String file = (String)files.nextElement();
		          String file_name = multi.getFilesystemName(file);
		          String ori_file_name = multi.getOriginalFileName(file);
		          org_file_name[cnt] = ori_file_name;
		          chage_file_name[cnt] = file_name;
		          System.out.println("변경      업로드된 파일명 : " + chage_file_name[cnt] );
		          System.out.println("오리지널 업로드된 파일명 : " + org_file_name[cnt] );
		          System.out.println("---------------------------------------" );
		          cnt++;	          
	        }
	        JsonObject jsonobject = new JsonObject();
	        boolean a = dao.insert_lottery_gift(_nickname, _local, _rankgift, _tel, _kakao, _facebook, _teletc, _photo_comment, _photo_etc, _amt, _prodct, org_file_name, chage_file_name); 
	        jsonobject.addProperty("boolean", a);
	        
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        String json = gson.toJson(jsonobject);
	        
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(json);  
         
        } catch (Exception e) {
         System.out.print("예외 발생 : " + e);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}