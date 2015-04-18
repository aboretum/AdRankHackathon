

//called when the user clicks the Login button on the login screen
function loginUser(){
	// ajax call 
	alert("loginUser working");	

}

//called when the user clicks the Submit button on the Forgot Password screen
function forgotPassword(){
	// ajax call
	alert("forgotPassword working");

}

//called when the user clicks the Register button on the Sign Up screen
function signupUser(){
	alert("signupUser working");

	var email = $('#email').val();
	var firstName = $('#fname').val();
	var lastName = $('#lname').val();
	alert("works");
	var password = $('#pwd').val();
	var gender = $( '#gender').val();

	alert(email);
	alert(firstName);
	alert(lastName);
	alert(password);
	alert(gender);
	// ajax call
	alert("signupUser working");

}