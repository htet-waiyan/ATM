package com.team1a.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1a.biz.CustomerManager;
import com.team1a.dto.Customer;
import com.team1a.util.DuplicateKeyException;

/**
 * Servlet implementation class EditCustomerControl
 */
@WebServlet("/edit")
public class EditCustomerControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCustomerControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		String userID=(request.getParameter("userID"));
		String name=request.getParameter("txtName");
		String address=request.getParameter("txtAddr");
		String password=request.getParameter("passwd");
		
		
		System.out.println("Editing Customer");
		System.out.println(password);
		Customer c=(Customer) request.getSession(false).getAttribute("cust");
		String searchId=c.getUserId();
		c.setUserId(userID);
		c.setName(name);
		c.setAddress(address);
		c.setPassword(password);
		
		CustomerManager cm=new CustomerManager();
		
		try{
			cm.updateCustomer(c,searchId);
		}
		catch(DuplicateKeyException de){
			de.printStackTrace();
			request.setAttribute("error", "User Name Alredy Exists");
		}
		catch(SQLException e){
			e.printStackTrace();
			request.setAttribute("error", "error in editing");
		}
		
		request.getRequestDispatcher("custdetail.jsp").forward(request, response);
	}

}
