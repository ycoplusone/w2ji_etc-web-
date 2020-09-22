//hide the submit button and gameContent when the page loads
$("#submit").hide();
$("#gameContent").hide();
//declare global variables to keep track of time, correct answers, incorrect answers, unanswered questions, and which mode the user has selected
var gameTime;
var intervalId;
var correctAnswers = 0;
var wrongAnswers = 0;
var unAnswered = 0;
var hardClicked;
//create question object
var questions = [
    {
        ques: "Who was the first drummer of the Beatles?",
        ans: ["Pete Best", "Ringo Starr", "Travis Barker", "Buddy Rich"],
        name: "firstBeatlesDrummer",
        correct: "Pete Best",
        divClass: ".firstBeatlesDrummer"
    },
    {
        ques: "What city is synonymous with grunge?",
        ans: ["New York", "Los Angeles", "Seattle", "London"],
        name: "grunge",
        correct: "Seattle",
        divClass: ".grunge"
    },
    {
        ques: "What sport was Bob Marley's skill considered to be at a professional level?",
        ans: ["Cricket", "Soccer", "Tennis", "Basketball"],
        name: "bobMarley",
        correct: "Soccer",
        divClass: ".bobMarley"
    },
    {
        ques: "Who is one famous musician that died on the day the music died?",
        ans: ["Don McLean", "Prince", "Amy Winehouse", "Buddy Holly"],
        name: "musicDied",
        correct: "Buddy Holly",
        divClass: ".musicDied"
    },
    {
        ques: "Who is not a left-handed guitarist?",
        ans: ["Kurt Cobain", "Paul McCartney", "Slash", "Jimi Hendrix"],
        name: "leftHanded",
        correct: "Slash",
        divClass: ".leftHanded"
    },
    {
        ques: "What famous song's bass line was sampled for Shaggy's song 'Angel'?",
        ans: ["Queen - Under Pressure", "The Steve Miller Band - The Joker", "The Seinfeld Intro", "Motörhead - Ace of Spades"],
        name: "angel",
        correct: "The Steve Miller Band - The Joker",
        divClass: ".angel"
    },
    {
        ques: "In what city were the Rolling Stones formed?",
        ans: ["Liverpool", "Manchester", "Bristol", "London"],
        name: "rollingStones",
        correct: "London",
        divClass: ".rollingStones"
    },
    {
        ques: "Who was the first artist to win a Grammy without a record contract?",
        ans: ["Taylor Swift", "Chance the Rapper", "Amy Winehouse", "Beyonce"],
        name: "grammy",
        correct: "Chance the Rapper",
        divClass: ".grammy"
    },
    {
        ques: "What was the first music video played on MTV when it launched on August 1, 1981?",
        ans: ["The Buggles - Video Killed The Radio Star", "Sheena Easton - 9 to 5", "Rick Springfield - Jessie's Girl", "Hall and Oates - Private Eyes"],
        name: "mtv",
        correct: "The Buggles - Video Killed The Radio Star",
        divClass: ".mtv"
    },
    {
        ques: "What is the name of the beat that is featured in all reggaeton songs?",
        ans: ["Paradiddle", "Train Beat", "Money Beat", "Dem Bow"],
        name: "beat",
        correct: "Dem Bow",
        divClass: ".beat"
    },

]
var loadedJSON;

function init(){
	
	var xhr= new XMLHttpRequest();
	var target= "http://127.0.0.1:8080/w2ji_web/musicqlist";
	xhr.open("GET", target);
	xhr.send();
	xhr.onreadystatechange= function(){
		if(xhr.readyState=== XMLHttpRequest.DONE){
			if(xhr.status== 200){
				loadedJSON = JSON.parse(xhr.responseText);
				return loadedJSON;
				//$("#content").html("resultCode: "+ loadedJSON.resultCode+ "<br>");
			}else{
				alert("fail to load");
			}
		}
	}
}

//create logic that when the button is pressed, game starts - my game has 2 modes, so the timing will depend on which mode the user chose
//if the user selects Challenge Mode, then gameTime = 31 seconds. if the user selectes Easy Mode, then gameTime = 61 seconds. Added 1 second here to give the user the full time.
$("#hard").on("click", function () {
	init();
	
    hardClicked = true;
    event.preventDefault();
    setTimeout(function() {    	
    	questions = loadedJSON;
    	console.log(questions);
    	startGame();
    }, 1500);    
});

$("#easy").on("click", function () {
	init();
    hardClicked = false;
    event.preventDefault();
    setTimeout(function() {
    	console.log(questions);
    	questions = loadedJSON;
    	startGame();
    }, 1500);    
});

function congame(){
	init();
	$("#results").empty();
	
	$("#time-left").empty();
    $("#time-left").show();
    //hide the questions
    $(".questions").show();
    $(".questions").empty();
    $(".questions").append('<div><br><button id="submit" onclick="su()">Submit</button></div>');
    
    //hide the masthead
    $("h1").show();
	
	event.preventDefault();
    setTimeout(function() {
    	console.log(questions);
    	questions = loadedJSON;
    	startGame();
    }, 1500);
}


function startGame() {
    //conditional to set gameTime based on whether the user selects the Challenge Mode or Easy Mode. Only one mode can be selected to start the game.
    if (hardClicked) {
        gameTime = 31;
    }
    else {
        gameTime = 61;
    }
    //setInterval function to call decrement every second
    intervalId = setInterval(decrement, 1000);
    //show the submit button
    $("#submit").show();
    //show the game content
    $("#gameContent").show();
    //hide the introductory screen
    $("#startScreen").hide();
    //function call to generate the questions and write them to the page
    questionDisplay();
}
//function to display the questions on the page 
function questionDisplay() {
    // Show questions on page by looping through the questions array and attaching html elements to help with displaying the information
    for (var i = 0; i < questions.length; i++) {
        $('.questions').prepend(`<div class= ${questions[i].name}></div>`);
        $(questions[i].divClass).append(`<h2>${questions[i].ques}</h2`);
        var f_music = `${questions[i].music}`;
        var f_img = `${questions[i].image}`;
        if( f_img ==''){
        	f_img = 'ww.png';
        }
        $(questions[i].divClass).append('<table><tr><td><audio controls ><source src="./upload/'+f_music+'"></source></audio></td>');
        $(questions[i].divClass).append('<td><img src="./upload/'+f_img+'" width=200 height=200></td></tr></table>');
        
        
        // loops through answers for each radio button and write them to the page as radio buttons
        for (var j = 0; j <= 3; j++) {
            $(questions[i].divClass).append(`<input type="radio"  name="${questions[i].name}" value="${questions[i].ans[j]}"/><label>${questions[i].ans[j]}</label>`);
        }
        //output looks nicer with the line added
        $('.questions').prepend(`<hr />`);

    }
}
//create a function that grades the trivia challenge
function grade() {

    //for loop to compare the answer the user selected via radio buttons to the correct answer in the questions array
    for (var i = 0; i < questions.length; i++) {
        //scenario where a user selects the correct answer is if the checked value of the question radio button is the same as the correct option in the questions object
        if ($(`input:radio[name="${questions[i].name}"]:checked`).val() === questions[i].correct) {
            correctAnswers++;
            //scenario where a user leaves a question unanswered is if the checked value of the question radio button is undefined which means that it is not checked
        }
        else if ($(`input:radio[name="${questions[i].name}"]:checked`).val() === undefined) {
            unAnswered++;
            //scenario where a user gets a question wrong is if it is neither correct nor unanswered
        }
        else {
            wrongAnswers++;
        }
    }

}

function su(){
    grade();
    //call the endGame function to end the game
    endGame();
    event.preventDefault();	
}

//this click event handler dictates what happens when the Submit button is clicked
$("#submit").on("click", function () {
    //call grade function to get the user's count of correct and wrong answers, and unanswered questions
    grade();
    //call the endGame function to end the game
    endGame();
    event.preventDefault();
})
//function to tell the user how much time is left for the trivia challenge. The number gets updated to the page every second.
function decrement() {
    gameTime--;
    $("#time-left").html(`<h2>Time Remaining: ${gameTime} seconds</h2>`);
    //if the user runs out of time, or time = 0, then the game ends and the user's score is shown
    if (gameTime === 0) {
        //call grade function to get the user's count of correct and wrong answers, and unanswered questions
        grade();
        //call the endGame function to end the game
        endGame();
    }
}
function endGame() {
    //stop the timer
    clearInterval(intervalId);
    //hide the time div
    $("#time-left").hide();
    //hide the questions
    $(".questions").hide();
    //hide the masthead
    $("h1").hide();
    var t0 = `${correctAnswers}`;
    var t1 = `${wrongAnswers}`;
    console.log('t1',t1);
    var t2 ='';
    if(t1==0 && t0 !=0 ){
    	t2 = `<button onclick='congame()'>Continue</button>`;
    }
    //show stats on the page
    $("#results").prepend(`<h2>Thanks for taking the Music Trivia Challenge!</h2> <br>Correct Answers: ${correctAnswers}<br> Wrong Answers: ${wrongAnswers} <br> Unanswered Questions: ${unAnswered} <br>`+t2);
    
    

}
