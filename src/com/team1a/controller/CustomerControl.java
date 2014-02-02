package com.team1a.controller;

import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team1a.biz.CustomerManager;
import com.team1a.dto.Customer;
import com.team1a.util.DuplicateKeyException;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/customer")
public class CustomerControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		CustomerManager custManager=new CustomerManager();
		RequestDispatcher dispatcher=null;
		
		HttpSession session=request.getSession();
		
		if(action.equalsIgnoreCase("register"))
			dispatcher=request.getRequestDispatcher(registerCustomer(request, response, custManager));
		
		else if(action.equalsIgnoreCase("edit")){
			
		}
		else
			dispatcher=request.getRequestDispatcher("custdetail.jsp");
		
		dispatcher.forward(request, response);
		
		
		//private Date joinDate
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=req.getParameter("action");
		CustomerManager cm=new CustomerManager();
		RequestDispatcher dispatcher=req.getRequestDispatcher("custdetail.jsp");
		
		if(action.equalsIgnoreCase("edit")){
			editCustomer(req, resp, cm);
		}
			
		dispatcher.forward(req, resp);
	}
	private void editCustomer(HttpServletRequest request,HttpServletResponse response, CustomerManager cm)throws ServletException,IOException{
		String userID=(request.getParameter("userID"));
		String name=request.getParameter("cusname");
		String address=request.getParameter("address");
		String password=request.getParameter("passwd");
		
		System.out.println("Editing Customer");
		Customer c=(Customer) request.getSession(false).getAttribute("cust");
		String searchId=c.getUserId();
		c.setUserId(userID);
		c.setName(name);
		c.setAddress(address);
		c.setPassword(password);
		
		try{
			cm.updateCustomer(c,searchId);
		}
		catch(DuplicateKeyException de){
			de.printStackTrace();
			request.setAttribute("error", de.getMessage());
		}
		catch(SQLException e){
			e.printStackTrace();
			request.setAttribute("error", "error in editing");
		}
	}
	private String registerCustomer(HttpServletRequest request,HttpServletResponse response,CustomerManager custManager) throws ServletException, IOException{
		String userID=(request.getParameter("userID"));
		String nric=request.getParameter("NRIC");
		String name=request.getParameter("cusname");
		String address=request.getParameter("address");
		String dob=request.getParameter("dob");
		String nationality=request.getParameter("nationality");
		String password=request.getParameter("passwd");
		
		//date of join
		String doj=request.getParameter("doj");
		String gender=request.getParameter("gender");
		
		System.out.println(doj);
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
		Date birthDate=null;
		Date joinDate=null;
		try {
			joinDate=format.parse(doj);
			birthDate = format.parse(dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		Customer customer=new Customer();
		customer.setUserId(userID);
		customer.setNric(nric);
		customer.setName(name);
		customer.setGender(gender);
		customer.setAddress(address);
		customer.setBirthdate(birthDate);
		customer.setNationality(nationality);
		customer.setJoinDate(joinDate);
		customer.setPassword(password);
		
		int rows=0;
		try {
			rows = custManager.registerCustomer(customer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error_msg", "Duplicate customer id");
			return "register.jsp";
		}
		
		if(rows>0){
			request.getSession(false).setAttribute("cust", customer);
			return "custdetail.jsp";
		}
		else
			return "register.jsp";
	}
	
//	private String doEdit(HttpServletRequest request,HttpServletResponse response, CustomerManager cm)throws ServletException,IOException{
//		String userID=(request.getParameter("userID"));
//		String name=request.getParameter("cusname");
//		String address=request.getParameter("address");
//		String password=request.getParameter("passwd");
//		
//		Customer c=(Customer) request.getSession(false).getAttribute("cust");
//		c.setUserId(userID);
//		c.setName(name);
//		c.setAddress(address);
//		c.setPassword(password);
//		
//		try{
//			//cm.updateCustomer(c);
//		}
//		catch(SQLException e){
//			request.setAttribute("error_msg", "cannot update");
//			e.printStackTrace();
//			
//			return "custdetail.jsp";
//		}
//		
//		return "custdetail.jsp";
//	}
	
	private String  doDelete(HttpServletRequest request,HttpServletResponse response, CustomerManager cm)throws ServletException,IOException{
		return "custdetail.jsp";
	}

}
