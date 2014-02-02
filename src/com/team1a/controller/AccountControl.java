package com.team1a.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1a.biz.BankAccountManager;
import com.team1a.dto.BankAccount;
import com.team1a.dto.CheckingAccount;
import com.team1a.dto.Customer;
import com.team1a.dto.SavingAccount;
import com.team1a.util.AccountNumberGenerator;
import com.team1a.util.NoAccountFound;

/**
 * Servlet implementation class AccountCreateController
 */
@WebServlet("/bankacc")
public class AccountControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		BankAccountManager accManager=new BankAccountManager();
		RequestDispatcher dispatcher=null;
		
		if(action.equalsIgnoreCase("request")){
			request.getSession(false).setAttribute("accNo", AccountNumberGenerator.generate());
			dispatcher=request.getRequestDispatcher("create.jsp");
			
		}
		
		else if(action.equalsIgnoreCase("create")){
			dispatcher=request.getRequestDispatcher(createAccount(request, response,accManager ));
		}

		else
			dispatcher=request.getRequestDispatcher("create.jsp");
		
		dispatcher.forward(request, response);
	}
	
	protected String createAccount(HttpServletRequest request, HttpServletResponse response, BankAccountManager accManager){
		String userID=request.getParameter("userID");
		//String pass_nrc=request.getParameter("pass_nrc");
		String accountNO=request.getParameter("accNo");
		String pinNO=request.getParameter("pinNo");
		String accType=request.getParameter("AccType");
		String minBalance=request.getParameter("minbal");
		String creationDate=request.getParameter("doj");
		String overDraftAmt=request.getParameter("overAmt");
		
		System.out.println(pinNO);
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
		Date createDate=null;
		SavingAccount sa=null;
		CheckingAccount ca=null;
		
		//List<BankAccount> accList=new ArrayList<BankAccount>();
		
		try {
			createDate=format.parse(creationDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(accType.equalsIgnoreCase("Saving")){
			sa=new SavingAccount();
			sa.setAccountNumber(Integer.parseInt(accountNO));
			sa.setBalance(new BigDecimal(0.0));
			sa.setPinNo(Integer.parseInt(pinNO));
			sa.setCreateDate(createDate);
			sa.setMinimumBalance(new BigDecimal(Double.parseDouble(minBalance)));
			sa.setType("Saving");
			
			try {
				accManager.createSavingAccount(sa,userID);
				Customer c=(Customer) request.getSession(false).getAttribute("cust");
				c.addAccount(sa);
				//request.getSession(false).setAttribute("acctype", "Saving");
				
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("msg", "Cannot create bank account, make sure data is correct");
				
				if(e.getErrorCode()==1062){
					request.setAttribute("msg", "UserId has been reserved.");
				}
				return "create.jsp";
			}
		}
		else if(accType.equalsIgnoreCase("checking")){
			ca=new CheckingAccount();
			ca.setAccountNumber(Integer.parseInt(accountNO));
			ca.setBalance(new BigDecimal(0.0));
			ca.setPinNo(Integer.parseInt(pinNO));
			ca.setCreateDate(createDate);
			ca.setOverdraftAmount(new BigDecimal(Double.parseDouble(overDraftAmt)));
			ca.setType("Checking");
			
			try {
				accManager.createCheckingAccount(ca,userID);
				Customer c=(Customer) request.getSession(false).getAttribute("cust");
				c.addAccount(ca);
				//request.setAttribute("accType", "Checking");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				request.setAttribute("msg", "Cannot create bank account, make sure data is correct");
				e.printStackTrace();
				return "create.jsp";
			}
		}
		
		return "custdetail.jsp";
	}
}
