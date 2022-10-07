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
 * Servlet implementation class CreateNewPlanServlet. This servlet class creates
 * a new trip plan defined by the user.
 */
@WebServlet("/CreateNewPlanServlet")
public class CreateNewPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This is the default, no argument constructor.
	 * 
	 * @see HttpServlet#HttpServlet(). This servlet class creates a new travel plan
	 *      for the user.
	 */
	public CreateNewPlanServlet() {
		super();
	}

	/**
	 * This method allows for the creation of a new trip plan defined by the user.
	 * 
	 * @param request  - the HTTP request
	 * @param response - the HTTP response
	 * @throws ServletException, IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		USParksHelper uph = new USParksHelper();
		String planName = request.getParameter("planName");
		String travelActivity = request.getParameter("travelActivity");
		System.out.println("Plan Name: " + planName); /* Diagnostic */

		// Assigning the user inputs to the appropriate variables
		String tripMonth = request.getParameter("tripMonth");
		String tripDay = request.getParameter("tripDay");
		String tripYear = request.getParameter("tripYear");

		String travelerFName = request.getParameter("travelerFName");
		String travelerLName = request.getParameter("travelerLName");

		LocalDate date;

		// Exception handling for when the user does not enter a number or does not
		// populate the date fields
		try {
			date = LocalDate.of(Integer.parseInt(tripYear), Integer.parseInt(tripMonth), Integer.parseInt(tripDay));
		} catch (NumberFormatException e) {
			System.out.println("ERROR!!! " + e.getMessage()); /* Diagnostic */
			date = LocalDate.now(); // If so, the trip's date will be today
		}

		// Assigning the parks selected to an array collection
		String[] selectedParks = request.getParameterValues("allParksToAdd");

		// Adding the array items to a list
		List<USNationalParks> selectedParksInList = new ArrayList<USNationalParks>();

		// Here we are making sure that the user has selected a park. Otherwise, a
		// NullPointerException would be thrown at us
		if (selectedParks != null && selectedParks.length > 0) {
			for (int i = 0; i < selectedParks.length; i++) { // Traversing the list
				System.out.println(selectedParks[i]); /* Diagnostic */
				USNationalParks p = uph.searchForParkById(Integer.parseInt(selectedParks[i])); // Searching for the
																								// particular park
				selectedParksInList.add(p); // Adding the found park to the list
			}
		}

		// Instantiating a new traveler using the non-default constructor
		Traveler traveler = new Traveler(travelerFName, travelerLName);

		// Instantiating a new travel plan for the database
		TravelPlan travelPlan = new TravelPlan(planName, travelActivity, date, traveler);
		travelPlan.setParkList(selectedParksInList);

		TravelPlanHelper tph = new TravelPlanHelper();
		tph.addNewPlan(travelPlan); // Inserting the new record to the database

		System.out.println(travelPlan.toString()); /* Diagnostic */

		// Forwarding the request to the appropriate page
		getServletContext().getRequestDispatcher("/ViewPlansServlet").forward(request, response);
	}

	/**
	 * This method receives the request from the form and forwards it to the doGet()
	 * method of this servlet class.
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