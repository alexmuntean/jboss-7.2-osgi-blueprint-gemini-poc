<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<P>  The word from locals is ${messageFromLocalBean}. </P>
<P>  The word from other homies is ${messageFromOSGIService}. </P>
</body>
</html>
