<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Options - A tiny project based on Google App Engine</title>
<script>
function endsWith(str, suffix) {
    return str.indexOf(suffix, str.length - suffix.length) !== -1;
}

function checkForm(f) {
	var msg = document.getElementById('uploadMessage');
	var filename = document.getElementById('myFile').value;
	var passed = endsWith(filename.toLowerCase(), '.jpg');
	if (!passed) {
		msg.style.color = 'red';
	}
	return passed;
}

</script>
</head>
<body>
<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>
    <form action="<%= blobstoreService.createUploadUrl("/upload") %>" method="post" enctype="multipart/form-data" onsubmit="return checkForm(this);">
        <input type="file" name="myFile" id="myFile" />
        <input type="submit" value="Submit" />
    </form>
    <div id="uploadMessage">Please upload a <b>JPG</b> image!</div>
</body>
</html>