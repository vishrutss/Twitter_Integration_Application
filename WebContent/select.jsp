<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Twitter Integration Application</title>
</head>
<style>
#top, #bottom, #left, #right {
	background: #a5ebff;
	position: fixed;
	}
	#left, #right{
		top: 0; bottom: 0;
		width: 15px;
		}
		#left{ left: 0; }
		#right { right: 0; }
	#top, #bottom{
		left: 0; right: 0;
		height: 15px;
		}
		#top{ top: 0; }
		#bottom { bottom: 0; }
</style>
<body>
<br><br><br>
	<h2  align="center" style="font-family:impact;color:dodgerblue">Please select one of the following options</h2>
		<form action="displayServlet">
			<p align="center" style="font-family:georgia"><input type="radio" name="option" value="Display">Display current timeline</p>
			<p align="center" style="font-family:georgia"><input type="radio" name="option" value="Search">Search for tweets</p>
			<p align="center" style="font-family:georgia"><input type="radio" name="option" value="Post">Post a tweet<br><br>
			<input type="submit" value="Enter" onclick="<%request.getSession().setAttribute("error2",null);%>" onclick="<%request.getSession().setAttribute("error1",null);%>" style="background-color:skyblue;border-radius:10px">
			<input type="button" value="Exit" onclick="window.location='index.jsp'" onclick="<%request.getSession().setAttribute("error",null);%>" style="background-color:skyblue;border-radius:10px"></p>
			<p align="center" style="font-family:Courier;color:Red;"><%if(request.getSession().getAttribute("err")!=null){%><%=request.getSession().getAttribute("err")%><%}%></p>
		</form>
		<br><br><br><br><br><br><br><br><br><br><br><br><br>
		<footer><h6 align="center">You can revoke access to this application at any time from the <a href="https://twitter.com/settings/applications">Applications tab</a> of your Settings page.

<br>By authorizing this application you continue to operate under <a href="https://twitter.com/tos">Twitter's Terms of Service</a>. In particular, some usage information will be shared back with Twitter. For more, see Twitter's <a href="https://twitter.com/privacy">Privacy Policy</a>.</h6></footer>
</body>
</html>