package com.uttara.game;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GuessServlet
 */
public class GuessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String email;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuessServlet() {
        super();
        System.out.println("in no-arg of constr of guess");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("in doget() of GS");
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter pw=response .getWriter();
		HttpSession session=request.getSession(false);
		if(session==null)
		{
			pw.write("<html><body>Error Start the Game again <a href='HomePage.html'> Click to go back to homepage</a></body></html>");
		}
		else
		{
			String email = (String)session.getAttribute("user");
			int genVal=(Integer) session.getAttribute("genVal");
			int tries =(Integer) session.getAttribute("tries");
			
			String gNum=request.getParameter("guessNnum");
			int guessNum=Integer.parseInt(gNum);
			
			if(tries==0)
			{
				session.removeAttribute("user");
				session.removeAttribute("genVal");
				session.removeAttribute("tries");
				session.invalidate();
				pw.write("<html><body>Error Cannot attempt more than 3 times<a href='HomePage.html'> Click to go back to homepage</a></body></html>");
			}
			else
			{
				if(guessNum==genVal)
				{
					session.removeAttribute("user");
					session.removeAttribute("genVal");
					session.removeAttribute("tries");
					session.invalidate();
					
					ServletContext sc = getServletContext();
					if(sc.getAttribute("pointsMap")==null)
					{
						Map<String,Integer> pointsMap = new HashMap<String, Integer>();
						pointsMap.put(email,5);
						sc.setAttribute("pointsMap", pointsMap);
					}
					else
					{
						Map<String, Integer> pointsMap = (Map<String, Integer>) sc.getAttribute("pointsMap");
						if(pointsMap.get(email)!=null)
						{
							int val = pointsMap.get(email);
							val = val+5;
							pointsMap.put(email, val);
						}
						else
						{
							pointsMap.put(email, 5);
						}
						sc.setAttribute("pointsMap", pointsMap);
					}
					
					pw.write("<html><body>Success<a href='HomePage.html'> Click to restart</a></body></html>");	
				}
				else
				{
					if(guessNum>genVal)
					{
						pw.write("<html><body>Guess wrongly<a href='HomePage.html'> Click to restart</a></body></html>");	
					}
					tries--;
					session.setAttribute("tries", tries);
				}
			}				
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
