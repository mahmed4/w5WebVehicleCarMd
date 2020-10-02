package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CarPojo;

/**
 * Servlet implementation class VagitationServlet
 */
@WebServlet("/navigationServlet")
public class NavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NavigationServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//doGet(request, response);		
		
		String act = request.getParameter("doThisToItem");		
		String path = "/viewAllItemsServlet";
		
		CarCrud dao = new CarCrud();
		if(act.equals("delete")) {	
			try {
			Integer tempId = Integer.parseInt(request.getParameter("id"));
			CarPojo itemToDelete = dao.searchForItemById(tempId);
			dao.deleteItem(itemToDelete);
			
			}catch (NumberFormatException e) {
				System.out.println("Forgot to select an item");
			}		
		}
		else if (act.equals("edit")) {
			try {
			Integer tempId = Integer.parseInt(request.getParameter("id"));
			CarPojo itemToEdit = dao.searchForItemById(tempId);
			request.setAttribute("itemToEdit", itemToEdit);
			path = "/edit-item.jsp";
			
			}catch (NumberFormatException e) {
				System.out.println("Forgot to select an item");
			}			
		}
		else if (act.equals("add")) {	
			
			path = "/index.html";
			
		}
		
		getServletContext().getRequestDispatcher(path).forward(request, response);
	}

}
