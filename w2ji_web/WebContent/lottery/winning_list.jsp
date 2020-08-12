<%@page import="lottery.LotterySummery"%>
<%@page import="lottery.DAO"%>
<%@page import="lottery.LotteryInfo"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	List<String[]> list = new ArrayList<String[]>();
	DAO dao = new DAO();
	list = dao.select_lottery_gift("%" , "%");	
    String aa = (String)session.getAttribute("admin");
    
    List<String[]> com = new ArrayList<String[]>();
    com = dao.getLotteryInfo();
    //getLotteryInfo
%>

<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>회차등록</title>
    <style>
    table,th,td{
    	border:1px solid gray;
    	border-collapse:collapse;
    }

    
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
    	if(aa ==null || aa.equals("")){
    		out.print(" window.location.href = '../index.jsp'; ");
    	}else if(aa.equals("true")){
	    	//정상
	    }else{
	    	out.print(" window.location.href = '../index.jsp'; ");
	    }
    %>
    
    
    
    </script>
</head>
<body>

    <div>
        <div class="left">
        <a href="../index.jsp">홈</a><br>
		<a href="./addlottery.jsp">회차등록</a><br>        
        <a href="./notice.jsp">후원 문구 수정</a><br>  
        <a href="./giflist.jsp">선물 리스트</a><br>
        <a href="./winning_list.jsp">당첨 정보</a><br>      
        </div>        
         

         
        <div class="right">
        	<br>
        	<p><b>회차정보 정보</b></p>    
        	    	
			<form id='frmSubmit' action='./winning_list.jsp' method="post">
	        	<select name="info">
	        	<% 
	            	if(com != null){            	
	        		for(String[] vo : com){%>
	        		<option value="<%= vo[0] %>"><%= vo[1] %></option>
	    		<% }} %>
	    		</select>   
	    		<input type="submit"  value="조회"  widht='150'>	
    		</form>	
        	
        	
        	<br><br><br><br><br><br>
        	<p><b>당첨자 정보</b></p>
        	<%
         	// 담첨 요약 
         	
         	String info = "";//request.getParameter("info");
         	String _tmp = request.getParameter("info");
         	
        
         	if(_tmp == null || _tmp.equals("")){
         		info = com.get(0)[0];
         	}else{
         		info = _tmp;
         	}
        	
        	List<LotterySummery> l_lotterysummery =  new ArrayList<LotterySummery>();
         	
         	l_lotterysummery = dao.getThisLotterySummery(info);
        	%>
        	<table width="200" border="1">
            	<thead>
            		<tr>
            			<td>당첨그룹</td>
            			<td>누적</td>
            		</tr>
            	</thead>
            <% if(l_lotterysummery != null){            	
        		for(LotterySummery vo : l_lotterysummery){%>         			
          			<tr>
              			<td><%= vo.getCnt() %></td>
              			<td><%= vo.getTot_cnt() %></td>                			
          			</tr>
    		<% }} %>
    		</table>
        </div>            
        	
    </div>



<br>

</body>
</html>