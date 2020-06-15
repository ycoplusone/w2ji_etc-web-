<%@page import="guestbook.GuestBookDAO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("utf-8");
    Integer no = Integer.parseInt(request.getParameter("no"));
    String Inputpwd = request.getParameter("pwd");
    
    GuestBookDAO vo = new GuestBookDAO();
    String dbPwd = vo.getPwd(no);
    String parseInputPwd = vo.getInputPwd(Inputpwd);
    
    if( dbPwd.equals(parseInputPwd)){
        vo.delete(no);
    }
    response.sendRedirect("/guestbook");
%>