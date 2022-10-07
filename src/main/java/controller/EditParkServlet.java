package controller; // The package where this class is located at

/**
 * @author Ilia Bravard - igbravard
 * CIS175 - Fall 2022
 * Oct 5, 2022
 */

// Including the needed imports for this servlet
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Allows access to the USNAtionalParks entity
import model.USNationalParks; // Allows for the use of the model class

/**
 * Servlet implementation class EditParkServlet. This servlet class updates a
 * national park record from the database application.
 */
@WebServlet("/EditParkServlet")
public class EditParkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This is the default, no argument constructor.
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public EditParkServlet() {
		super();
	}

	/**
	 * This method updates a park record from the database application and redirects
	 * the user to another page.
	 * 
	 * @param request  - the HTTP request
	 * @param response - the HTTP response
	 * @throws ServletException, IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		USParksHelper parkDao = new USParksHelper(); // Instantiating a new data access object

		// Assigning the inputs from the user to the appropriate variables
		String name = request.getParameter("name");
		String state = request.getParameter("state");
		Double area = 0.00;

		// Checking whether the user entered a parseable a string or anything else for
		// the park's area
		try {
			area = Double.parseDouble(request.getParameter("area"));
		} catch (Exception e) { // If so, an exception is thrown
			System.out.println("ERROR!!! " + e.getMessage()); /* Diagnostic */
		}

		Integer parkId = Integer.parseInt(request.getParameter("userId")); // Parsing the string to an integer

		// Searches for the particular park by its id
		USNationalParks parkToUpdate = parkDao.searchForParkById(parkId);

		// Setting the new values
		parkToUpdate.setName(name);
		parkToUpdate.setState(state);
		parkToUpdate.setArea(area);

		// Updating the park
		parkDao.updatePark(parkToUpdate);

		// Forwarding the request to the appropriate page
		getServletContext().getRequestDispatcher("/ViewParksServlet").forward(request, response);
	}
}