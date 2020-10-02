package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CarPojo;

/**
 * Servlet implementation class EditItemServlet
 */
@WebServlet("/editItemServlet")
public class EditItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditItemServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		CarCrud dao = new CarCrud();		
		String make = request.getParameter("make");
		String model = request.getParameter("model");
		String year = request.getParameter("year");
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		
		CarPojo itemToUpdate = dao.searchForItemById(tempId);
		itemToUpdate.setMake(make);
		itemToUpdate.setModel(model);
		itemToUpdate.setYear(year);
		
		dao.updateItem(itemToUpdate);
		
		getServletContext().getRequestDispatcher("/viewAllItemsServlet").forward(request, response);		
		
	}

}
