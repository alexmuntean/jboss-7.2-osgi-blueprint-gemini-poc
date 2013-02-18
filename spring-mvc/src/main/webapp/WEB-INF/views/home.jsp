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

<p>  The time on the server is ${serverTime}. </p>
<p>  The word from locals is ${messageFromLocalBean}. </p>
<p>  The word from other homies is ${messageFromOSGIService}. </p>

<h3>OSGi bundles found</h3>
<ul>
<c:forEach items="${osgibundles}" var="item">
   <li>${item}</li>
</c:forEach>
</ul>
<h3>OSGi services found</h3>
<ul>
<c:forEach items="${osgiservices}" var="item">
   <li>${item}</li>
</c:forEach>
</ul>
</body>
</html>
