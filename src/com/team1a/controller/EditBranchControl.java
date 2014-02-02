package com.team1a.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1a.biz.BankBranchManager;
import com.team1a.dto.BankBranch;

/**
 * Servlet implementation class EditBranchControl
 */
@WebServlet("/editbranch")
public class EditBranchControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBranchControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		
		BankBranchManager bbm=new BankBranchManager();
		
		if(action.equalsIgnoreCase("all")){
			List<BankBranch> bbLst=bbm.getAllBranch();
			
			request.getSession(false).setAttribute("bbLst", bbLst);
			request.getRequestDispatcher("searchbranch.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("edit")){
			int id=Integer.parseInt(request.getParameter("id"));
			BankBranch bb=bbm.getBankBranchById(id);
			
			request.setAttribute("branch", bb);
			request.getRequestDispatcher("editbranch.jsp").forward(request, response);
		}
		
		else if(action.equalsIgnoreCase("search")){
			String name=request.getParameter("search");
			List<BankBranch> blst=bbm.getBranchByName("name");
			
			request.getSession(false).setAttribute("bbLst", blst);
			request.getRequestDispatcher("searchbranch.jsp").forward(request, response);
		}
		
		else if(action.equalsIgnoreCase("delete")){
			int id=Integer.parseInt(request.getParameter("id"));
			bbm.deleteBankBranch(id);
			List<BankBranch> bblst=(List<BankBranch>) request.getSession(false).getAttribute("bbLst");
			for(int i=0;i<bblst.size();i++){
				if(bblst.get(i).getBBID()==id)
					bblst.remove(i);
			}
			
			request.setAttribute("msg", "Delete successful");
			request.getRequestDispatcher("searchbranch.jsp").forward(request, response);
		}
		else if(action.equalsIgnoreCase("editsave")){
			int id=Integer.parseInt(request.getParameter("id"));
			
			BankBranch bb=null;
			List<BankBranch> blst=(List<BankBranch>) request.getSession(false).getAttribute("bbLst");
			
			for(int i=0;i<blst.size();i++){
				if(blst.get(i).getBBID()==id){
					bb=blst.get(i);
					break;
				}
			}
			
			bbm.updateBankBranch(bb);
			request.setAttribute("msg", "Update successful");
			request.getRequestDispatcher("editbranch.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
