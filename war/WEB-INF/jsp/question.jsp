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
<h1>Question</h1>

<c:choose>
<c:when test="${empty voteTopic}">
<div class="errorinfo">No question available...</div>
<a href="/ask.do">Ask a question now!</a>
</c:when>

<c:when test="${(empty loginUser) || readonly}">

<c:if test="${empty loginUser}">
<div class="errorinfo">Please <a href="<c:url value="/login.do"><c:param name="returnUrl" value="/question.do?id=${voteTopic.encodedKey}"/><c:param name="autoLogin" value="true"/></c:url>">login</a> to share your opinion!</div>
</c:if>

<table class="alignment">
  <tr>
    <th>Title:</th>
    <td><c:out value="${voteTopic.text}" /></td>
  </tr>
  <tr>
    <th>Image:</th>
    <td><img src="/serve?blob-key=${voteTopic.imageBlobId}" border="0" width="300" /></td>
  </tr>
  <tr>
    <th>Description:</th>
    <td><c:out value="${voteTopic.description}" /></td>
  </tr>
  <tr>
    <th>Options:</th>
    <td>
    <ul>
    <c:forEach items="${voteTopic.options}" var="voteOption" varStatus="status">
      <li><c:out value="${voteOption.text}" /></li>
	</c:forEach>
	</ul>
    </td>
  </tr>
  <c:if test="${empty loginUser}">
  <tr>
    <th>&nbsp;</th>
    <td><button onclick="window.location = '<c:url value="/login.do"><c:param name="returnUrl" value="/question.do?id=${voteTopic.encodedKey}"/><c:param name="autoLogin" value="true"/></c:url>';">Login with Facebook to vote!</button></td>
  </tr>
  </c:if>
  <c:if test="${!(empty loginUser) && readonly}">
  <tr>
    <th>&nbsp;</th>
    <td><button onclick="window.location = '<c:url value="/question.do"><c:param name="id" value="${voteTopic.encodedKey}"/></c:url>';">I'd like to vote it!</button></td>
  </tr>
  </c:if>
</table>

</c:when>
<c:otherwise>

<form action="/vote.do?id=${voteTopic.encodedKey}" method="post" id="vote_form">
<table class="alignment">
  <tr>
    <th>Title:</th>
    <td><c:out value="${voteTopic.text}" /></td>
  </tr>
  <tr>
    <th>Image:</th>
    <td><img src="/serve?blob-key=${voteTopic.imageBlobId}" border="0" width="300" /></td>
  </tr>
  <tr>
    <th>Description:</th>
    <td><c:out value="${voteTopic.description}" /></td>
  </tr>
  <tr>
    <th>Options:</th>
    <td id="options_panel">
    <c:forEach items="${voteTopic.options}" var="voteOption" varStatus="status">
      <div><label><input type="checkbox" name="optionIds" value="<c:out value="${voteOption.encodedKey}"/>" /> <c:out value="${voteOption.text}" /></label></div>
	</c:forEach>
    </td>
  </tr>
  <tr>
    <th>&nbsp;</th>
    <td><input type="submit" value="Vote" /></td>
  </tr>
</table>
</form>

</c:otherwise>
</c:choose>

</div>
<jsp:include page="_footer.jsp"/>
</body>
</html>