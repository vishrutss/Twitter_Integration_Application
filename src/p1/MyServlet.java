package p1;

import java.io.*;
import java.net.SocketException;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {

	private static final long serialVersionUID = 407268478043565416L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException 
	{
		PrintWriter out=response.getWriter();
		ConfigurationBuilder cz=new ConfigurationBuilder();
		cz.setDebugEnabled(true)
				.setOAuthConsumerKey("0HuwkkELYNPRySwzXWSbyEGQU")
				.setOAuthConsumerSecret("7vImC8jYWGAIIHg1TRgsvgla2c9kfikatOQJjVIzeRRPScWznD");
		TwitterFactory t=new TwitterFactory(cz.build());
		Twitter twitter=t.getInstance();
		AccessToken accessToken=null;
		try {
			
			RequestToken reqToken = (RequestToken) request.getSession().getAttribute("rtoken");
			
			while(accessToken==null)
			{
				String pin = request.getParameter("pass");
				try
				{
					if(pin.length()>0)
						accessToken=twitter.getOAuthAccessToken(reqToken,pin);
					else
						accessToken=twitter.getOAuthAccessToken(reqToken);
				}
				catch(TwitterException te)
				{
					if(te.getStatusCode()==401)
					{
						String s="PIN entered is incorrect. Please click on the link again to create a new one";
						request.getSession().setAttribute("error",s);					
						response.sendRedirect("index.jsp");
						break;
					}
					else
						out.println("Please check your Internet connection<br />If device is connected to the internet make sure the connection allows access to www.twitter.com");
				}
				catch(NullPointerException a)
				{
					String s="Please click on the link above to recieve the PIN";
					request.getSession().setAttribute("error",s);					
					response.sendRedirect("index.jsp");
					break;
				}
			}
			if(accessToken!=null)
			{
				request.getSession().setAttribute("atoken",accessToken);
				System.out.println("Login Successful");
				response.sendRedirect("select.jsp");
			}
		}
		catch(IllegalStateException ie)
		{
			ie.printStackTrace();
			if(!twitter.getAuthorization().isEnabled())
			{
				System.out.println("OAuth consumer key/secret is not set");
				System.exit(-1);
			}
		}
	}
}