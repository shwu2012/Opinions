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
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="content">
<h1>Ask a question?</h1>
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
</div>
<jsp:include page="_footer.jsp"/>
</body>
</html>