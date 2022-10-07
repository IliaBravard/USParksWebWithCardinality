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

/**
 * Servlet implementation class ViewParksServlet. This servlet class displays
 * all national park records available in the database application, if any.
 */
@WebServlet("/ViewParksServlet")
public class ViewParksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This is the default, no argument constructor.
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewParksServlet() {
		super();
	}

	/**
	 * This method presents all available records within the local database.
	 * 
	 * @param request - the HTTP request
	 * @param response - the HTTP response
	 * @throws ServletException, IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		USParksHelper parkDao = new USParksHelper(); // Instantiating a new data access object

		request.setAttribute("allParks", parkDao.showAllParks()); // Setting a new attribute

		String path = "/view.jsp";

		// If no records exist in the current database, the user is redirected to the
		// options page to add a park
		if (parkDao.showAllParks().isEmpty()) {
			path = "/options.html";
		}

		// Forwarding the request to the appropriate page
		getServletContext().getRequestDispatcher(path).forward(request, response);
	}
}