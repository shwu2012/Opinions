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
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#loginForm').submit(function() {
		  var username = $('#loginForm > input[name=userKey]').val();
		  if ($.trim(username) == '') {
			  alert("Username cannot be empty.");
			  return false;
		  }
		});
});
</script>
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="content">
<h1>Welcome! Please login...</h1>
<form name="loginForm" id="loginForm" action="/login.do" method="post">
	User ID: <input type="text" name="userKey" /><br />
	<input type="submit" value="Login" />
</form>
<a href="/register.do">Not registered?</a>
</div>
<jsp:include page="_footer.jsp"/>
</body>
</html>