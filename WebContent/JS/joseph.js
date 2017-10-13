function append() {
	var a = $("#info").val();
	var li = document.createElement('li');
	li.innerText = a;
	$("#info").attr("value", "");
	$("#peoplesList").append(li);
	
	$("#peoplesList li").hover(function() {
		$("#peoplesList li").css("background-color", "white");
		$("#selected").attr("id", "");
		$(this).css("background-color", "#E9E9E4"); // The selected display this
		// color when mouse over
		$(this).attr("id", "selected"); // Setting a id for the selected
		$(".menu").css("display", "block");
	}, function() {
		/* $(".menu").css("display", "none"); */
	});

	console.log("Append: " + li.innerText); // print log in browser console
	console.log("Peoples are:" + $("#peoplesList").text());
};

function insertPeople() { // Insert a person
	var a = $("#info").val();
	var li = document.createElement('li');
	li.innerText = a;
	$("#selected").before(li);
	$(".menu").css("display", "none");

	console.log("Insert " + li.innerText + +' in insertPeople');
	console.log("Peoples are:" + $("#peoplesList").text());
};

function deletePeople() { // Delete a person
	$("#selected").remove();
	$(".menu").css("display", "none");

	console.log($("#selected").text() + " remove!");
	console.log("Peoples are:" + $("#peoplesList").text());
};

function moveUpPeople() { // MoveUp
	$.each($("#selected"), function() {
		var onthis = $(this);
		var getUp = onthis.prev();
		$(onthis).after(getUp);
		$(".menu").css("display", "none");
	});

	console.log($("#selected").text() + " move up!");
	console.log("Peoples are:" + $("#peoplesList").text());
};

function moveDownPeople() { // MoveDown
	$.each($("#selected"), function() {
		var onthis = $(this);
		var getdown = onthis.next();
		$(getdown).after(onthis);
		$(".menu").css("display", "none");
	});

	console.log($("#selected").text() + " move down!");
	console.log("Peoples are:" + $("#peoplesList").text());
};

function RenamePeople() { // Rename for a person
	var a = $("#info").val();
	var li = document.createElement('li');
	li.innerText = a;
	$("#selected").replaceWith(li);
	$(".menu").css("display", "none");

	console.log("Rename : " + $("#selected").text());
	console.log("Peoples are:" + $("#peoplesList").text());
};

function clear() {
	/*
	 * $("#peoplesList").empty(); $(":text").val(""); $("#last").empty();
	 * $("#myMenu").hide();
	 */
	location.reload();
};

function validateStart(value) {
	if (value == "") {
		return false;
	} else {
		if (Number.isInteger(Number(value))) {
			if (Number(value) > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
};

function validateInterval(value) {
	if (value == "") {
		return false;
	} else {
		if (Number.isInteger(Number(value))) {
			if (Number(value) >= 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
};

function setEmpty()
{
	$("#info").val("");
};

function returnErrors(field,errors,textId,errorId,message,data){
	
	if($.inArray(field,errors)!=-1){		
		 $(textId).css("border","2px solid #ff0080 ");
		 $(errorId).css("color","#ff0080").html('<p>'+message+ data + '</p>');	
	}
};


function calc() {

/*	// ($("#istart").val().search(/^[0-9]*[1-9][0-9]*$/) == -1) // from 1

	if (validateStart($("#istart").val())) {

		// ($("#iinterval").val().search(/^\d+$/) == -1) // from 0
		if (validateInterval($("#iinterval").val())) {

			
			  var josephObj = { // Create a json object for inputs people :
			  peopleArr, start : $("#istart").val(), interval :
			  $("#iinterval").val() };
			  
			  var joseph = JSON.stringify(josephObj);
			 
			if ($("#peoplesList").children().text() != "") {

				var peopleArr = [];
				$("#peoplesList").each(function() { // Peoples array
					$(this).children().each(function() {
						peopleArr.push($(this).text());
					});
				});
				var circleobj = { // Create a json object for inputs
					start : $("#istart").val(),
					interval : $("#iinterval").val(),
					persons : peopleArr
				};
			} else {
				alert("Peoples cann`t be null!");
				console.log("Peoples cann`t be null!");
			}
		} else {
			alert("Interval shound be a non-negative integer !");
			console.log("Interval shound be a non-negative integer!");
		}
	} else {
		alert("Start shound be a positive integer!");
		console.log("Start shound be a positive integer!");
	}*/
	
	if ($("#peoplesList").children().text() != "") {

		var peopleArr = [];
		$("#peoplesList").each(function() { // Peoples array
			$(this).children().each(function() {
				peopleArr.push($(this).text());
			});
		});
		var circleobj = { // Create a json object for inputs
			start : $("#istart").val(),
			interval : $("#iinterval").val(),
			persons : peopleArr
		};
	} else {
		alert("Peoples cann`t be null!");
		console.log("Peoples cann`t be null!");
	}
	
	var josephObj = {
		circle : circleobj
	};
	var joseph = JSON.stringify(josephObj);
	console.log("The json is:" + joseph);
   
/*	if(!$("#validate").valid()){
		return false;
	}*/
	
	$.ajax({  // Send request
	    url : "JosephController",
		type : "post",
		async : false,
		data : joseph,
		datatype : "application/json",
		contentType : "application/json",
		error:function(request){
		    	console.log("error");
		    	window.location.href ="Errors.html";
			},
		success : function(data1) {
			if(data1.lastPeople!=null){
				$("#last").css("color","black").html('The last one is:<p class="text">' + data1.lastPeople + '</p>');
				console.log("The last people:" + data1.lastPeople);
			}else{		
				var length = data1.errors.length;
				var errors = [length];
				
				for(var i=0; i<length; i++){
					errors[i]=data1.errors[i].field ;					
				}
				returnErrors("circle.start",errors,"#istart","#error1","Start:",data1.errors[0].message);
				returnErrors("circle.interval",errors,"#iinterval","#error2","Interval:",data1.errors[0].message);
				returnErrors("circle.persons",errors,"ol","#error3","Peoples:",data1.errors[0].message);
				
			    console.log(errors);
			}			
		}
	});
    return false;
};
$(document).ready(function() {
	$("#validate").validate({
        rules:{  
        	start:{
        		required:true, 
        		digits:true,
        		min:1
        	},
        	interval:{
        		required:true, 
        		digits:true,
        		min:0
        	},
        	info:{
        		required:true,
        		minlength:1
        	}
	   }      			
	});
	$("#submit").click(calc);
	$("#clear").click(clear);
});
