<%@page import="lottery.DAO"%>
<%@page import="lottery.LotteryInfo"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    List<LotteryInfo> list = new ArrayList<LotteryInfo>();
	DAO dao = new DAO();
	list = dao.getLotteryinfo();
	
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
   		   for(LotteryInfo vo : list){%>
   		   <% 
	   		   	if( vo.getUse_yn().equals("y") ){
	   		   		out.print("alert('접수중인 회차가 있으면 등록 할수 없습니다.'); return;");	   		   		
	   		   		break;
	   		   	}
   		   
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
		<a href="./addlottery.jsp">회차등록</a><br>        
        <a href="./notice.jsp">후원 문구 수정</a><br>        
        </div>        
         
        <div class="right">
            <b>회차 등록</b>
            <form action="../cu_lotteryinfo" method="post" name="lotteryinfo" >
            	<p>
	            	<input type="hidden" name="id"  id="id" placeholder="번호">
			        <input type="text" name="title" id="title" placeholder="회차정보">
			        <input type="text" name="d_day" id="d_day" placeholder="마감일자(ex:20201225)">
			        
			        <select name="use_yn" id="use_yn">
					    <option value="y">접수중</option>
					    <option value="c">마감</option>
					</select>			        
		        </p>
		        <p>
		        	<input type="text" name="num1" id="num1" placeholder="번호1">
		        	<input type="text" name="num2" id="num2" placeholder="번호2">
		        	<input type="text" name="num3" id="num3" placeholder="번호3">
		        	<input type="text" name="num4" id="num4" placeholder="번호4">
		        	<input type="text" name="num5" id="num5" placeholder="번호5">
		        	<input type="text" name="num6" id="num6" placeholder="번호6">
		        </p>
		        <p><input type="button" id="btn" value="등록" onClick="push()"></p>
		    </form>
        	<b>목록</b>
			<table width="700" border="1">
            	<thead>
            		<tr>
            			<td>순번</td>
            			<td>회차정보</td>
            			<td>마감일</td>
            			<td>번호1</td>
            			<td>번호2</td>
            			<td>번호3</td>
            			<td>번호4</td>
            			<td>번호5</td>
            			<td>번호6</td>            			
            			<td>상태</td>   
            			<td>비고</td>         			
            		</tr>
            	</thead>
				<% if(list != null){
        		for(LotteryInfo vo : list){%>         			
          			<tr>
              			<td><%= vo.getId() %></td>
              			<td><%= vo.getTitle() %></td>
              			<td><%= vo.getD_day() %></td>
              			<td><%= vo.getNum1() %></td>
              			<td><%= vo.getNum2() %></td>
              			<td><%= vo.getNum3() %></td>
              			<td><%= vo.getNum4() %></td>
              			<td><%= vo.getNum5() %></td>
              			<td><%= vo.getNum6() %></td>
              			<td><%= vo.getUse_nm() %></td>
              			<td>
              				<% if( vo.getUse_yn().equals("y") ){ 
              					out.print("<input type='button' value='수정' onClick=\"edit('"+vo.getId()+"','"+vo.getTitle()+"','"+vo.getD_day()+"','"+vo.getNum1()+"','"+vo.getNum2()+"','"+vo.getNum3()+"','"+vo.getNum4()+"','"+vo.getNum5()+"','"+vo.getNum6()+"')\" /> "); 
              				}else{
              				}%>
              			</td>                			
          			</tr>
    			<% }} %>

    		</table>
        
        </div>
    </div>



<br>

</body>
</html>