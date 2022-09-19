<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
			<p align="center">Please check your Internet connection<br /><br />If device is connected to the internet make sure the connection allows access to api.twitter.com<br /><br />Click on the link below to go to the homepage of Twitter integration application<br /><br />
			<input type="button" value="Try again" onclick="window.location='index.jsp'" onclick="<%request.getSession().setAttribute("error",null);%>" style="background-color:skyblue;border-radius:10px"></p>
</body>
</html>