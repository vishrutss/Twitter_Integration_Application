package p1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;


public class postServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String s1;
		AccessToken accessToken = (AccessToken) request.getSession().getAttribute("atoken");
		ConfigurationBuilder cf=new ConfigurationBuilder();
		cf.setDebugEnabled(true)
				.setOAuthConsumerKey("0HuwkkELYNPRySwzXWSbyEGQU")
				.setOAuthConsumerSecret("7vImC8jYWGAIIHg1TRgsvgla2c9kfikatOQJjVIzeRRPScWznD")
				.setOAuthAccessToken(accessToken.getToken())
				.setOAuthAccessTokenSecret(accessToken.getTokenSecret());
		Twitter tf = new TwitterFactory(cf.build()).getInstance();
	    String qu=request.getParameter("post");
		
		try {
			if(qu.length()==0)
			{
				s1="Please enter a tweet";
				request.getSession().setAttribute("error1",s1);
				response.sendRedirect("post.jsp");
			}
			else if(qu.length()<=280)
			{
				tf.updateStatus(qu);
				out.println("Successfully updated the status in Twitter.");
			}
			else
			{
				s1="Number of characters has exceeded the limit";
				request.getSession().setAttribute("error1",s1);					
				response.sendRedirect("post.jsp");
			}
		}
		catch (TwitterException te) {
			te.printStackTrace();
		}
	}
}
