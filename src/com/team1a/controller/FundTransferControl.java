package com.team1a.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1a.biz.OverDrafLimitExceededException;
import com.team1a.biz.TxnManager;
import com.team1a.dto.BankAccount;
import com.team1a.dto.Transaction;
import com.team1a.util.BelowMinimumBalanceException;
import com.team1a.util.ExceedLimitException;
import com.team1a.util.NoAccountFound;
import com.team1a.util.NotEnoughCashInBankException;
import com.team1a.util.TransactionAbortedException;

/**
 * Servlet implementation class FundTransferControl
 */
@WebServlet("/transfer")
public class FundTransferControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FundTransferControl() {
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
		String type = request.getParameter("type");
		int fromAcc = Integer.parseInt(request.getParameter("fromAcc").trim());
		int toAcc = Integer.parseInt(request.getParameter("toAcc").trim());
		//double amount = Double.parseDouble(request.getParameter("amt").trim());
		BigDecimal amount=new BigDecimal(Double.parseDouble(request.getParameter("amt").trim()));
		
		
		TxnManager tm = new TxnManager();

		BankAccount ba = (BankAccount) request.getSession(false).getAttribute(
				"txnAcc");

		Transaction txn = null;

		try {
			txn = new Transaction();
			txn.setBankaccount(ba);
			txn.setToAccount(toAcc);
			txn.setTxnAmount(amount);

			Calendar txnTime = Calendar.getInstance();
			SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			try {
				txn.setTxnTime(fmt.parse(fmt.format(txnTime.getTime())));
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			txn.setTxnType("Transfer");
			tm.transferTo(ba, txn);

//			((Map<Integer, BankAccount>) request.getSession(false)
//					.getAttribute("accLst")).put(ba.getAccountNumber(), ba);
			
			Map<Integer, BankAccount> accLst=(Map<Integer, BankAccount>) request.getSession(false).getAttribute("accLst");
			accLst.put(ba.getAccountNumber(), ba);
			
//			BankAccount accTo=accLst.get(txn.getToAccount());
//			accLst.put(txn.getToAccount(), accTo);
			
			request.setAttribute("txn", txn);
			request.getRequestDispatcher("message.jsp")
					.forward(request, response);

		} catch (NoAccountFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("type", type);
			request.setAttribute("fromAcc", fromAcc);
			request.setAttribute("toAcc", toAcc);
			request.setAttribute("error", e.getMessage());
//			request.setAttribute("txn", txn);
//			request.setAttribute("txnMsg",
//					"The Following are transaction details.");

			request.getRequestDispatcher("transfer.jsp")
					.forward(request, response);

		} catch (ExceedLimitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			request.setAttribute("error", e.getMessage());
			request.setAttribute("type", type);
			request.setAttribute("fromAcc", fromAcc);
			request.setAttribute("toAcc", toAcc);
			request.getRequestDispatcher("transfer.jsp")
					.forward(request, response);
		} catch (NotEnoughCashInBankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			request.setAttribute("error", e.getMessage());
			request.setAttribute("type", type);
			request.setAttribute("fromAcc", fromAcc);
			request.setAttribute("toAcc", toAcc);
			request.getRequestDispatcher("transfer.jsp")
					.forward(request, response);
		}
	}
}
