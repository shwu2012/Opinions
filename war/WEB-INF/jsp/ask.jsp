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
<h1>welcome, <c:out value="${loginUser.name}"/>!</h1>
<form action="/ask.do" method="post">
	Title: <input type="text" name="title" /><br />
	Desc: <input type="text" name="description" /><br />
	<input type="text" name="options" /><br />
	<input type="text" name="options" /><br />
	<input type="text" name="options" /><br />
	<input type="text" name="options" /><br />
	<input type="text" name="options" /><br />
	<input type="text" name="options" /><br />
	<input type="text" name="options" /><br />
	<input type="submit" value="Ask" />
</form>

<h1>Your questions</h1>
<ul>
	<c:forEach items="${voteTopics}" var="voteTopic" varStatus="status">
		<li><c:out value="${voteTopic.text}" /></li>
	</c:forEach>
</ul>

	<a href="/index.do">Back to home</a>
<a href="/logout.do">Logout</a>
</body>
</html>