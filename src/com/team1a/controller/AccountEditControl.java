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
 * Servlet implementation class AccountEditControl
 */
@WebServlet("/editacc")
public class AccountEditControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountEditControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type=request.getParameter("type");
		int accNo=Integer.parseInt(request.getParameter("accNo"));
		int pin=Integer.parseInt(request.getParameter("pin"));
		double amt=Double.parseDouble(request.getParameter("amt"));
		
		BankAccountManager bm=new BankAccountManager();
		
		if(type.equalsIgnoreCase("Checking"))
			bm.updateCheckingAccount(pin, new BigDecimal(amt), accNo);
		
		else
			bm.updateSavingAccount(pin, new BigDecimal(amt), accNo);
		
		request.getRequestDispatcher("custdetail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type=request.getParameter("type");
		int accNo=Integer.parseInt(request.getParameter("accNo"));
		int pin=Integer.parseInt(request.getParameter("pin"));
		double amt=Double.parseDouble(request.getParameter("amt"));
		
		BankAccountManager bm=new BankAccountManager();
		
		if(type.equalsIgnoreCase("Checking"))
			bm.updateCheckingAccount(pin, new BigDecimal(amt), accNo);
		
		else
			bm.updateSavingAccount(pin, new BigDecimal(amt), accNo);
		
		request.getRequestDispatcher("custdetail.jsp").forward(request, response);
	}

}
