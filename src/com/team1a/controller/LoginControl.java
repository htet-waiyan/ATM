package com.team1a.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team1a.biz.BankAccountManager;
import com.team1a.biz.CustomerManager;
import com.team1a.dto.BankAccount;
import com.team1a.dto.Customer;
import com.team1a.util.Authenticator;

/**
 * Servlet implementation class LoginControl
 */
@WebServlet("/login")
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginControl() {
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
		String id=request.getParameter("txtID");
		String passwd=request.getParameter("txtPasswd");
		
		CustomerManager cm=new CustomerManager();
		BankAccountManager bm=new BankAccountManager();
		
		Authenticator auth=new Authenticator();
		Customer cust=null;
		HttpSession session=null;
		RequestDispatcher dispatcher=null;
		Map<Integer, BankAccount> accLst=new HashMap<Integer, BankAccount>();
		
		if(auth.authenticate(id, passwd)){
			System.out.println("Logging....in");
			cust=cm.getCustomerById(id);
			session=request.getSession(true);
			
			System.out.println(cust.getName());
			session.setAttribute("cust", cust);
			session.setMaxInactiveInterval(60*10);
			
			accLst=bm.getAllAccountsOf(cust.getUserId());
     		//System.out.println(accLst.get(1).getAccountNumber());
			session.setAttribute("accLst", accLst);
			
			//response.sendRedirect("customer/customer.jsp?url=listacc");
			//request.getRequestDispatcher("/customer/listacc.jsp").forward(request, response);
			response.sendRedirect("CustomerHomepage.jsp");
		}
		else{
			dispatcher=request.getRequestDispatcher("login.jsp");
			request.setAttribute("msg", "userid and password doesn't match");
			dispatcher.forward(request, response);
		}
		
		//response.sendRedirect("login)
		
	}

}
