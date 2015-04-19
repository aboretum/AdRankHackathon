

//called when the user clicks the Login button on the login screen
function loginUser(){
	// ajax call 
	//alert("loginUser working");	

	//on login navigate to search.html , window.location.href to search.html

}

//called when the user clicks the Submit button on the Forgot Password screen
function forgotPassword(){
	// ajax call
	//alert("forgotPassword working");

}

//called when the user clicks the Register button on the Sign Up screen
function signupUser(){
	alert("signupUser working");

	var email = $('#email').val();
	var firstName = $('#fname').val();
	var lastName = $('#lname').val();
	//alert("works");
	var password = $('#pwd').val();
	var gender = $( '#gender').val();

	// ajax call

}



// function loadResults(){
// 	alert("test");
// 	alert("working");
// 	alert($('#searchText').val());
// 	// get the result object here and use the asme loop to form an object same as ajax call 


// //	$.ajax({
// //     url: 'http://gdata.youtube.com/feeds/mobile/videos?alt=json-in-script&q=' + query,
// //     dataType: 'jsonp',
// //     success: function (data) {
// //     	alert("success");
// //         var row = "";
// //         for (i = 0; i < data.feed.entry.length; i++) {
// //             row += "<div class='search_item'>";
// //             row += "<table width='100%'>";
// //             row += "<tr>";
// //             row += "<td vAlign='top' align='left'>";
// //             row += "<a href='#' ><img width='120px' height='80px' src=" + data.feed.entry[i].media$group.media$thumbnail[0].url + " /></a>";
// //             row += "</td>";
// //             row += "<td vAlign='top' width='100%' align='left'>";
// //             row += "<a href='#' ><b>" + data.feed.entry[i].media$group.media$title.$t + "</b></a><br/>";
// //             row += "<span style='font-size:12px; color:#555555'>by " + data.feed.entry[i].author[0].name.$t + "</span><br/>";
// //             row += "<span style='font-size:12px' color:#666666>" + data.feed.entry[i].yt$statistics.viewCount + " views" + "<span><br/>";
// //             row += "</td>";
// //             row += "</tr>";
// //             row += "</table>";
// //             row += "</div>";
// //         }
// //         document.getElementById("search-results-block").innerHTML = row;
// //     },
// //     error: function () {
// //         alert("Error loading youtube video results");
// //     }
// // });
// return false;
// }



// loop through the user list to populate
function populateFrequentUsers(){

	var ul = document.getElementById("frequentUsers");

	// insert for loop here
	
	var li = document.createElement("li");
	li.className = "list-group-item";
	//li.addClass("list-group-item");
	li.appendChild(document.createTextNode("Four"));
	ul.appendChild(li);

	// for loop ends
}


function populateRecentlySearched(){
	//alert("comes here");
	//iterate through data and add the video links just like what we did in search SearchYouTube(query) in search.html
	// this shows the list of videos


	// var row = "";
 //    for (i = 0; i < data.feed.entry.length; i++) {
 //        row += "<div class='search_item'>";
 //        row += "<table width='100%'>";
 //        row += "<tr>";
 //        row += "<td vAlign='top' align='left'>";
 //        row += "<a href="+ data.feed.entry[i].media$group.media$player[0].url +" ><img width='120px' height='80px' src=" + data.feed.entry[i].media$group.media$thumbnail[0].url + " /></a>";
 //        row += "</td>";
 //        row += "<td vAlign='top' width='100%' align='left'>";
 //        row += "<a href="+ data.feed.entry[i].media$group.media$player[0].url +" ><b>" + data.feed.entry[i].media$group.media$title.$t + "</b></a><br/>";
 //        row += "<span style='font-size:12px; color:#555555'>by " + data.feed.entry[i].author[0].name.$t + "</span><br/>";
 //        row += "<span style='font-size:12px' color:#666666>" + data.feed.entry[i].yt$statistics.viewCount + " views" + "<span><br/>";
 //        row += "</td>";
 //        row += "</tr>";
 //        row += "</table>";
 //        row += "</div>";
 //    }
    document.getElementById("recently-searched-block").innerHTML = row;
}