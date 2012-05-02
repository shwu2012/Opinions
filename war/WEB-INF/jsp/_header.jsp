<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
<c:choose>
    <c:when test="${empty loginUser}">
        <a href="/register.do">Register</a> | 
        <a href="/login.do">Login</a>
    </c:when>
    <c:otherwise>
        Hello, <a href="/profile.do"><c:out value="${loginUser.name}" /></a> |
        <a href="/ask.do">Ask a question</a> | 
  		<a href="/vote.do">Share opinions</a> | 
  		<a href="/results.do">My questions</a> |
  		<a href="/friends.do">Friends</a> | 
        <a href="/logout.do">Logout</a>
    </c:otherwise>
</c:choose>
</header>