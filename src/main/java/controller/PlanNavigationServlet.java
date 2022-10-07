package controller; // The class where this servlet class is located at

/**
 * @author Ilia Bravard - igbravard
 * CIS175 - Fall 2022
 * Oct 5, 2022
 */

// Including the needed imports for this servlet class.
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Allows for the use of the TravelPlan entity
import model.TravelPlan;

/**
 * Servlet implementation class PlanNavigationServlet. This servlet navigates
 * the user to what actions should be taken when he/she presses a certain button
 * on the form.
 */
@WebServlet("/PlanNavigationServlet")
public class PlanNavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This is the default, no argument constructor.
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public PlanNavigationServlet() {
		super();
	}

	/**
	 * This method determines the action that should be taken when the user decides
	 * to either delete or edit a certain travel plan or when he/she wants to go
	 * back or exit the application.
	 * 
	 * @param request  - the HTTP request
	 * @param response - the HTTP response
	 * @throws ServletException, IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TravelPlanHelper planDao = new TravelPlanHelper();
		String action = request.getParameter("doThisToPlan");

		if (action == null) { // If no button has been selected,
			// Send the user to the views page
			getServletContext().getRequestDispatcher("/ViewPlansServlet").forward(request, response);
		}

		else if (action.equals("delete")) { // Else if the user presses the delete button,
			try { // Exception handling for when the user forgets to select an option to edit or
					// delete
				Integer planId = Integer.parseInt(request.getParameter("id"));
				TravelPlan planToDelete = planDao.searchForPlanById(planId);
				planDao.deletePlans(planToDelete);
			} catch (NumberFormatException e) {
				System.out.println("Forgot to press a button!"); /* Diagnostic */
			} finally { // Forwarding the request to the appropriate page regardless of the result
				getServletContext().getRequestDispatcher("/ViewPlansServlet").forward(request, response);
			}
		}

		else if (action.equals("edit")) { // Else if the user presses the edit button,
			try {
				Integer planId = Integer.parseInt(request.getParameter("id"));

				// Searching for the travel plan record to be edited
				TravelPlan planToEdit = planDao.searchForPlanById(planId);
				request.setAttribute("planToEdit", planToEdit);

				// Setting attributes for the travel month, date, and year
				request.setAttribute("travelMonth", planToEdit.getTravelDate().getMonthValue());
				request.setAttribute("travelDate", planToEdit.getTravelDate().getDayOfMonth());
				request.setAttribute("travelYear", planToEdit.getTravelDate().getYear());

				USParksHelper parkDao = new USParksHelper();

				request.setAttribute("allParks", parkDao.showAllParks());

				// If there are no parks in the parks table, the allParks attribute is set to
				// an empty string
				if (parkDao.showAllParks().isEmpty()) {
					request.setAttribute("allParks", "");
				}

				// Forwarding the request to the appropriate page
				getServletContext().getRequestDispatcher("/editPlan.jsp").forward(request, response);
			} catch (NumberFormatException e) { // Sending the user back to the original page if no plan was selected to
												// be edited
				System.out.println("ERROR!!! " + e.getMessage()); /* Diagnostic */
				getServletContext().getRequestDispatcher("/ViewPlansServlet").forward(request, response);
			}
		}

		else if (action.equals("add")) { // Else if the user chooses the back option,
			getServletContext().getRequestDispatcher("/options.html").forward(request, response);
		}
	}
}