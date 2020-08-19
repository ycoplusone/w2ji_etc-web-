<%@page import="lottery.LotteryList"%>
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
    com = dao.getLotteryInfoList();
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
        	    	
			<form id='frmSubmit' action='./winning_list.jsp' method="post" height = "150">
	        	<select name="info"   style="width:500px;height:70px;"  >
	        	<% 
	            	if(com != null){            	
	        		for(String[] vo : com){%>
	        		<option value="<%= vo[0] %>"><%= vo[1] %></option>
	    		<% }} %>
	    		</select>   
	    		<input type="submit"  value="조회"  style="width:150px;height:70px;">	
    		</form>	        	
        	
        	<br><br>
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
         	
        	LotteryInfo lotteryinfo = new  LotteryInfo();
        	lotteryinfo = dao.getThisLotteryinfo(info);
        	
        	// 당첨자 리스트 
        	List<LotteryList> l_lotterylist =  new ArrayList<LotteryList>();
        	l_lotterylist = dao.getThisLotteryList(info);
         	
         	
        	%>
        	
<table>
	<tr>
		<td>
		<center>
		<% if(lotteryinfo != null){
			out.print("<b> 회차 정보 : "+ lotteryinfo.getTitle()+"</b>" );
		} %>		
		</center>
		</td>
	</tr>
	<tr>
		<td>
		<center>
		<% if(lotteryinfo != null){
			out.print("마감일 : "+ lotteryinfo.getD_day() );
		} %>	
		</center>	
		</td>
	</tr>

</table>
	<br>
		<br>
			<br>

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
    		
    		<br>
    		<br>
    		<br>
    		<br>

	
	<table width="600" border="1" style="border:1px solid gray;   	border-collapse:collapse;">
            	<thead>
            		<tr>
            			<td>순서</td>
            			<td>별칭</td>
            			<td>번호1</td>
            			<td>번호2</td>
            			<td>번호3</td>
            			<td>번호4</td>
            			<td>번호5</td>
            			<td>번호6</td>
            			<td>일치</td>
            			
            			
            		</tr>
            	</thead>
            <% if(l_lotterylist != null){
            	int cnt = 1;
        		for(LotteryList vo : l_lotterylist){%>         			
          			<tr>
              			<td><%= cnt %></td>
              			<td><%= vo.getNick_nm() %></td>
              			<td <% if(vo.getNum1_chk().equals("true")){out.print("style = 'background-color: #bbdefb'");} %>><%= vo.getNum1() %></td>
              			<td <% if(vo.getNum2_chk().equals("true")){out.print("style = 'background-color: #bbdefb'");} %>><%= vo.getNum2() %></td>
              			<td <% if(vo.getNum3_chk().equals("true")){out.print("style = 'background-color: #bbdefb'");} %>><%= vo.getNum3() %></td>
              			<td <% if(vo.getNum4_chk().equals("true")){out.print("style = 'background-color: #bbdefb'");} %>><%= vo.getNum4() %></td>
              			<td <% if(vo.getNum5_chk().equals("true")){out.print("style = 'background-color: #bbdefb'");} %>><%= vo.getNum5() %></td>
              			<td <% if(vo.getNum6_chk().equals("true")){out.print("style = 'background-color: #bbdefb'");} %>><%= vo.getNum6() %></td>              			
              			<td><%= vo.getCnt() %></td>                			
          			</tr>
    		<% cnt++; }} %>
    		</table>
	
	
	       	

        	
        
 
    		
        </div>            
        	
    </div>



<br>

</body>
</html>