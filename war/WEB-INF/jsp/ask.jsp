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
<script type="text/javascript" src="js/jquery.1.7.2.min.js"></script>
<script>
$(document).ready(function() {

	$('#options_panel a').bind('click', function(e){
		if ($('#options_panel div').length > 2) {
			$(this).parent().remove();
		} else {
			window.alert('You must have at least 2 options.');
		}
		return false;
	});
	
	$('#add_option_link').bind('click', function(e){
		var textbox = $('#options_panel div:first-child').clone(true);
		textbox.children('input').attr('value', '');
		$('#options_panel').append(textbox);
		return false;
	});
	
	$('#ask_form').bind('submit', function(e){
		var errorCount = 0;
		$('#ask_form input[type="text"]').map(function (index) {
            if ($.trim($(this).attr('value')) == '') {
            	$(this).attr('value', '');
            	$(this).siblings('span.errorinfo').text('Cannot be empty').show().fadeOut(5000);
            	errorCount++;
            } else {
            	$(this).siblings('span.errorinfo').hide();
            }
        });
		return (errorCount == 0);
	});
});

function setBlobKeyFromUploader(imageId) {
	$('#ask_form input[name="imageBlobId"]').attr('value', imageId);
	$('img#question_image').attr('src', 'serve?blob-key=' + imageId);
}

</script>
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="content">
<h1>Ask a question?</h1>

<h2>Step 1: Upload a image</h2>
<iframe src="/upload.jsp" frameborder="0" width="600" height="75"></iframe>

<h2>Step 2: Fill in your topic and options</h2>
<form action="/ask.do" method="post" id="ask_form">
<input type="hidden" name="imageBlobId" value="" />
<table class="alignment">
  <tr>
    <th>Title:</th>
    <td><input type="text" name="title" size="40" /> <span class="errorinfo"></span></td>
  </tr>
  <tr>
    <th>Image:</th>
    <td><img id="question_image" width="200" border="0" src="/serve" /></td>
  </tr>
  <tr>
    <th>Description:</th>
    <td><textarea name="description" rows="5" cols="35"></textarea> <span class="errorinfo"></span></td>
  </tr>
  <tr>
    <th>Options:<br/><a id="add_option_link" href="#">Add option</a></th>
    <td id="options_panel">
      <div><input type="text" name="options" size="40" /> <a href="#">Remove</a> <span class="errorinfo"></span></div>
      <div><input type="text" name="options" size="40" /> <a href="#">Remove</a> <span class="errorinfo"></span></div>
    </td>
  </tr>
  <tr>
    <th>&nbsp;</th>
    <td><input type="submit" value="Ask" /></td>
  </tr>
</table>
</form>
</div>
<jsp:include page="_footer.jsp"/>
</body>
</html>