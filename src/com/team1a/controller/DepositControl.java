package com.team1a.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1a.biz.TxnManager;
import com.team1a.dto.BankAccount;
import com.team1a.dto.Transaction;

/**
 * Servlet implementation class DepositControl
 */
@WebServlet("/cashdeposit")
public class DepositControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepositControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String accType=request.getParameter("type");
		int accNo=Integer.parseInt(request.getParameter("fromAcc"));
		double amount=Double.parseDouble(request.getParameter("amount"));
		
		BankAccount ba=(BankAccount) request.getSession(false).getAttribute("txnAcc");
		TxnManager tm=new TxnManager();
		
		Transaction txn=new Transaction();
		txn.setBankaccount(ba);
		txn.setTxnAmount(new BigDecimal(amount));
		txn.setTxnType("Deposit");
		
		txn.setTxnTime(Calendar.getInstance().getTime());
		
		tm.deposit(txn);
		
		request.setAttribute("txn", txn);
		request.getRequestDispatcher("message.jsp").forward(request, response);
	}
}
