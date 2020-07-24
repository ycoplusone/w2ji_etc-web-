<%@page import="lottery.DAO" %>
<%@page import="lottery.LotteryNotice" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    List<LotteryNotice> list = new ArrayList<LotteryNotice>();
	DAO dao = new DAO();
	list = dao.getLotteryNoice("y");    
%>

<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>후원 문구 수정</title>
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
         
        <div class="right">
        	<b>목록</b>
			<table width="510" border="1">
            	<thead>
            		<tr>
            			<td>후원 정보</td>
            		</tr>
            	</thead>
            <% if(list != null){
        		for(LotteryNotice vo : list){%>         			
          			<tr>          			
              			<td>
              				<textarea readonly cols="65" rows="5" style="resize: none;">
              					<%= vo.getTxt() %>
              				</textarea>
              			</td>
          			</tr>
    		<% }} %>
    		</table>
        
        </div>
    </div>



<br>

</body>
</html>