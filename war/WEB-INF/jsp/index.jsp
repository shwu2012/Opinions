<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset=utf-8 />
<title>Home</title>
</head>
<body>
<h1>Welcome, <c:out value="${loginUser.name}"/>!</h1>
<h2>This is your home page!</h2>
<a href="/ask.do">I have question now!</a>
<a href="/logout.do">Logout</a>
</body>
</html>