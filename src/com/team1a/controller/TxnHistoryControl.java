package com.team1a.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1a.biz.TxnManager;
import com.team1a.dto.BankAccount;
import com.team1a.dto.Transaction;

/**
 * Servlet implementation class TxnHistoryControl
 */
@WebServlet("/txnhistory")
public class TxnHistoryControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TxnHistoryControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int accNo=Integer.parseInt(request.getParameter("accNo"));
		
//		List<Transaction> txnList=(List<Transaction>) request.getSession(false).getAttribute("accLst");
//		
//		BankAccount ba=null;
//		
//		for(int i=0;i<txnList.size();i++){
//			if(txnList.get(i).getBankaccount().getAccountNumber()==accNo){
//				ba=txnList.get(i).getBankaccount();
//			}
//		}
		
		Map<Integer, BankAccount> mapAcc=(Map<Integer, BankAccount>) request.getSession(false).getAttribute("accLst");
		
		BankAccount ba=mapAcc.get(Integer.valueOf(accNo));
		
		TxnManager tm=new TxnManager();
		
		List<Transaction> txnList=tm.getTransactionHistory(ba);
		
		request.setAttribute("txnLst", txnList);
		request.getRequestDispatcher("TransactionHistory.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int accNo=Integer.parseInt(request.getParameter("accNo"));
	}

}

