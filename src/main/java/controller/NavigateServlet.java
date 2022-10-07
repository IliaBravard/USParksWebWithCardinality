package controller; // The package where this class is located at

/**
 * @author Ilia Bravard - igbravard
 * CIS175 - Fall 2022
 * Oct 5, 2022
 */

// Including the needed imports for this servlet class
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Allows access to the USNationalParks entity
import model.USNationalParks;

/**
 * Servlet implementation class EditRemoveServlet. This servlet class determines
 * what should be done if the user chooses to either edit or delete a national
 * park record.
 */
@WebServlet("/NavigateServlet")
public class NavigateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This is the default, no argument constructor.
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public NavigateServlet() {
		super();
	}

	/**
	 * This method determines the actions that should be taken when the user hits
	 * the edit and delete buttons of the form and redirects the user to the same or
	 * another page.
	 * 
	 * @param request  - the HTTP request
	 * @param response - the HTTP response
	 * @throws ServletException, IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Instantiating a new data access object
		USParksHelper parkDao = new USParksHelper();
		String act = request.getParameter("doThisToPark");
		int parkId = 0;

		String path = "/ViewParksServlet";

		if (act.equals("delete")) { // If the option chosen is to delete,

			try { // Checking whether the id entered is parseable
				parkId = Integer.parseInt(request.getParameter("userId")); // Get the id of the record
			} catch (Exception e) {
				System.out.println("ERROR!!! " + e.getMessage()); /* Diagnostic */
			}

			USNationalParks parkToDelete = parkDao.searchForParkById(parkId); // Search the record by the id
			parkDao.deletePark(parkToDelete); // Delete the record
		}

		else if (act.equals("edit")) { // Else if the option chosen is to edit the record,

			try { // Same exception handling as the above one
				parkId = Integer.parseInt(request.getParameter("userId")); // Get the id
			} catch (Exception e) {
				System.out.println("ERROR!!! " + e.getMessage()); /* Diagnostic */
			}

			USNationalParks parkToEdit = parkDao.searchForParkById(parkId); // Search for the park
			request.setAttribute("toEdit", parkToEdit); // Edit the record
			path = "/edit.jsp"; // Redirect the user to the edit page
		}

		// Forwarding the request to the appropriate page
		getServletContext().getRequestDispatcher(path).forward(request, response);
	}
}