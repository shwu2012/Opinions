<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset=utf-8 />
<title>Welcome Page</title>
</head>
<body>
	<h1>Your question is ready!</h1>
	<ul>
		<c:forEach items="${voteTopics}" var="voteTopic" varStatus="status">
			<li><c:out value="${voteTopic.text}" /></li>
		</c:forEach>
	</ul>
</body>
</html>