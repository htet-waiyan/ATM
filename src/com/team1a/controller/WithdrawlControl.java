package com.team1a.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1a.biz.TxnManager;
import com.team1a.dto.BankAccount;
import com.team1a.dto.Transaction;
import com.team1a.util.BelowMinimumBalanceException;
import com.team1a.util.ExceedLimitException;
import com.team1a.util.NotEnoughCashInBankException;

/**
 * Servlet implementation class WithdrawlControl
 */
@WebServlet("/cashwithdrawl")
public class WithdrawlControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WithdrawlControl() {
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
		String type=request.getParameter("type");
		int accNo=Integer.parseInt(request.getParameter("fromAcc"));
		double amount=Double.parseDouble(request.getParameter("amount"));
		
		BankAccount ba=(BankAccount) request.getSession(false).getAttribute("txnAcc");
		Transaction txn=new Transaction();
		txn.setBankaccount(ba);
		txn.setToAccount(ba.getAccountNumber());
		txn.setTxnAmount(new BigDecimal(amount));
		txn.setTxnTime(Calendar.getInstance().getTime());
		txn.setTxnType("Withdraw");
		
		TxnManager tm=new TxnManager();
		
		try{
			tm.withdraw(txn);
			request.setAttribute("txn", txn);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
		catch(NotEnoughCashInBankException ne){
			ne.printStackTrace();
			request.setAttribute("error", ne.getMessage());
			request.setAttribute("type", type);
			request.setAttribute("fromAcc", accNo);
			request.getRequestDispatcher("withdraw.jsp").forward(request, response);
		}
		catch(ExceedLimitException ee){
			ee.printStackTrace();
			
			request.setAttribute("error", ee.getMessage());
			request.setAttribute("type", type);
			request.setAttribute("fromAcc", accNo);
			request.getRequestDispatcher("withdraw.jsp").forward(request, response);
			//request.getRequestDispatcher("customer/withdraw_body.jsp").forward(request, response);
		}
	}

}
