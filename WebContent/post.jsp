<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Twitter Integration Application</title>
</head>
<body>
		<br><br><br>
		<h3 align="center" style="font-family:georgia;color:dodgerblue">Post a tweet (max 280 characters)</h3>
		<form action="postServlet">
			<p align="center"><textarea rows="9" cols="40" name="post"></textarea><br><br>
			<input type="submit" value="Enter" style="background-color:skyblue;border-radius:10px">
			<input type="button" value="Back" onclick="window.location='select.jsp'" onclick="<%request.getSession().setAttribute("err",null);%>" style="background-color:skyblue;border-radius:10px"></p>
			<p align="center" style="font-family:Courier;color:Red;"><%if(request.getSession().getAttribute("error1")!=null){%><%=request.getSession().getAttribute("error1")%><%}%></p>
		</form>
		<br><br><br><br><br><br><br><br><br><br><br>
		<footer><h6 align="center">You can revoke access to this application at any time from the <a href="https://twitter.com/settings/applications">Applications tab</a> of your Settings page.

<br>By authorizing this application you continue to operate under <a href="https://twitter.com/tos">Twitter's Terms of Service</a>. In particular, some usage information will be shared back with Twitter. For more, see Twitter's <a href="https://twitter.com/privacy">Privacy Policy</a>.</h6></footer>
</body>
</html>