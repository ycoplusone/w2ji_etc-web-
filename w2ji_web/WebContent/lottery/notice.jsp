<%@page import="lottery.DAO" %>
<%@page import="lottery.LotteryNotice" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    List<LotteryNotice> list = new ArrayList<LotteryNotice>();
	DAO dao = new DAO();
	list = dao.getLotteryNoice("%");    
	String aa = (String)session.getAttribute("admin");
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
    <script>
    function edit(seq , use_yn ){
    	console.log( 'seq ',seq );
    	
    	var t_seq = document.getElementById("eid_"+seq);
    	var t_txt = document.getElementById("etxt_"+seq);
    	var t_use_yn = document.getElementById("euse_"+seq);

    	var _seq = document.getElementById("seq");
    	var _txt = document.getElementById("txt");
    	var _use_yn = document.getElementById("use_yn");
    	var _btn = document.getElementById("btn");
    	
    	
    	
    	_btn.value = '수정하기';
    	_seq.value = seq;
    	_txt.value = t_txt.textContent;
    	_use_yn.value = use_yn;
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
			<b>후원 정보 등록하기</b>
            <form action="../cu_lotterynotice" method="post" name="lotterynotice" >
            	<p><input type="hidden" name="seq"  id="seq" cols="30" rows="5" style="resize: none;"></p>
				<p>
					<textarea  name="txt" id="txt" placeholder="후원 문구"  ></textarea> 
				</p>			        
			    <p>
			    	<select name="use_yn" id="use_yn">
					    <option value="y">사용</option>
					    <option value="n">미사용</option>
					</select>			        
		        </p>

		        <p><input type="submit" id="btn" value="등록"></p>
		    </form>
		    <br>        
        	<b>목록</b>
			<table width="510" border="1">
            	<thead>
            		<tr>
            			<td>번호</td>
            			<td>후원 문구</td>
            			<td>상태</td>
            			<td>비고</td>
            		</tr>
            	</thead>
            <% if(list != null){
        		for(LotteryNotice vo : list){%>         			
          			<tr>          			
              			<td><%= vo.getSeq() %></td>
              			<td id='etxt_<%=vo.getSeq() %>'><%= vo.getTxt() %></td>
              			<td><%= vo.getUse_nm() %></td>
						<td>
							<% out.print("<input type='button' value='수정' onClick=\"edit('"+vo.getSeq()+"','"+vo.getUse_yn()+"')\" /> ");  %> 
              			</td>                			
          			</tr>
    		<% }} %>
    		</table>
        
        </div>
    </div>



<br>

</body>
</html>