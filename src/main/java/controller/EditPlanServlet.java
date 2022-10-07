package controller; // The package where this class is located at

/**
 * @author Ilia Bravard - igbravard
 * CIS175 - Fall 2022
 * Oct 5, 2022
 */

// Including the needed imports for this servlet class
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Allows access to all entities in this application
import model.TravelPlan;
import model.Traveler;
import model.USNationalParks;

/**
 * Servlet implementation class EditPlanServlet. This servlet class updates a
 * travel plan specified by the user.
 */
@WebServlet("/EditPlanServlet")
public class EditPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This is the default, no argument constructor.
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public EditPlanServlet() {
		super();
	}

	/**
	 * This method processes a form, updates all the fields, and finally persists
	 * the updates from the database entity.
	 * 
	 * @param request  - the HTTP request
	 * @param response - the HTTP response
	 * @throws ServletException, IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Instantiating the needed data access objects from each model
		TravelPlanHelper planDao = new TravelPlanHelper();
		USParksHelper parkDao = new USParksHelper();
		TravelerHelper travelerDao = new TravelerHelper();

		Integer planId = Integer.parseInt(request.getParameter("id"));
		TravelPlan planToUpdate = planDao.searchForPlanById(planId);

		// Assigning the user's inputs to the appropriate variables
		String newPlanName = request.getParameter("planName");

		String newMonth = request.getParameter("travelMonth");
		String newDay = request.getParameter("travelDate");
		String newYear = request.getParameter("travelYear");

		String travelerFName = request.getParameter("travelerFName");
		String travelerLName = request.getParameter("travelerLName");

		// Finding the new traveler
		Traveler newTraveler = travelerDao.findTraveler(travelerFName, travelerLName);

		LocalDate newDate;
		try { // Exception handling for when the user does not enter a date or he/she enters
				// characters instead of numbers
			newDate = LocalDate.of(Integer.parseInt(newYear), Integer.parseInt(newMonth), Integer.parseInt(newDay));
		} catch (NumberFormatException e) {
			System.out.println("ERROR!!! " + e.getMessage());
			newDate = LocalDate.now(); // If so, the trip date becomes today
		}

		try { // Exception handling for when no park exists in the database
			String[] selectedParks = request.getParameterValues("allParksToAdd");
			List<USNationalParks> selectedParksInPlan = new ArrayList<USNationalParks>();
			for (int i = 0; i < selectedParks.length; i++) {
				System.out.println(selectedParks[i]); /* Diagnostic */
				USNationalParks park = parkDao.searchForParkById(Integer.parseInt(selectedParks[i]));
				selectedParksInPlan.add(park);
			}
			planToUpdate.setParkList(selectedParksInPlan);
		} catch (NullPointerException e) {
			// If no parks are selected from the travel plan, set an empty list
			List<USNationalParks> selectedParksInPlan = new ArrayList<USNationalParks>();
			planToUpdate.setParkList(selectedParksInPlan);
			System.out.println("ERROR!!! " + e.getMessage()); /* Diagnostic */
		}

		// Setting the new travel plan information
		planToUpdate.setPlanName(newPlanName);
		planToUpdate.setTravelDate(newDate);
		planToUpdate.setTraveler(newTraveler);

		// Updating the travel plan record
		planDao.updatePlan(planToUpdate);

		// Forwarding the request to the appropriate page
		getServletContext().getRequestDispatcher("/ViewPlansServlet").forward(request, response);
	}
}