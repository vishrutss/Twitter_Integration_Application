package p1;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.*;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;


public class searchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		List <Status> tweets=new ArrayList<Status>();
		int count=1,i=0;
		String s2;
		AccessToken accessToken = (AccessToken) request.getSession().getAttribute("atoken");
		ConfigurationBuilder cf=new ConfigurationBuilder();
		cf.setDebugEnabled(true)
				.setOAuthConsumerKey("0HuwkkELYNPRySwzXWSbyEGQU")
				.setOAuthConsumerSecret("7vImC8jYWGAIIHg1TRgsvgla2c9kfikatOQJjVIzeRRPScWznD")
				.setOAuthAccessToken(accessToken.getToken())
				.setOAuthAccessTokenSecret(accessToken.getTokenSecret());
		Twitter tf = new TwitterFactory(cf.build()).getInstance();
	    String qu=request.getParameter("search");	  
	    
	    try {
	    	if(qu=="")
        	{
        		s2="Search parameter missing";
				request.getSession().setAttribute("error2",s2);					
				response.sendRedirect("search.jsp");
        	}
	    	else
	    	{
	    		s2=null;
				request.getSession().setAttribute("error2",s2);	
		    	Query query = new Query(qu);
		        QueryResult result;
		      do {   	
		            result = tf.search(query);
		            tweets.addAll(result.getTweets());
		            count++;
		          if(count>15)
		           	break;
		      	} while ((query = result.nextQuery()) != null);
		      	request.getSession().setAttribute("tw",tweets);
		      	request.getRequestDispatcher("pages.jsp").forward(request, response);
	            //out.print("<h3 style=\"font-family:georgia;color:dodgerblue\"> Showing results for \""+qu+"\"</h3><h3 align=\"right\" style=\"font-family:georgia;color:Blue\">Page No: "+count+"</h3>");
	            //out.print("<table class=\"roundedCorners\">");
	            //out.print("<tr style=\"font-family:Times;color:Blue\"><th>No.</th><th>User</th><th>Tweet</th>");  
	           // for (Status tweet : tweets) {
	            //	i++;
	              //  out.print("<tr><td>"+i+"</td><td>"+tweet.getUser().getScreenName()+"</td><td>"+tweet.getText()+"</td></tr>");  
	            //}
	            //out.print("</table><br />");	
	            //out.print("<p align=\"center\"><input type=button value=exit onclick=window.location='search.jsp' style=\"background-color:skyblue;border-radius:10px\"></p>");
		        if(count==2)
		        {
		        	s2="No tweets for the topic found";
					request.getSession().setAttribute("error2",s2);					
					response.sendRedirect("search.jsp");
		        }
		       // else
		        //	response.sendRedirect("pages.jsp");
	    	}
	    } catch (TwitterException te) {
	        te.printStackTrace();
	        out.println("Failed to search tweets: " + te.getMessage());
	        System.exit(-1);
	    }
	}
}	





