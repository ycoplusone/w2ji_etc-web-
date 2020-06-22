<%@page import="book_store.BookStoreDAO"%>
<%@page import="book_store.BookStore"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    List<BookStore> list = new ArrayList<BookStore>();
	BookStoreDAO dao = new BookStoreDAO();

    list = dao.getList("sort");
%>

<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>대여된 책</title>
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
        <b>대여량 기준 정렬</b> 
        <div class="right">
			<table width="510" border="1">
            	<thead>
            		<tr>
            			<td>아이디</td>
            			<td>책제목</td>
            			<td>저자</td>
            			<td>가격</td>
            			<td>렌탈여부</td>
            			<td>대여횟수</td>
            		</tr>
            	</thead>
            <% if(list != null){
        		for(BookStore vo : list){%>         			
          			<tr>
              			<td><%= vo.getId() %></td>
              			<td><%= vo.getNm() %></td>
              			<td><%= vo.getWriter() %></td>
              			<td><%= vo.getPrice() %></td>
              			<td><%= vo.getRentalYn() %></td>
              			<td><%= vo.getCount() %></td>                			
          			</tr>
    		<% }} %>
    		</table>
        
        </div>
    </div>



<br>

</body>
</html>