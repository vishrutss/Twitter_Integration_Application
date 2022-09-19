<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="twitter4j.conf.ConfigurationBuilder"%>
<%@page import="twitter4j.*" %>
<%@page import="twitter4j.auth.AccessToken"%>
<%@page import="twitter4j.auth.RequestToken"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.io.PrintWriter"%>

<!doctype html>
<html>
<head>
<style>
	#top, #bottom, #left, #right {
		background: white; 
		position: fixed; 
		}
		#left, #right{
			top: 0; bottom: 0; 
			}
			#left{ left: 0; } 
			#right { right: 0; } 
		#top, #bottom{
			left: 0; right: 0; 
			}
			#top{ top: 0; } 
			#bottom { bottom: 0; }
</style>
<style>
#paging {
  padding: 0 20px 20px 20px;
  font-size: 13px;
  margin-top: 10px;
}
 
#paging a {
  color: #000;
  background: #33EEFF;
  padding: 8px 12px;
  margin-right: 5px;
  text-decoration: none;
}
 
#paging a.aktif {
  background: #000 !important;
  color: #fff;
}
 
#paging a:hover {
  border: 1px solid #000;
}
 
.hidden {
  display: none;
}
</style>
<meta charset="utf-16">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Twitter integration application</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed" rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="PagingStyle.css" />
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="paging.js"></script>
    <script type="text/javascript">
      $(function() {
        $("#listPage").JPaging({
          pageSize: 10
        });
      });
    </script>
    <style>
    body {
      font-family: 'Roboto Condensed';
      background-color: #fafafa;
}
h1 { text-align: center; }
  </style>
  </head>
  <body>
  	<div id="left"></div> 
	<div id="right"></div> 
	<div id="top"><h1>Showing results for "<%=request.getParameter("search")%>"</h1></div> 
	<div id="bottom"></div>
	<br><br><br>
    <div><ul id="listPage">
		<%int i=0; 
		List<Status> x=(ArrayList<Status>)request.getSession().getAttribute("tw");
			for (Status tweet : x) {
			i++;
		    out.print("<li><p style=\"color:blue\">"+i+") @"+tweet.getUser().getScreenName()+"--></p>"+tweet.getText()+"</li>");  
		}%>
      </ul>
      
    <script "type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36251023-1']);
  _gaq.push(['_setDomainName', 'jqueryscript.net']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
	<p align="center"><input type=button value=exit onclick="window.location='search.jsp'" style="background-color:skyblue;border-radius:10px"></p>
  </div>
  </body>
  <br>
  <footer><h6 align="center">You can revoke access to this application at any time from the <a href="https://twitter.com/settings/applications">Applications tab</a> of your Settings page.

<br>By authorizing this application you continue to operate under <a href="https://twitter.com/tos">Twitter's Terms of Service</a>. In particular, some usage information will be shared back with Twitter. For more, see Twitter's <a href="https://twitter.com/privacy">Privacy Policy</a>.</h6></footer>
</html>