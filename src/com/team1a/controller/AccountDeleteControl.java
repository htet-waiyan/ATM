package com.team1a.controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1a.biz.BankAccountManager;
import com.team1a.dto.Customer;

/**
 * Servlet implementation class AccountDeleteControl
 */
@WebServlet("/deleteacc")
public class AccountDeleteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountDeleteControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Customer c = (Customer) request.getSession(false).getAttribute("cust");
		String type = request.getParameter("type");
		int accNo = Integer.parseInt(request.getParameter("acc"));
		
		BankAccountManager bm=new BankAccountManager();
		
		if(type.equalsIgnoreCase("Checking"))
			bm.deleteCheckingAccount(accNo);
		
		else
			bm.deleteSavingAccount(accNo);
		
		for (int i = 0; i < c.getAccountList().size(); i++) {
			if (c.getAccountList().get(i).getAccountNumber() == accNo)
				c.getAccountList().remove(i);
		}

		request.getRequestDispatcher("custdetail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
