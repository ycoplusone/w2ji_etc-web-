<%@page import="lottery.DAO"%>
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
    <style>
     div {
        width: 100%;
        height: 250px;        
        border: 0px solid #000;
        display : flex;
        justify-content : center;
        align-items : center;
    }
    </style>
    <script>
	<%
	String chk = (String) request.getParameter("chk")==null ? "":(String) request.getParameter("chk") ;
	if(chk.equals("true")){
		session.setAttribute("admin", "true");
		out.print("alert('관리자님 환영합니다.');");
		out.print(" window.location.href = './addlottery.jsp'; ");
		
	}else if(chk.equals("false")){
		session.setAttribute("admin","false");
		out.print("alert('다시 입력하세요.');");
	}
	%>    
	</script>
    
</head>
<body>

    <div>
            <form action="../userlogin" method="post">
		        <p><input type="text" name="id" placeholder="아이디"></p>
		        <p><input type="password" name="ps" placeholder="비밀번호"></p>
		        <p><input type="submit" value="접속"></p>
		    </form>

    </div>



<br>

</body>
</html>