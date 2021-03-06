<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<meta charset="utf-8">
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
.line {
  stroke: black;
  stroke-width: 1;
}

.ledger {
  visibility: hidden;
}

.buttons {
  display: flex;
  justify-content: center;
}

button {
  background-color: transparent;
  outline: none;
  border: 2px solid black;
  width: 40px;
  height: 40px;
  margin-right: 10px;
  font-weight: bold;
  border-radius: 5px;
}

button:hover {
  color: white;
  background-color: black;
}

.score {
  width: 500px;
  margin: auto;
}

#msg {
  text-align: center;
  font-weight: bold;
  visibility: hidden;
}

.correct {
  color: green;
}

.incorrect {
  color: red;
}

.score>button {
  width: auto;
}

.note {
  text-align: center;
  font-weight: bold;
}
</style>
</head>
<body>
  <div class="score">
    <h3>점수: <span id="points"></span> / <span id="counter"></span></h3>
    <button id="next">시작</button>
    <!-- button onclick="setCookie()">쿠키 생성</button>
    <button onclick="getCookie('game1')">가져오기</button>
    <button onclick="movepage()">이동</button-->
    
    
    
    
  </div>
  <p id="msg"><br /><br /></p>
  <svg id="score" height="125" width="500" style="display:block;margin:50px auto;">
    <line class="line" x1="0" y1="45" x2="500" y2="45" />
    <line class="line" x1="0" y1="55" x2="500" y2="55" />
    <line class="line" x1="0" y1="65" x2="500" y2="65" />
    <line class="line" x1="0" y1="75" x2="500" y2="75" />
    <line class="line" x1="0" y1="85" x2="500" y2="85" />
    <image xlink:href="clef.svg" height="75" width="44" x="0" y="30" />
    <image id="note" xlink:href="wholenote.svg" height="10" width="15" x="242" y="5" />
    <line class="line ledger" id="l-1" x1="236" y1="95" x2="262" y2="95" />
    <line class="line ledger" id="l-2" x1="236" y1="105" x2="262" y2="105" />
    <line class="line ledger" id="l-3" x1="236" y1="115" x2="262" y2="115" />
    <line class="line ledger" id="l1" x1="236" y1="35" x2="262" y2="35" />
    <line class="line ledger" id="l2" x1="236" y1="25" x2="262" y2="25" />
    <line class="line ledger" id="l3" x1="236" y1="15" x2="262" y2="15" />
  </svg>

  <div class="buttons">
    <button id="doNote" disabled>도</button>
    <button id="reNote" disabled>레</button>
    <button id="miNote" disabled>미</button>
    <button id="faNote" disabled>파</button>
    <button id="solNote" disabled>솔</button>
    <button id="laNote" disabled>라</button>
    <button id="siNote" disabled>시</button>
  </div>
  
  <br />
  <!-- p class="note">Selecciona el nombre de la nota que aparece en el pentagrama.</p>
  <p class="note">Puedes utilizar un teclado presionando: <br>
    C = Do<br>
    D = Re<br>
    E = Mi<br>
    F = Fa<br>
    G = Sol<br>
    A = La<br>
    B = Si<br>
    ENTER = Siguiente<br-->
    
<script>
  window.addEventListener('load', init);
  let n = '';
  let notePos = '';
  let answer = '';
  let point = 0;
  let count = 0;
  var msg = document.getElementById('msg');

 const notes = [
    '5',
    '10',
    '15',
    '20',
    '25',
    '30',
    '35',
    '40',
    '45',
    '50',
    '55',
    '60',
    '65',
    '70',
    '75',
    '80',
    '85',
    '90',
    '95',
    '100',
    '105',
    '110',
    '115'
  ]

document.getElementById('next').addEventListener('click', nextFunc);

function nextFunc() {	//시작 버튼	
	if( getCookie('game1') == getYymmdd() ){
		alert('하루 한번 가능합니다.');
		return;
	}else{
		setCookie();
	}
	
	msg.style.visibility = "hidden";  
	document.getElementById('l-1').style.visibility = "hidden";
	document.getElementById('l-2').style.visibility = "hidden";
	document.getElementById('l-3').style.visibility = "hidden";
	document.getElementById('l1').style.visibility = "hidden";
	document.getElementById('l2').style.visibility = "hidden";
	document.getElementById('l3').style.visibility = "hidden";  
	buttoninit('true');
	init();
}

function continueFunc() {	//계속
	  //msg.style.visibility = "hidden";  
	  document.getElementById('l-1').style.visibility = "hidden";
	  document.getElementById('l-2').style.visibility = "hidden";
	  document.getElementById('l-3').style.visibility = "hidden";
	  document.getElementById('l1').style.visibility = "hidden";
	  document.getElementById('l2').style.visibility = "hidden";
	  document.getElementById('l3').style.visibility = "hidden";
	  init();
}


function setCookie(){
	alert(document.cookie);
    // 변수를 선언
    // 변수를 선언한다.
    var date = new Date();
	date.setDate(date.getDate() +365);
	var yymmdd =  getYymmdd();
    var val = "";
    val += "game1="+yymmdd+";";
    val += "path=/;";
    val += "Expires=" + date.toUTCString();
    
    // 쿠키를 집어넣는다.
    document.cookie = val;
}

function getCookie(name) {
	var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
	return value? value[2] : null;
}

function getYymmdd(){
    var date = new Date();    
	var year = date.getFullYear();              //yyyy
	var month = (1 + date.getMonth());          //M
	month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
	var day = date.getDate();                   //d
	day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
	return year + '-' + month + '-' + day;       //'-' 추가하여 yyyy-mm-dd 형태 생성 가능
}

function buttoninit( flag ){ //버튼 활성화 체크
	if( flag == 'true' ){
		document.getElementById('next').disabled = true;
		doNote.disabled = false;
		reNote.disabled = false;
		miNote.disabled = false;
		faNote.disabled = false;
		solNote.disabled = false;
		laNote.disabled = false;
		siNote.disabled = false;		
	}else{
		document.getElementById('next').disabled = false;
		doNote.disabled = true;
		reNote.disabled = true;
		miNote.disabled = true;
		faNote.disabled = true;
		solNote.disabled = true;
		laNote.disabled = true;
		siNote.disabled = true;		
	}
	
}


function noteSwitch() {
  if(n == 20 || n == 55 || n == 90) {
    answer = "Do"; 
  } else if(n == 15 || n == 50 || n == 85) {
    answer = "Re";
  } else if(n == 10 || n == 45 || n == 80 || n == 115) {
    answer = "Mi";
  } else if(n == 5 || n == 40 || n == 75 || n == 110) {
    answer = "Fa";
  } else if(n == 35 || n == 70 || n == 105) {
    answer = "Sol";
  } else if(n == 30 || n == 65 || n == 100) {
    answer = "La";
  } else if(n == 25 || n == 60 || n == 95) {
    answer = "Si";
  }
}

function init() {
  let x = Math.floor((Math.random() * notes.length) + 1);
  if (note == Number(notes[x])){
    document.getElementById('l-1').style.visibility = "hidden";
    document.getElementById('l-2').style.visibility = "hidden";
    document.getElementById('l-3').style.visibility = "hidden";
    document.getElementById('l1').style.visibility = "hidden";
    document.getElementById('l2').style.visibility = "hidden";
    document.getElementById('l3').style.visibility = "hidden";
    x = Math.floor((Math.random() * notes.length) + 1);
    x = Math.floor((Math.random() * notes.length) + 1);
  }

  note = document.getElementById('note').attributes.y.value = notes[x];  
  n = Number(note);
  console.log('n : '+n);

  
 

  if(n >= 90){
      document.getElementById('l-1').style.visibility = "visible";
  } if(n >= 100){
      document.getElementById('l-2').style.visibility = "visible";
  } if(n >= 110){
      document.getElementById('l-3').style.visibility = "visible";
  } if(n <= 10){
      document.getElementById('l3').style.visibility = "visible";
  } if(n <= 20){
      document.getElementById('l2').style.visibility = "visible";
  } if(n <= 30){
      document.getElementById('l1').style.visibility = "visible";
  } if(isNaN(n) == true) {
    init();
  }
}

document.addEventListener('keyup', function(e){
  if (e.keyCode == 67){
    if(doNote.disabled != true){
      doCheck();
    } else {return 0;}
  }
  if (e.keyCode == 68){
    if(reNote.disabled != true){
      reCheck();
    } else {return 0;}
  }
  if (e.keyCode == 69){
    if(miNote.disabled != true){
      miCheck();
    } else {return 0;}
  }
  if (e.keyCode == 70){
    if(faNote.disabled != true){
      faCheck();
    } else {return 0;}
  }
  if (e.keyCode == 71){
    if(solNote.disabled != true){
      solCheck();
    }
  }
  if (e.keyCode == 65){
    if(laNote.disabled != true){
      laCheck();
    } else {return 0;}
  }
  if (e.keyCode == 66){
    if(siNote.disabled != true){
      siCheck();
    } else {return 0;}
  }
  if (e.keyCode == 13) {
    if(document.getElementById('next').disabled != true) {
      nextFunc();
    } else {return 0;}
  }
});

var doNote = document.getElementById('doNote');
var reNote = document.getElementById('reNote');
var miNote = document.getElementById('miNote');
var faNote = document.getElementById('faNote');
var solNote = document.getElementById('solNote');
var laNote = document.getElementById('laNote');
var siNote = document.getElementById('siNote');

doNote.addEventListener('click', doCheck);
reNote.addEventListener('click', reCheck);
miNote.addEventListener('click', miCheck);
faNote.addEventListener('click', faCheck);
solNote.addEventListener('click', solCheck);
laNote.addEventListener('click', laCheck);
siNote.addEventListener('click', siCheck);

function doCheck(){
  if(n == 20 || n == 55 || n == 90) {
    msg.innerHTML = '정답!<br /><br />';
    msg.style.visibility = "visible";
    msg.classList.remove('incorrect');
    msg.classList.add('correct');
    point++;
    continueFunc();
  } else {
    noteSwitch();
    msg.innerHTML = '"도" 정답이 아닙니다.. <br /> '  ;
    msg.style.visibility = "visible";
    msg.classList.remove('correct'); 
    msg.classList.add('incorrect');
  }
  count++;
  over_check();  
}

function reCheck(){
  if(n == 15 || n == 50 || n == 85) {
    msg.innerHTML = '정답!<br /><br />';
    msg.style.visibility = "visible";
    msg.classList.remove('incorrect');
    msg.classList.add('correct');
    point++;
    continueFunc();
  } else {
    noteSwitch();
    msg.innerHTML = '"레"  정답이 아닙니다.. <br /> '  ;
    msg.style.visibility = "visible";
    msg.classList.remove('correct'); 
    msg.classList.add('incorrect');
  }
  count++;
  over_check();
}

function miCheck(){
  if(n == 10 || n == 45 || n == 80 || n == 115) {
    msg.innerHTML = '정답!<br /><br />';
    msg.style.visibility = "visible";
    msg.classList.remove('incorrect');
    msg.classList.add('correct');
    point++;
    continueFunc();
  } else {
    noteSwitch();
    msg.innerHTML = '"미" 정답이 아닙니다.. <br /> '  ;
    msg.style.visibility = "visible";
    msg.classList.remove('correct'); 
    msg.classList.add('incorrect');
  }
  count++;
  over_check();

}

function faCheck(){
  if(n == 5 || n == 40 || n == 75 || n == 110) {
    msg.innerHTML = '정답!<br /><br />';
    msg.style.visibility = "visible";
    msg.classList.remove('incorrect');
    msg.classList.add('correct');
    point++;
    continueFunc();
  } else {
    noteSwitch();
    msg.innerHTML = '"파" 정답이 아닙니다.. <br /> '  ; 
    msg.style.visibility = "visible";
    msg.classList.remove('correct'); 
    msg.classList.add('incorrect');
  }
  count++;
 
  over_check();
}

function solCheck(){
  if(n == 35 || n == 70 || n == 105) {
    msg.innerHTML = '정답!<br /><br />';
    msg.style.visibility = "visible";
    msg.classList.remove('incorrect');
    msg.classList.add('correct');
    point++;
    continueFunc();
  } else {
    noteSwitch();
    msg.innerHTML = '"솔" 정답이 아닙니다.. <br /> '  ;
    msg.style.visibility = "visible";
    msg.classList.remove('correct'); 
    msg.classList.add('incorrect');
  }
  count++;
  
  over_check();
}

function laCheck(){
  if(n == 30 || n == 65 || n == 100) {
    msg.innerHTML = '정답!<br /><br />';
    msg.style.visibility = "visible";
    msg.classList.remove('incorrect');
    msg.classList.add('correct');
    point++;
    continueFunc();
  } else {
    noteSwitch();
    msg.innerHTML = '"라" 정답이 아닙니다.. <br /> '  ;
    msg.style.visibility = "visible";
    msg.classList.remove('correct'); 
    msg.classList.add('incorrect');
  }
  count++;  
  over_check();
}

function siCheck(){
  if(n == 25 || n == 60 || n == 95) {
    msg.innerHTML = '정답!<br /><br />';
    msg.style.visibility = "visible";
    msg.classList.remove('incorrect');
    msg.classList.add('correct');
    point++;
    continueFunc();
  } else {
    noteSwitch();
    msg.innerHTML = '"시" 정답이 아닙니다.. <br /> '  ;
    msg.style.visibility = "visible";
    msg.classList.remove('correct'); 
    msg.classList.add('incorrect');
  }
  count++;
  over_check();
   
}

function over_check(){	
	if( (count - point ) >= 2 ){
		point = 0;
		count = 0;		
		alert('2회 틀리셧습니다. \n 다시 시작하세요.');
		  buttoninit('false');
		//document.getElementById('next').disabled = false;
	}else if( point >= 10 ){
		alert('멜로디 게임으로 이동합니다.');
		//location.href="src/index.html";
		movepage();
	}
	document.getElementById('counter').innerHTML = count;
	document.getElementById('points').innerHTML = point;
}

function movepage(){	
	location.href="move.jsp";
}

</script>
</body>
</html>

