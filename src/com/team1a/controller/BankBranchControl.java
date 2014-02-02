package com.team1a.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1a.biz.BankBranchManager;
import com.team1a.dto.BankBranch;
import com.team1a.util.DuplicateKeyException;

/**
 * Servlet implementation class BankBranchControl
 */
@WebServlet("/bankbranch")
public class BankBranchControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankBranchControl() {
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
		//int bbid=Integer.parseInt(request.getParameter("bbid"));
		String name=request.getParameter("name");
		String location=request.getParameter("location");
		String desc=request.getParameter("desc");
		
		BankBranch branch=new BankBranch();
		//branch.setBBID(bbid);
		branch.setBranchName(name);
		branch.setLocation(location);
		branch.setDescription(desc);
		
		BankBranchManager bbm=new BankBranchManager();
		try{
			int row=bbm.registerBankBranch(branch);
			if(row>0){
				request.setAttribute("msg", "Save Successful");
				request.getRequestDispatcher("bankbranch.jsp").forward(request, response);
			}
		}
		
		catch(SQLException de){
			de.printStackTrace();
		}
	}

}
