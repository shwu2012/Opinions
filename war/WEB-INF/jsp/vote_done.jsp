<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset=utf-8 />
<title>Ask questions and share opinions!</title>
<link type="text/css" rel="stylesheet" href="css/main.css" />
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="content">
<h1>Your opinion is published!</h1>
<ul>
  <li><a href="/question.do?id=${voteTopicEncodedKey}&view=true">Have a look at the previous question again.</a></li>
  <li><a href="/question.do">Try another question!</a></li>
</ul>
</div>
<jsp:include page="_footer.jsp"/>
</body>
</html>