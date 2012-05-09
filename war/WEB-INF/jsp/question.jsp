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
<script type="text/javascript" src="js/jquery.1.7.2.min.js"></script>
</head>
<body>
<div id="fb-root"></div>
<script>
(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/all.js#xfbml=1&appId=217557015023052";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
</script>
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
      <li><c:out value="${voteOption.text}" /> (${fn:length(voteOption.actions)} Votes)</li>
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
      <div><label><input type="checkbox" name="optionIds" value="<c:out value="${voteOption.encodedKey}"/>" /> <c:out value="${voteOption.text}" /> (${fn:length(voteOption.actions)} Votes)</label></div>
	</c:forEach>
    </td>
  </tr>
  <tr>
    <th>&nbsp;</th>
    <td><input type="submit" value="Vote" /> <span id="checkedOptionsError" class="errorinfo"></td>
  </tr>
</table>
</form>

<script>
$('#vote_form').bind('submit', function(e){
	var errorCount = 0;
	var checkedOptions = $('#options_panel input:checked');
	var passed = (checkedOptions.length > 0);
	if (!passed) {
		$('#checkedOptionsError').text('Please select at least one option.').show().fadeOut(5000);
	} else {
		$('#checkedOptionsError').hide();
	}
	return passed;
});
</script>

</c:otherwise>
</c:choose>

<div class="fb-like" data-send="false" data-width="450" data-show-faces="true" data-href="http://localhost/question.do?id=${voteTopic.encodedKey}"></div>

</div>
<jsp:include page="_footer.jsp"/>
</body>
</html>