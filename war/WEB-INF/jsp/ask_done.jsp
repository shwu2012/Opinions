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
<div id="fb-root"></div>
<script>
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '${fbApiKey}', // App ID
      channelUrl : '//localhost:8888/channel.html', // Channel File
      status     : true, // check login status
      cookie     : true, // enable cookies to allow the server to access the session
      xfbml      : true  // parse XFBML
    });

    // Additional initialization code here
  };

  // Load the SDK Asynchronously
  (function(d){
     var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement('script'); js.id = id; js.async = true;
     js.src = "//connect.facebook.net/en_US/all.js";
     ref.parentNode.insertBefore(js, ref);
   }(document));
</script>


<jsp:include page="_header.jsp"/>
<div class="content">
<h1>New question is published!</h1>

<h2>Step3: Share with your friends on Facebook</h2>

<fb:like send="true" width="450" show_faces="true" />

<!-- <button onclick="sendRequestViaMultiFriendSelector();">Share with friends</button> -->
<ul>
  <li><a href="/question.do?id=${voteTopic.encodedKey}&view=true">Have a look at this hot question!</a></li>
</ul>
</div>
<jsp:include page="_footer.jsp"/>
</body>
</html>