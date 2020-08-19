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
    function check(){
    	var _return_val = false;
    	var _t1 = false;
    	var _t2 = false;
    	var _max_val = 45;
    	var _num1 = document.getElementById("num1").value;
    	var _num2 = document.getElementById("num2").value;
    	var _num3 = document.getElementById("num3").value;
    	var _num4 = document.getElementById("num4").value;
    	var _num5 = document.getElementById("num5").value;
    	var _num6 = document.getElementById("num6").value;
    	var _num = new Array(_num1 , _num2, _num3, _num4, _num5, _num6);
    	
    	var _cnt = 0;
    	_num.forEach(function( v1 ){
    		_num.forEach(function( v2 ){
        	    if( v1 == v2 && v1 == 0 && v2 == 0){
        	    	_cnt = _cnt +1;
        	    }
        	});
    		if( _cnt >= 2 ){
    			_t2 = true;
    			_cnt = 0;
    		}else{
    			_cnt = 0;
    		} 		
    	});
    	
    	   	
    	
    	if( _num1 > _max_val || _num2 > _max_val|| _num3 > _max_val|| _num4 > _max_val|| _num5 > _max_val|| _num6 > _max_val ){
    		_t1 = true;	
    	}
    	   	
    	if(_t1 || _t2){
    		_return_val = true;
    	}
    	
    	
    	return _return_val;
    }
    
    function edit(id , title ,  num1, num2, num3, num4, num5, num6){
    	
    	var _id = document.getElementById("id");
    	var _title = document.getElementById("title");
    	var _num1 = document.getElementById("num1");
    	var _num2 = document.getElementById("num2");
    	var _num3 = document.getElementById("num3");
    	var _num4 = document.getElementById("num4");
    	var _num5 = document.getElementById("num5");
    	var _num6 = document.getElementById("num6");
    	var _btn = document.getElementById("btn");
    	_btn.value = '수정하기';
   	
    	_id.value = id;
    	_title.value = title;
    	_num1.value = num1;
    	_num2.value = num2;
    	_num3.value = num3;
    	_num4.value = num4;
    	_num5.value = num5;
    	_num6.value = num6;
    }    
    
    function push(){
    	   var form = document.lotteryinfo;
    	   
    	   var _btn = document.getElementById("btn");
    	   if( _btn.value  == '수정하기' ){
    		   
    	   }else{
    		   <% if(list != null){
    	   		   for(LotteryInfo vo : list){%>
    	   		   <% 
    		   		   	if( vo.getUse_yn().equals("y") ){
    		   		   		out.print("alert('접수중인 회차가 있으면 등록 할수 없습니다.'); return;");	   		   		
    		   		   		break;
    		   		   	}   		   
    	   		   %>
    	   		   <% }} %>    		   
    	   }
    	    var _title = document.getElementById("title");
	   		var _num1 = document.getElementById("num1");
	    	var _num2 = document.getElementById("num2");
	    	var _num3 = document.getElementById("num3");
	    	var _num4 = document.getElementById("num4");
	    	var _num5 = document.getElementById("num5");
	    	var _num6 = document.getElementById("num6");
	    	if( _title.value =='' ){ _title.value ='입력하시오'; }
   		   	if( _num1.value =='' ){ _num1.value =0; }
   		 	if( _num2.value =='' ){	_num2.value =0; }
   		 	if( _num3.value =='' ){	_num3.value =0; }
	   		if( _num4.value =='' ){	_num4.value =0; }
	   		if( _num5.value =='' ){	_num5.value =0; }
	   		if( _num6.value =='' ){	_num6.value =0; }
	   		
	   		if( check() ){
	   			alert('입력하신 숫자에 오류가 있습니다.');
	   		}else{
	   			form.submit();	
	   		}	   		
   			
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
    
    // 숫자 체크
    function inNumber(){
    	  if(event.keyCode<48 || event.keyCode>57){
    	     event.returnValue=false;
    	  }
   	}
    
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
            <b>회차 등록</b>
            
            
            <form action="../cu_lotteryinfo" method="post" name="lotteryinfo" >
            	<input type="hidden" name="id"  id="id" placeholder="번호">
            	<table   style='border-left:0;border-right:0;border-bottom:0;border-top:0'>
            		<tr>
            			<td>회차정보</td>
            			<td><input type="text" name="title" id="title" placeholder="회차정보" autocomplete="off" ></td>
            		</tr>
            		
            		<tr>
            			<td>1번</td>
            			<td><input type="text" name="num1" id="num1" placeholder="번호1" autocomplete="off" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"></td>
            		</tr>
            		<tr>
            			<td>2번</td>
            			<td><input type="text" name="num2" id="num2" placeholder="번호2" autocomplete="off" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"></td>
            		</tr>
            		<tr>
            			<td>3번</td>
            			<td><input type="text" name="num3" id="num3" placeholder="번호3" autocomplete="off" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"></td>
            		</tr>
            		<tr>
            			<td>4번</td>
            			<td><input type="text" name="num4" id="num4" placeholder="번호4" autocomplete="off" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"></td>
            		</tr>
            		<tr>
            			<td>5번</td>
            			<td><input type="text" name="num5" id="num5" placeholder="번호5" autocomplete="off" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"></td>
            		</tr>
            		<tr>
            			<td>6번</td>
            			<td><input type="text" name="num6" id="num6" placeholder="번호6" autocomplete="off" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"></td>
            		</tr>
            		<tr>
            			<td colspan=2><input type="button" id="btn" value="등록" onClick="push()" widht='150'></td>
            		</tr>
            		
            	</table>
		        
		    </form>
		    <br><br>
        	<b>목록</b>
			<table width="900" border="1">
            	<thead>
            		<tr>
            			<td><center>순번</center></td>
            			<td><center>회차정보</center></td>
            			<td><center>마감일</center></td>
            			<td><center>번호1</center></td>
            			<td><center>번호2</center></td>
            			<td><center>번호3</center></td>
            			<td><center>번호4</center></td>
            			<td><center>번호5</center></td>
            			<td><center>번호6</center></td>            			
            			<td><center>상태</center></td>   
            			<td><center></center></td>
            			<td><center>비고</center></td>         			
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
              			<td>
              			<% 
            				if( vo.getUse_nm().equals("접수중") ){
            					out.print("접수중 ");
            				}else{
            					out.print("마감");            					
            				}
            			%>
              			
              			</td>
              			<td>
              				<% if( vo.getUse_yn().equals("y") ){ 
              					out.print("<input type='button' value='수정' onClick=\"edit('"+vo.getId()+"','"+vo.getTitle()+"','"+vo.getNum1()+"','"+vo.getNum2()+"','"+vo.getNum3()+"','"+vo.getNum4()+"','"+vo.getNum5()+"','"+vo.getNum6()+"')\" /> "); 
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