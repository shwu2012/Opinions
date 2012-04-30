<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset=utf-8 />
<title>Welcome new user</title>
</head>
<body>
	<form action="/register.do" method="post">
		Email: <input type="text" name="email" /><br />
		Name: <input type="text" name="name" /><br />
		Gender: <input type="radio" name="gender" value="MALE" /> Male 
		<input type="radio" name="gender" value="FEMALE" /> Female
		<input type="submit" value="Register" />
	</form>
</body>
</html>