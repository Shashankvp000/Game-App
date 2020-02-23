package com.uttara.game;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StartGameServlet
 */
public class StartGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public StartGameServlet() {
    	super();
    	System.out.println("in SGS no arg-constr");
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in doGet() of SGS");
		
		String email=request.getParameter("email");
		PrintWriter pw=response.getWriter();
		if(email==null || email.equals("") || !email.contains("@"))
			pw.write("<html><body>Error</body></html>");
		else
		{
			//email input is valid
			int val=(int)(Math.random()*10);
			System.out.println("gen Val="+val);
			int tries=3;
			
			//ask WS to create session for client
			HttpSession session=request.getSession(true);
			session.setAttribute("user",email);
			session.setAttribute("genVal",email);
			session.setAttribute("tries",email);
			
			//forward
			RequestDispatcher rd=request.getRequestDispatcher("Guess.html");
			rd.forward(request, response);
		}
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
