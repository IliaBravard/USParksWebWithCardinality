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

// Allows access to the USNationalParks entity
import model.USNationalParks;

/**
 * Servlet implementation class AddParkServlet. This servlet class adds a
 * national park record to the database application.
 */
@WebServlet("/AddParkServlet")
public class AddParkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This is the default, no argument constructor.
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public AddParkServlet() {
		super();
	}

	/**
	 * This method completes the park record insertion if everything went
	 * successfully and redirects the user to another page.
	 * 
	 * @param request  - the HTTP request
	 * @param response - the HTTP response
	 * @throws ServletException, IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Assigning the user's input to the appropriate variables
		String parkName = request.getParameter("name");
		String parkState = request.getParameter("state");
		Double parkArea = 0.00;
		boolean check = false;
		String path = "";

		// Checking whether the user entered a parseable string or anything else for
		// the park's area
		try {
			// Parsing the string input to a double data type
			parkArea = Double.parseDouble(request.getParameter("area"));
			check = true;
		} catch (Exception e) { // If so, an exception is thrown
			System.out.println("ERROR!!! " + e.getMessage()); /* Diagnostic */
			path = "/insert.html"; // Refreshing the insert parks page to try again
			check = false;
		}

		if (check) {
			USNationalParks parkToAdd = new USNationalParks(parkName, parkState, parkArea);
			USParksHelper parkDao = new USParksHelper(); // Instantiating a new data access object
			parkDao.insertRecord(parkToAdd); // Adding the record to the database
			path = "/options.html"; // Going back to the options page if everything went successfully
		}

		// Forwarding the request to the appropriate page
		getServletContext().getRequestDispatcher(path).forward(request, response);
	}
}