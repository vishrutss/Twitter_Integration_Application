<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="twitter4j.conf.ConfigurationBuilder"%>
<%@page import="twitter4j.*" %>
<%@page import="twitter4j.auth.AccessToken"%>
<%@page import="twitter4j.auth.RequestToken"%>
<%@page import="java.io.PrintWriter"%>
<%	
	try{
		
 	ConfigurationBuilder cb=new ConfigurationBuilder();
	cb.setDebugEnabled(true)
			.setOAuthConsumerKey("")
			.setOAuthConsumerSecret("");
	try {
		TwitterFactory t=new TwitterFactory(cb.build());
		Twitter twitter=t.getInstance();
		try {
			RequestToken requestToken = twitter.getOAuthRequestToken();
			String a=requestToken.getAuthorizationURL();
			request.getSession().setAttribute("link",a);
			request.getSession().setAttribute("rtoken",requestToken);
		}
		catch(IllegalStateException ie)
		{
			if(!twitter.getAuthorization().isEnabled())
			{
				System.exit(-1);
			}
		}
	}
	catch(Exception te)
	{
		response.sendRedirect("error.jsp");
		te.printStackTrace();
	}
}
catch(Exception e)
{
	e.printStackTrace();
	out.print("Consumer keys are wrong");
}
%>
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
		#bottom { bottom: 0; }</style>
<body>
<p align="center"><img src="twitter.jpg"></p>
<h1 align="center" style="font-family:impact;color:dodgerblue">Twitter Integration Application</h1>
	<form action="MyServlet">
		<p align="center" style="font-family:arial;">Please click on the Link provided below to log into twitter and obtain the PIN<br><br>
		Link: <input type="button" value="click here" onclick="window.open('<%=request.getSession().getAttribute("link")%>')" style="background-color:skyblue;border-radius:10px"><br><br>
		Enter PIN: <input type="password" name="pass" style="border-radius:10px">
		<input type="submit" value="Enter" onclick="<%request.getSession().setAttribute("err",null);%>" style="background-color:skyblue;border-radius:10px"><br></p>
		<p align="center" style="font-family:Courier;color:Red;"><%if(request.getSession().getAttribute("error")!=null){%><%=request.getSession().getAttribute("error")%><%}%></p>		
		</form>
		<br><br><br><br><br><br><br><br><br><br><br><br>
		<footer><h6 align="center">You can revoke access to this application at any time from the <a href="https://twitter.com/settings/applications">Applications tab</a> of your Settings page.

<br>By authorizing this application you continue to operate under <a href="https://twitter.com/tos">Twitter's Terms of Service</a>. In particular, some usage information will be shared back with Twitter. For more, see Twitter's <a href="https://twitter.com/privacy">Privacy Policy</a>.</h6></footer>
</body>
</html>
