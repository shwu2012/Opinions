<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset=utf-8 />
<title>Ask questions and share opinions!</title>
<link type="text/css" rel="stylesheet" href="css/main.css" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="content">
<h1>See you later <c:out value="${logoutUserName}" />!</h1>
<p id="logoutMsg">Please wait, we are logging you out of Facebook... </p>

<div id="fb-root"></div>

<!-- pull down Facebook's Javascript SDK -->
<script src="http://connect.facebook.net/en_US/all.js"></script>

<!-- execute the following script immediately when the page loads -->
<script>
function gotoLoginPage() {
	window.location = '<c:out value="${loginUrl}" />';	
}

// initialize the library with your Facebook API key
FB.init({ apiKey: '<c:out value="${fbApiKey}" />' });

// fetch the status so that we can log out

// you must have the login status before you can logout
// and if you authenticated via oAuth (server side), this is necessary.
// if you logged in via the JavaScript SDK, you can simply call FB.logout()
// once the login status is fetched, call handleSessionResponse
FB.getLoginStatus(function(response) {
	console.log("response.status = %s", response.status);
	if (response.status === 'connected') {
		var uid = response.authResponse.userID;
		var accessToken = response.authResponse.accessToken;
		console.log("uid = %s", uid);
	    console.log("accessToken = %s", accessToken);
	    FB.logout(function(response) {
	    	  // user is now logged out
	    	  console.log("user is now logged out");
	    	  $('#logoutMsg').append('<b>Done!</b>');
	    	  gotoLoginPage();
	    	});
	} else if (response.status === 'not_authorized') {
		// the user is logged in to Facebook, 
		// but has not authenticated your app
		$('#logoutMsg').append('<b>You are not logged in our website.</b>');
		gotoLoginPage();
	} else {
		// the user isn't logged in to Facebook.
		$('#logoutMsg').append('<b>You are not logged in Facebook.</b>');
		gotoLoginPage();
	}
});

//if we do have a non-null response.session, call FB.logout(),
//the JS method will log the user out
//of Facebook and remove any authorization cookies


</script>

</div>
<jsp:include page="_footer.jsp"/>
</body>
</html>