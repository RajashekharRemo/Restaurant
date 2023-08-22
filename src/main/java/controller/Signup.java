package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.Mydao;
import dto.Customer;

//this is to map the action URL to this class(should be same as action - case sensitive)
@WebServlet("/signup")
@MultipartConfig
public class Signup extends HttpServlet {

	// when there is form and we want data to be secured so doPost
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// logoc to receive values from front end

		String name = req.getParameter("fullname");
		String email = req.getParameter("email");
		long num = Long.parseLong(req.getParameter("num"));
		String pswd = req.getParameter("psw");
	    LocalDate	dob=LocalDate.parse(req.getParameter("dob"));
		String gender = req.getParameter("Gender");
		String city = req.getParameter("city");
		String agreebox = req.getParameter("agreebox");
		int age= Period.between(dob, LocalDate.now()).getYears();
		
		
		//to get image from the ui or signup html and convert to byte
	    Part image=req.getPart("image");
	    byte[] picture=null;
	    picture=new byte[image.getInputStream().available()];
	    image.getInputStream().read(picture);

		
		
		System.out.println(name);
		System.out.println(email);
		System.out.println(num);
		System.out.println(pswd);
		System.out.println(gender);
		System.out.println(city);
		System.out.println(agreebox);
		System.out.println(dob);
		System.out.println(age);
		
		
		Mydao dao= new Mydao();
		
		
		
		// login to verify email and mobile number is not repeated
		if(dao.fetchByEmail(email)==null && dao.fetchByMobile(num)==null) {
		
		// loading values inside object 
		Customer c=new Customer();
		c.setFullname(name);
		c.setEmail(email);
		c.setNum(num);
		c.setPswd(pswd);
		c.setDob(dob);
		c.setGender(gender);
		c.setCity(city);
		c.setAgreebox(agreebox);
		c.setAge(age);
		c.setImage(picture);
		
		
		dao.save(c);
		
		resp.getWriter().print("<h1 style='color:green'> Account created successfully</h1> ");
		req.getRequestDispatcher("login.html").include(req, resp);
		
		}else {
			resp.getWriter().print("<h1>this email or number is already present");
			req.getRequestDispatcher("signup.html").include(req, resp);
		}
		
	}
}
