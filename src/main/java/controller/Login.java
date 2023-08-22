package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Mydao;
import dto.Customer;

@WebServlet("/login")
public class Login extends HttpServlet
{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String email = req.getParameter("mail");
	String pswd = req.getParameter("paswa");
	System.out.println(email);
	System.out.println(pswd);
	
	// verify if mail exist or not
	
	Mydao dao= new Mydao();
	Customer cust=dao.fetchByEmail(email);
	if (cust==null) {
		resp.getWriter().print("<h1>invalid email<h1/>");
		req.getRequestDispatcher("login.html").include(req, resp);
		// req.getRequestDispatcher("login.html").forward(req, resp);
		// it will not consider the previous getWriter method , direcltly enter into the next page
		
	}else {
		if(pswd.equals(cust.getPswd())) {
			// if response should be both text and html
			// resp.setContentTpye("text/html");
			resp.getWriter().print("<h1>Login success<h1/>");
			req.getRequestDispatcher("update.html").include(req, resp);
		}else {
			resp.getWriter().print("<h1>Invalid Password<h1/>");
			req.getRequestDispatcher("login.html").include(req, resp);
			
		}
	}
	
}
}
