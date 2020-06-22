<%@page import="book_store.BookStoreDAO"%>
<%@page import="book_store.BookStore"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    List<BookStore> list = new ArrayList<BookStore>();
	BookStoreDAO dao = new BookStoreDAO();

    list = dao.getList("rentaled");
%>


<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>책 추가하기</title>
    <style>
    div {
        width: 100%;
        height: 1024px;
        
        border: 0px solid #000;
    }
    div.left {
        width: 20%;
        float: left;
        box-sizing: border-box;
        
        /*background: #ff0;*/
    }
    div.right {
        width: 80%;
        float: right;
        box-sizing: border-box;
        
        /*background: #0ff;*/
    }
    </style>
    <script>
	<%
	String chk = (String) request.getParameter("chk")==null ? "":(String) request.getParameter("chk") ;
	if(chk.equals("true")){
		out.print("alert('추가 되었습니다.');");
	}else if(chk.equals("false")){
		out.print("alert('추가에 실패 하였습니다.');");
	}
	%>    
	</script>
    
</head>
<body>

    <div>
        <div class="left">
		<a href="./list.jsp">목록</a><br>
        <a href="./sortlist.jsp">대여량 기준 정렬</a><br>
        <a href="./rentaledlist.jsp">대여된 책</a><br>
        <a href="./rentallist.jsp">대여 가능한 책</a><br>
        <a href="./rental.jsp">대여</a><br>
        <a href="./return.jsp">반납</a><br>
        <a href="./bookadd.jsp">추가</a><br>
        
        </div>
        <b>책 등록 하기</b>
        <div class="right">		
            <form action="../bookadd" method="get">
		        <p><input type="text" name="nm" placeholder="책 제목"></p>
		        <p><input type="text" name="writer" placeholder="저자"></p>
		        <p><input type="text" name="price" placeholder="가격"></p>
		        <p><input type="submit" value="등록"></p>
		    </form>
        </div>
    </div>



<br>

</body>
</html>