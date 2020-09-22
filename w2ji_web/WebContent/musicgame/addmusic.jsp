<%@page import="music.DAO"%>
<%@page import="music.Musicgame"%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    List<Musicgame> list = new ArrayList<Musicgame>();
	DAO dao = new DAO();
	list = dao.getMusicList("%","%");
	
    String aa = (String)session.getAttribute("admin");

%>

<!doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>회차등록</title>
        <link href="https://fonts.googleapis.com/css?family=Neucha" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="screen" href="./assets/css/style.css" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
    
    
    function edit(id , ques ,  ans1, ans2, ans3, ans4, correct, name ,use_yn ){
    	
    	var _id = document.getElementById("id");
    	var _ques = document.getElementById("ques");
    	var _ans1 = document.getElementById("ans1");
    	var _ans2 = document.getElementById("ans2");
    	var _ans3 = document.getElementById("ans3");
    	var _ans4 = document.getElementById("ans4");
    	var _correct = document.getElementById("correct");
    	var _name = document.getElementById("name");
    	var _use_yn = document.getElementById("use_yn");
    	//_btn.value = '수정하기';
   	
    	_id.value = id;
    	_ques.value = ques;
    	_ans1.value = ans1;
    	_ans2.value = ans2;
    	_ans3.value = ans3;
    	_ans4.value = ans4;
    	_correct.value = correct;
    	_name.value = name;
    	_use_yn.value = use_yn;
    }    
    
    function push(){
    	   var form = document.addmusic;
    	   var check = 0;
    	   
    	   
    	    var _ques = document.getElementById("ques");
	   		var _ans1 = document.getElementById("ans1");
	    	var _ans2 = document.getElementById("ans2");
	    	var _ans3 = document.getElementById("ans3");
	    	var _ans4 = document.getElementById("ans4");
	    	var _correct = document.getElementById("correct");	    	
	    	var _name = document.getElementById("name");
	    	
	    	if( _ques.value =='' ){ 
	    		alert('질문를 입력하시오');
	    		check=1;
	    	}
   		   	if( _ans1.value =='' ){ 
	   		   	alert('문답1을 입력하시오');
	   		 	check=1; 
   		   	}
   		 	if( _ans2.value =='' ){	
	   		 	alert('문답2을 입력하시오');
	   		 	check=1;  
   		 	}
   		 	if( _ans3.value =='' ){	
	   		 	alert('문답3을 입력하시오');
	   		 	check=1;  
   		 	}
	   		if( _ans4.value =='' ){	
	   			alert('문답4을 입력하시오');
	   			check=1;  
	   		}
	   		if( _correct.value =='' ){	
	   			alert('정답을 입력하시오');
	   			check=1;  
	   		}
	   		if( _name.value =='' ){	
	   			alert('이름을 입력하시오');
	   			check=1;  
	   		}
	   		if(check ==0){
	   			form.submit();	
	   		}
	   		
   			
    }
    
    function fnImgPop(url){
    	  var img=new Image();
    	  img.src=url;
    	  var img_width=img.width;
    	  var win_width=img.width+25;
    	  var img_height=img.height;
    	  var win=img.height+30;
    	  var OpenWindow=window.open('','_blank', 'width='+img_width+', height='+img_height+', menubars=no, scrollbars=auto');
    	  OpenWindow.document.write("<style>body{margin:0px;}</style><img src='"+url+"' >");
	}
    
    function fnMusicPop(url){
  	  var OpenWindow=window.open('','_blank', 'width=350, height=150, menubars=no, scrollbars=no');
  	  OpenWindow.document.write("<audio controls autoplay loop preload><source src='"+url+"'></source></audio>");
	}
    
    
    <%
    	if(aa ==null || aa.equals("")){
    		out.print(" window.location.href = './musicgame/index.jsp'; ");
    	}else if(aa.equals("true")){
	    	//정상
	    }else{
	    	out.print(" window.location.href = './musicgame/index.jsp'; ");
	    }
    %>
    

    
    </script>
</head>
<body style="background-image: url('');">

    <div>
        <div class="right">
        <br>
        	<button onclick='location.href="./index.html"'>초기화면으로 가기</button>
            <b>문제 등록</b>
            
            
            <form action="../musicfileupload" method="post"  enctype="multipart/form-data" name="addmusic">
            	
            	<table   border="1">
            		
            		<tr>
            			<input type="hidden" name="id" id="id" placeholder="키" autocomplete="off" >
            			<td>질문</td>
            			<td colspan=4><input class='in' type="text" name="ques" id="ques" placeholder="질문" autocomplete="off" size="140" ></td>
            		</tr>
            		
            		<tr>
            			<td>문답</td>
            			<td><input class='in' type="text" name="ans1" id="ans1" placeholder="문답1" autocomplete="off" ></td>
            			<td><input class='in' type="text" name="ans2" id="ans2" placeholder="문답2" autocomplete="off" ></td>
            			<td><input class='in' type="text" name="ans3" id="ans3" placeholder="문답3" autocomplete="off" ></td>
            			<td><input class='in' type="text" name="ans4" id="ans4" placeholder="문답4" autocomplete="off" ></td>
            		</tr>
            		<tr>
            			<td>정답</td>
            			<td colspan=4><input class='in' type="text" name="correct" id="correct" placeholder="정답" autocomplete="off" size="140" ></td>
            		</tr>
            		<tr>
            			<td>이름</td>
            			<td colspan=4><input class='in' type="text" name="name" id="name" placeholder="이름" autocomplete="off" size="140" ></td>
            		</tr>
            		<tr>
            			<td>음악파일</td>
            			<td colspan=4><input class='in' type="file" name="musicfile" id="musicfile" placeholder="음악파일" autocomplete="off"  accept=".wav, .mp4, .mp3" size="140" ></td>
            		</tr>
            		<tr>
            			<td>이미지파일</td>
            			<td colspan=4><input class='in' type="file" name="imgfile" id="imgfile" placeholder="그림파일" autocomplete="off"  accept=".gif, .jpg, .png" size="140" ></td>
            		</tr>
            		
            		<tr>
            			<td>사용여부</td>
            			<td  colspan=4>
            			<select  class='in' name="use_yn" id="use_yn">
            				<option value="y">사용</option>
  							<option value="n">중지</option>
            			</select>
            			</td>
            		</tr>
            		<tr>
            			<td colspan=5><input class='in' type="button" id="btn" value="등록 하기" onClick="push()"  ></td>
            		</tr>
            		
            	</table>
		        
		    </form>
		    <br><br>
		    
        	<b>목록</b>
        	<table width="900" border="1">
            	<thead>
            		<tr>
            			<td><center>번호</center></td>
            			<td><center>질문</center></td>
            			<td><center>문답1</center></td>
            			<td><center>문답2</center></td>
            			<td><center>문답3</center></td>
            			<td><center>문답4</center></td>
            			<td><center>정답</center></td>
            			<td><center>이름</center></td>
            			<td><center>음악</center></td>            			
            			<td><center>이미지</center></td>   
            			<td><center>사용</center></td>
            			<td><center>비고</center></td>         			
            		</tr>
            	</thead>
				<% if(list != null){
        		for(Musicgame vo : list){%>         			
          			<tr>
              			<td><%= vo.getId() %></td>
              			<td><%= vo.getQues() %></td>
              			<td><%= vo.getAns1() %></td>
              			<td><%= vo.getAns2() %></td>
              			<td><%= vo.getAns3() %></td>
              			<td><%= vo.getAns4() %></td>
              			<td><%= vo.getCorrect() %></td>
              			<td><%= vo.getName() %></td>
              			<td>
              			<%
							if( !vo.getMusicFile().isEmpty() ){
              					out.print("<button onclick=\"fnMusicPop(\'./upload/"+vo.getMusicFile()+"\')\">음악</button>");		
              				}              				
              			%>
              			</td>
              			<td>
              			<%
							if( !vo.getImgFile().isEmpty() ){
              					out.print("<button onclick=\"fnImgPop(\'./upload/"+vo.getImgFile()+"\')\">그림</button>");		
              				}              				
              			%>
              			</td>
              			<td>
              			<% 
            				if( vo.getUseYn().equals("y") ){
            					out.print("사용 ");
            				}else{
            					out.print("미사용");            					
            				}
            			%>
              			</td>
              			<td>
              				<% 
              					out.print("<input class='in' type='button' value='수정' onClick=\"edit('"+vo.getId()+"','"+vo.getQues()+"','"+vo.getAns1()+"','"+vo.getAns2()+"','"+vo.getAns3()+"','"+vo.getAns4()+"','"+vo.getCorrect()+"','"+vo.getName()+"','"+vo.getUseYn()+"')\" /> "); 
             				%>
              			</td>                			
          			</tr>
    			<% }} %>

    		</table>
			
        
        </div>
    </div>



<br>

</body>
</html>