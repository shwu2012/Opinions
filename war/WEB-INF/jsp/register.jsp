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
<h1>Welcome to join us!</h1>
<form:form action="/register.do" method="post" commandName="newUser">
<table class="alignment">
  <tr>
    <td>Email:</td>
    <td><form:input path="email" /></td>
    <td><form:errors path="email" cssClass="errorinfo" /></td>
  </tr>
  <tr>
    <td>Name:</td>
    <td><form:input path="name" /></td>
    <td><form:errors path="name" cssClass="errorinfo" /></td>
  </tr>
  <tr>
	<td>Gender:</td>
	<td>
	  <form:radiobutton path="gender" value="MALE" label="Male" /> 
	  <form:radiobutton path="gender" value="FEMALE" label="Female" />
	</td>
	<td><form:errors path="gender" cssClass="errorinfo" /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
	<td><input type="submit" value="Register" /></td>
  </tr>	
</table>
</form:form>
</div>
<jsp:include page="_footer.jsp"/>
</body>
</html>