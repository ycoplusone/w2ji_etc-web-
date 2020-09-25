<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%
	 	session.setMaxInactiveInterval(10); // 초 단위
	 	session.setAttribute("v1", "true");
	 	response.sendRedirect("src/index.jsp");
	%>