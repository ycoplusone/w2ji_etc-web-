<%@page import="music.DAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    //List<BookStore> list = new ArrayList<BookStore>();
	DAO dao = new DAO();

    //list = dao.getList("rentaled");
%>


<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>관리자 </title>
    <link href="https://fonts.googleapis.com/css?family=Neucha" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="screen" href="./assets/css/style.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>    
    
    
    <script>
	<%
	String chk = (String) request.getParameter("chk")==null ? "":(String) request.getParameter("chk") ;
	if(chk.equals("true")){
		session.setAttribute("admin", "true");
		out.print("alert('관리자님 환영합니다.');");
		out.print(" window.location.href = './addmusic.jsp'; ");
		
	}else if(chk.equals("false")){
		session.setAttribute("admin","false");
		out.print("alert('다시 입력하세요.');");
	}
	%>    
	</script>
    
</head>
<body>

    <div>
            <form action="../musicgame/adminlogin" method="post">
            	<center>
		        <p><input class="in" type="text" name="id" placeholder="아이디"></p>
		        <p><input class="in" type="password" name="ps" placeholder="비밀번호"></p>
		        </center>		        
		        <button type="submit"  value="접속">접속</button>
		        
		    </form>

    </div>



<br>

</body>
</html>