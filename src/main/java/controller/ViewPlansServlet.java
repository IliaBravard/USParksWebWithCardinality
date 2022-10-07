package controller; // The package where this class is located at

/**
 * @author Ilia Bravard - igbravard
 * CIS175 - Fall 2022
 * Oct 5, 2022
 */

// Including the needed inports for this class
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Allows access to the TravelPlan entity
import model.TravelPlan;

/**
 * Servlet implementation class ViewPlansServlet. This servlet class gathers all
 * plans from the database and sends them to the JPS page.
 */
@WebServlet("/ViewPlansServlet")
public class ViewPlansServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This is the default, no argument constructor.
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewPlansServlet() {
		super();
	}

	/**
	 * This method persists the 'Travel_Plan' entity and sends the found records to
	 * an attribute to be later used in a JSP page
	 * 
	 * @param request  - the HTTP request
	 * @param response - the HTTP response
	 * @throws ServletException, IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TravelPlanHelper tph = new TravelPlanHelper();
		List<TravelPlan> genericPlan = tph.showAllPlans();

		request.setAttribute("allPlans", genericPlan);

		if (genericPlan.isEmpty()) { // If the plan is empty, the 'allPlans' attribute is set to nothing
			request.setAttribute("allPlans", "");
		}

		// Forwarding the request to the appropriate page
		getServletContext().getRequestDispatcher("/plans.jsp").forward(request, response);
	}

	/**
	 * This method sends the post request from the user to the doGet method.
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