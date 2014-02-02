package com.team1a.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1a.dto.BankAccount;
import com.team1a.util.Authenticator;

/**
 * Servlet implementation class FundTransferControl
 */
@WebServlet("/validator")
public class TwoStepValidateControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TwoStepValidateControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		
		//request.getRequestDispatcher("customer/customer.jsp?url=confirm&action="+action).forward(request, response);
		request.getRequestDispatcher("confirm.jsp?action="+action).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		System.out.println("action=" +action);
		//String type=request.getParameter("type");
		String type=null;
		String acc=request.getParameter("accNo");
		int	pin=Integer.parseInt(request.getParameter("txtPIN"));
		
		StringTokenizer token=new StringTokenizer(acc, "-");
		int accNo=0;
		
		while(token.hasMoreTokens()){
			accNo=Integer.parseInt(token.nextToken());
			type=token.nextToken().trim();
			break;
		}
		
		Map<Integer, BankAccount> accLst=(Map<Integer, BankAccount>) request.getSession(false).getAttribute("accLst");
		BankAccount ba=accLst.get(Integer.valueOf(accNo));
		
		System.out.println(accNo);
		System.out.println(ba.getAccountNumber());
		RequestDispatcher dispatcher=null;
		
		if(ba.getPinNo()==pin){
			System.out.println("Pin correct");
			request.getSession(false).setAttribute("txnAcc", ba);
			request.setAttribute("fromAcc", accNo);
			request.setAttribute("type", type);
			
			//request.getRequestDispatcher("/customer/customer.jsp?url="+action).forward(request, response);
			dispatcher=request.getRequestDispatcher(action+".jsp");
			dispatcher.forward(request, response);
		}
		
		else{
			request.setAttribute("error", "PIN incorrect");
			dispatcher=request.getRequestDispatcher("confirm.jsp?action"+action);
			dispatcher.forward(request, response);
		}
		
//dispatcher.forward(request, response);
	}

}
