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
    function edit(id , title , d_day , num1, num2, num3, num4, num5, num6){
    	
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
    
    function fnImgPop(url){
    	  var img=new Image();
    	  img.src=url;
    	  var img_width=img.width+75;
    	  var win_width=img.width;
    	  var img_height=img.height+125;
    	  var win=img.height;
    	  var OpenWindow=window.open('','_blank', 'width='+img_width+', height='+img_height+', menubars=no, scrollbars=auto');
    	  OpenWindow.document.write("<style>body{margin:0px;}</style><img src='"+url+"' width='"+win_width+"'>");
    }
    
    function option_on(seq) {
    	var _from = document.getElementById("frmSubmit");
    	var _seq = document.getElementById("seq");
    	var _yn  = document.getElementById("yn");
    	_seq.value= seq;
    	_yn.value = 'n';
    	_from.submit();
    }
    
    function option_off( seq ) {
    	var _from = document.getElementById("frmSubmit");
    	var _seq = document.getElementById("seq");
    	var _yn  = document.getElementById("yn");
    	_seq.value= seq;
    	_yn.value = 'y';
    	_from.submit();
    	
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
         
         <form id='frmSubmit' action='../option_yn'>
         	<input type='hidden' name='seq' id='seq' value=''>
         	<input type='hidden' name='yn'  id='yn'  value=''>
         </form>
         
        <div class="right">            
        	<b>목록</b>
			<table width="1024" border="1">
            	<thead>
            		<tr>
            			<td><center>번호</center></td>
            			<td><center>닉네임</center></td>
            			<td><center>지역</center></td>
            			<td><center>전번</center></td>
            			<td><center>카톡</center></td>
            			<td><center>페이스북</center></td>
            			<td><center>금액</center></td>
            			<td><center>물품</center></td>
            			<td><center>사진1</center></td>
            			<td><center>사진2</center></td>
            			<td><center>사진3</center></td>
            			<td><center>기타<br>텍스트</center></td>
            			<td><center>자동<br>반자동</center></td>
            			<td><center>비고</center></td>
    			
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
            			<td>
            			<% 
            				if( ls[8]==null||ls[8].equals("") ){
            					out.print("없음");
            				}else{
            					out.print("<img src='../upload/"+ls[8]+"' width='50' height='50' onclick='fnImgPop(this.src)'></img>");
            				}
            			%>
            			</td>
            			<td>
            			<% 
            				if( ls[10]==null||ls[10].equals("") ){
            					out.print("없음");
            				}else{
            					out.print("<img src='../upload/"+ls[10]+"' width='50' height='50' onclick='fnImgPop(this.src)'></img>");
            				}
            			%>
            			</td>
            			<td>
            			<% 
            				if( ls[12]==null||ls[12].equals("") ){
            					out.print("없음");
            				}else{
            					out.print("<img src='../upload/"+ls[12]+"' width='50' height='50' onclick='fnImgPop(this.src)'></img>");
            				}
            			%>
            			</td>
            			<td><%= ls[15] %></td>
            			<td>
            			<% 
            				if(ls[20].equals("y")){
            					out.print("자동설정 ");
            					out.print("<button onclick=\"option_on(\'"+ls[0]+"\')\">변경</button>");
            				}else{
            					out.print("　미설정 ");
            					out.print("<button onclick=\"option_off(\'"+ls[0]+"\');\">변경</button>");
            				}
            			%>
            			</td>
            			<td></td>  
 
       			                			
          			</tr>
    			<% }} %>

    		</table>
        
        </div>
    </div>



<br>

</body>
</html>