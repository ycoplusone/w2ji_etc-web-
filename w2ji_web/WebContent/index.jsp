<%@page import="guestbook.GuestBookDAO"%>
<%@page import="guestbook.GuestBookVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    List<GuestBookVO> list = new ArrayList<GuestBookVO>();
    GuestBookDAO dao = new GuestBookDAO();

    list = dao.getList();
%>

<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>방명록</title>
</head>
<body>
<form action="/guestbook/add.jsp" method="post">
    <table border="1" width="500">
        <tr>
            <td>이름</td><td><input type="text" name="name"></td>
            <td>비밀번호</td><td><input type="password" name="pwd"></td>
        </tr>
        <tr>
            <td colspan=4><textarea name="content" cols=60 rows=5></textarea></td>
        </tr>
        <tr>
            <td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
        </tr>
    </table>
</form>
<br>
    <% if(list != null){
        for(GuestBookVO vo : list){ %>
        <table width="510" border="1">
            <tr>
                <td><%= vo.getNo() %></td>
                <td><%= vo.getName() %></td>
                <td><%= vo.getRegDate() %></td>
                <td><a href="/guestbook/deleteform.jsp?no=<%= vo.getNo() %>">삭제</a></td>
            </tr>
            <tr>
                <td><%= vo.getContent() %></td>
            </tr>
        </table>
        <br>
        <% } %>
    <% } %>
</body>
</html>