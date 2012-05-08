<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
<c:choose>
    <c:when test="${empty loginUser}">
        <img src="/image/default_user_image.jpg" /> 
    	Hello, Guest | 
        <a href="/login.do">Login</a>
    </c:when>
    <c:otherwise>
    	<img src="https://graph.facebook.com/${loginUser.externalId}/picture" /> 
        Hello, <c:out value="${loginUser.name}" /> | 
        <a href="/ask.do">Ask a question</a> | 
  		<a href="/question.do">Share opinions</a> | 
  		<a href="/results.do">My questions</a> |
        <a href="/logout.do">Logout</a>
    </c:otherwise>
</c:choose>
</header>