package controller; // The package where this servlet class is located at

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
 * Servlet implementation class AddParksForPlanServlet. This servlet class
 * gathers all items from the database and puts them on a travel plan.
 */
@WebServlet("/AddParksForPlanServlet")
public class AddParksForPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This is the default, no argument constructor.
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public AddParksForPlanServlet() {
		super();
	}

	/**
	 * This method persists the 'Travel_Plans' entity and sends the found records to
	 * an attribute to be later used in a JSP page.
	 * 
	 * @param request  - the HTTP request
	 * @param response - the HTTP response
	 * @throws ServletException, IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		USParksHelper parkDao = new USParksHelper();

		request.setAttribute("allParks", parkDao.showAllParks());

		if (parkDao.showAllParks().isEmpty()) { // If the access object is empty, the 'allParks' attribute is set to
												// an empty string
			request.setAttribute("allParks", "");
		}

		// Forwarding the request to the appropriate page
		getServletContext().getRequestDispatcher("/insertPlan.jsp").forward(request, response);
	}

	/**
	 * This method sends the user request to the doGet() method.
	 * 
	 * @param request  - the HTTP request
	 * @param response - the HTTP response
	 * @throws ServletException, IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}