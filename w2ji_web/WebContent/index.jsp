<%@page import="lottery.DAO"%>
<%@page import="lottery.LotteryInfo" %>
<%@page import="lottery.LotteryList" %>
<%@page import="lottery.LotterySummery" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%

	DAO dao = new DAO();
	// 이번 회차 당첨 번호 리스트 
	LotteryInfo lotteryinfo = new  LotteryInfo();
	lotteryinfo = dao.getThisLotteryinfo();
	
	// 당첨자 리스트 
	List<LotteryList> l_lotterylist =  new ArrayList<LotteryList>();
	l_lotterylist = dao.getThisLotteryList();
	
	// 담첨 요약 
	List<LotterySummery> l_lotterysummery =  new ArrayList<LotterySummery>();
	l_lotterysummery = dao.getThisLotterySummery();
	
%>

<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>환영합니다.</title>
         <style>
    div{
        width: 100%;
       
        border: 0px solid #000;
        display : flex;
        justify-content : center;
        align-items : center;
    }
    div.body {
        width: 100%;
        height: 100%;                
        border: 0px solid #000;
        display : flex;
        justify-content : center;
        align-items : center;
    }    
    div.left {
        width: 50%;
        float: left;
        box-sizing: border-box;
        

    }
    div.right {
        width: 50%;
        float: right;
        box-sizing: border-box;

    }
    </style>
</head>
<body>

<div>
        <div class="left">
		<a href="./lottery.zip" download>다 운 받 기</a>
        </div>        
        <div class="right">            
    	<a href="./lottery/login.jsp">관 리 자 접 속</a><br>        
        </div>
</div>

<div>
<table>
	<tr>
		<td>
		<% if(lotteryinfo != null){
			out.print("<b>"+ lotteryinfo.getTitle()+"</b>" );
		} %>		
		</td>
	</tr>
	<tr>
		<td>
		<% if(lotteryinfo != null){
			out.print("마감일 : "+ lotteryinfo.getD_day() );
		} %>		
		</td>
	</tr>

</table>
<br>
<br>
<br>
</div>

<div class="body">
	<div class="left">
	
	<table width="600" border="1">
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
	<div class="right">
	
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



</body>
</html>