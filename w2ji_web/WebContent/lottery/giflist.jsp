<%@page import="lottery.DAO"%>
<%@page import="lottery.LotteryInfo"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	List<String[]> list = new ArrayList<String[]>();
	DAO dao = new DAO();
	list = dao.select_lottery_gift("%");	
    String aa = (String)session.getAttribute("admin");

%>

<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>회차등록</title>
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
    function edit(id , title , d_day , num1, num2, num3, num4, num5, num6){
    	console.log(id , title , d_day , num1 , num2 , num3 , num4 , num5 , num6);
    	var _id = document.getElementById("id");
    	var _title = document.getElementById("title");
    	var _d_day = document.getElementById("d_day");
    	var _num1 = document.getElementById("num1");
    	var _num2 = document.getElementById("num2");
    	var _num3 = document.getElementById("num3");
    	var _num4 = document.getElementById("num4");
    	var _num5 = document.getElementById("num5");
    	var _num6 = document.getElementById("num6");
    	var _btn = document.getElementById("btn");
    	_btn.value = '수정하기';
    	_btn.type = 'submit';
    	_btn.onclick = '';
    	_id.value = id;
    	_title.value = title;
    	_d_day.value = d_day;
    	_num1.value = num1;
    	_num2.value = num2;
    	_num3.value = num3;
    	_num4.value = num4;
    	_num5.value = num5;
    	_num6.value = num6;
    }    
    
    function push(){
    	   var form = document.lotteryinfo;
    	   <% if(list != null){
   		   for(String[] vo : list){%>
   		   <% 
   		   /*
	   		   	if( vo.getUse_yn().equals("y") ){
	   		   		out.print("alert('접수중인 회차가 있으면 등록 할수 없습니다.'); return;");	   		   		
	   		   		break;
	   		   	}*/
   		   
   		   %>
   		   <% }} %>
   			form.submit(); 
    	    
    }
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
        </div>        
         
        <div class="right">            
        	<b>목록</b>
			<table width="1024" border="1">
            	<thead>
            		<tr>
            			<td>번호</td>
            			<td>닉네임</td>
            			<td>지역</td>
            			<td>전번</td>
            			<td>카톡</td>
            			<td>페이스북</td>
            			<td>금액</td>
            			<td>물품</td>
            			<td>사진1</td>
            			<td>사진2</td>
            			<td>사진3</td>
            			<td>기타텍스트</td>
            			<td>자동반자동</td>       			
            		</tr>
            	</thead>
				<% if(list != null){
        		for(String[] ls : list){
        		//a.seq, a.nick_nm, a.local, a.rankgift, a.tel, a.kakao,a. facebook, a.teletc, a.file1, a.file1_nm, a.file2, a.file2_nm, a.file3, a.file3_nm, a.comment, a.etc, a.amt, a.prodct, a.update_dt, a.info_id, a.option_yn
        		//0      1          2         3           4  
        		%>         			
          			<tr>
						<td><%= ls[0] %></td>
            			<td><%= ls[1] %></td>
            			<td><%= ls[2] %></td>
            			<td><%= ls[4] %></td>
            			<td><%= ls[5] %></td>
            			<td><%= ls[6] %></td>
            			<td><%= ls[16] %></td>
            			<td><%= ls[17] %></td>
            			<td><%= ls[8] %></td>
            			<td><%= ls[10] %></td>
            			<td><%= ls[12] %></td>
            			<td><%= ls[15] %></td>
            			<td><%= ls[20] %></td>              			                			
          			</tr>
    			<% }} %>

    		</table>
        
        </div>
    </div>



<br>

</body>
</html>