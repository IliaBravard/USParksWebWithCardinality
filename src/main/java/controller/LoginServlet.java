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
 * Servlet implementation class LoginServlet. This servlet class checks the
 * user's login credentials and lets him/her to the database application.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This is the default, no argument constructor.
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * This method checks the user's login credentials and redirects him/her to the
	 * appropriate page.
	 * 
	 * @param request  - the HTTP request
	 * @param response - the HTTP response
	 * @throws ServletException, IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Assigning the user inputs to the appropriate variables
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		String path = ""; // The path the user is redirected to

		// Checking whether the login credentials are valid
		if (userName.equals("Instructor") && password.equals("AdminAccess@2022")) {
			path = "/options.html"; // Allow him/her in
		} else {
			path = "/index.html"; // Try again
		}

		// Forwarding the request to the appropriate page
		getServletContext().getRequestDispatcher(path).forward(request, response);
	}
}