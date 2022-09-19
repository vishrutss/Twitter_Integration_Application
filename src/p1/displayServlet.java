package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class displayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		int i=0;
		AccessToken accessToken = (AccessToken) request.getSession().getAttribute("atoken");
		try
		{
		ConfigurationBuilder cf=new ConfigurationBuilder();
		cf.setDebugEnabled(true)
				.setOAuthConsumerKey("0HuwkkELYNPRySwzXWSbyEGQU")
				.setOAuthConsumerSecret("7vImC8jYWGAIIHg1TRgsvgla2c9kfikatOQJjVIzeRRPScWznD")
				.setOAuthAccessToken(accessToken.getToken())
				.setOAuthAccessTokenSecret(accessToken.getTokenSecret());
		TwitterFactory tf=new TwitterFactory(cf.build());
		String q=request.getParameter("option");
		if("Display".equals(q))
		{
			twitter4j.Twitter tw=tf.getInstance();
			List<Status> status=tw.getHomeTimeline();
			out.print("<style>\r\n" + 
					"#top, #bottom, #left, #right {\r\n" + 
					"	background: white;\r\n" + 
					"	position: fixed;\r\n" + 
					"	}\r\n" + 
					"	#left, #right{\r\n" + 
					"		top: 0; bottom: 0;\r\n" + 
					"		}\r\n" + 
					"		#left{ left: 0; }\r\n" + 
					"		#right { right: 0; }\r\n" + 
					"	#top, #bottom{\r\n" + 
					"		left: 0; right: 0;\r\n" + 
					"		}\r\n" + 
					"		#top{ top: 0; }\r\n" + 
					"		#bottom { bottom: 0; }</style>\r\n" + 
					"<body>\r\n" + 
					"<div id=\"left\"></div>\r\n" + 
					"<div id=\"right\"></div>\r\n" + 
					"<div id=\"top\"><h2 align=\"center\" style=\"font-family:georgia;color:blue\">Timeline</h2></div>\r\n" + 
					"<div id=\"bottom\"></div><br><br><br><br>");
			out.print("<style>\r\n" + 
					"table.roundedCorners{\r\n" + 
					"	border: 1px solid black;\r\n" + 
					"	border-radius: 13px;\r\n" + 
					"	border-spacing: 0;\r\n" + 
					"}\r\n" + 
					"table.roundedCorners td,\r\n" + 
					"table.roundedCorners th {\r\n" + 
					"	border-bottom: 1px solid black;\r\n" + 
					"	padding: 10px;\r\n" + 
					"}\r\n" + 
					"table.roundedCorners tr:last-child>td{\r\n" + 
					"	border-bottom: none;\r\n" + 
					"}\r\n" + 
					"</style>");
			out.print("<table class=\"roundedCorners\">");
	        out.print("<tr style=\\\"font-family:georgia;color:blue><th>No.</th><th>User</th><th>Tweet</th>"); 
			for(Status st : status)
			{
                i++;
				out.print("<tr><td>"+i+"."+"</td><td>"+st.getUser().getName()+"</td><td>"+st.getText()+"</td></tr>");  
			}
			request.getSession().setAttribute("err",null);
			out.print("</table><br />");	  
			out.print("<p align=\"center\"><input type=button value=Exit onclick=window.location='select.jsp' style=\"background-color:skyblue;border-radius:10px\"></p>");	
			out.print("<footer><h6 align=\"center\">You can revoke access to this application at any time from the <a href=\"https://twitter.com/settings/applications\">Applications tab</a> of your Settings page.\r\n" + 
					"\r\n" + 
					"<br>By authorizing this application you continue to operate under <a href=\"https://twitter.com/tos\">Twitter's Terms of Service</a>. In particular, some usage information will be shared back with Twitter. For more, see Twitter's <a href=\"https://twitter.com/privacy\">Privacy Policy</a>.</h6></footer>");
		}
		else if("Search".equals(q))
		{
			response.sendRedirect("search.jsp");
		}
		else if("Post".equals(q))
		{
			response.sendRedirect("post.jsp");
		}
		else
		{
			String s2="Please select an option";
			request.getSession().setAttribute("err",s2);					
			response.sendRedirect("select.jsp");
		}
		}
		catch(TwitterException te)
		{
			te.printStackTrace();
			System.out.println("Failed to get timeline: "+te.getMessage());
			System.exit(-1);
		}
	}
}
