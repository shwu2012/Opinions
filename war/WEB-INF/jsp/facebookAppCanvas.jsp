<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Options - A tiny project based on Google App Engine</title>
<meta http-equiv="refresh" content="2;url=/question.do">
</head>
<body>
<c:choose>
<c:when test="${autoRedirect}">
<p>Jumping to <b>Options</b>, A tiny project based on Google App Engine...</p>
<script type="text/javascript">
	window.top.location.href = '/question.do';
</script>
</c:when> 
<c:otherwise> 
<p><a href="/question.do" target="_top">Click here to redirect to <b>Options</b>, A tiny project based on Google App Engine...</a></p>
</c:otherwise> 
</c:choose>
</body>
</html>
