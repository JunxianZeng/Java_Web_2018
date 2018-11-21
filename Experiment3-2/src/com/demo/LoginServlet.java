package com.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet{
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		if(userName != null && userName.equals("member") && password != null && password.equals("member01")) {
			HttpSession session =request.getSession(true);
			session.setAttribute("loggedIn", Boolean.TRUE);
					response.sendRedirect("download");
					return;
		}else {
			RequestDispatcher dispather = request.getRequestDispatcher("/login.jsp");
			dispather.forward(request, response);
		}
	}
}

