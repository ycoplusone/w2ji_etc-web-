<%@page import="guestbook.GuestBookDAO"%>
<%@page import="guestbook.GuestBookVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    request.setCharacterEncoding("utf-8");
    
    String name = request.getParameter("name");
    String pwd = request.getParameter("pwd");
    String content = request.getParameter("content");
    
    GuestBookVO vo = new GuestBookVO();
    
    vo.setName(name);
    vo.setPwd(pwd);
    vo.setContent(content);
    
    GuestBookDAO dao = new GuestBookDAO();
    dao.insert(vo);
    
    
    response.sendRedirect("/guestbook");
%>