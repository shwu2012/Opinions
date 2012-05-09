<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<h1>My questions</h1>
<ul>
<c:forEach items="${voteTopics}" var="voteTopic" varStatus="status">
	<div class="entity_item">
		<h2><c:out value="${voteTopic.text}" /></h2>
		<p><c:out value="${voteTopic.description}" /></p>
		<ul>
		<c:forEach items="${voteTopic.options}" var="voteOption" varStatus="status">
		  <li><c:out value="${voteOption.text}" /></li>
		</c:forEach>  
		</ul>
		<div class="tools">
			<a href="/question.do?id=${voteTopic.encodedKey}&view=true">View</a> |
			<a href="/question.do?id=${voteTopic.encodedKey}">Vote</a>  
 		</div>
	</div>
</c:forEach>
</ul>
</div>
<jsp:include page="_footer.jsp"/>
</body>
</html>