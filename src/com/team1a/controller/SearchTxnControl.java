package com.team1a.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1a.biz.TxnManager;
import com.team1a.dto.Transaction;

/**
 * Servlet implementation class SearchTxnControl
 */
@WebServlet("/searchtxn")
public class SearchTxnControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchTxnControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("txnhistory.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int no=Integer.parseInt(request.getParameter("accNo"));
		
		TxnManager tm=new TxnManager();
		List<Transaction> txLst=tm.getTransactionHistory(no);
		
		
		
		System.out.println(txLst.get(1).getTxnType());
		request.setAttribute("txnLst", txLst);
		request.getRequestDispatcher("txnhistory.jsp").forward(request, response);
	}

}
