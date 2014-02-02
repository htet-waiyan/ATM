package com.team1a.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1a.biz.BankAccountManager;
import com.team1a.biz.CustomerManager;
import com.team1a.dto.BankAccount;
import com.team1a.dto.Customer;

/**
 * Servlet implementation class CustomerSearchControl
 */
@WebServlet("/custsearch")
public class CustomerSearchControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerSearchControl() {
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
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("txtSearch");

		CustomerManager cm = new CustomerManager();
		Customer c = cm.getCustomerById(query);

		BankAccountManager bm = new BankAccountManager();
		Map<Integer, BankAccount> accLst = bm.getAllAccountsOf(query);

		if (c == null || accLst == null) {
			request.setAttribute("error", "No Customer Found");
			request.getRequestDispatcher("custdetail.jsp").forward(request,
					response);
		} else {
			c.setAccountList(new ArrayList<>(accLst.values()));
			request.getSession(false).setAttribute("cust", c);
			request.setAttribute("accList", accLst);
			request.getRequestDispatcher("custdetail.jsp").forward(request,
					response);
		}
	}
}
