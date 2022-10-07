package controller; // The package where this DAO is located at

/**
 * @author Ilia Bravard - igbravard
 * CIS175 - Fall 2022
 * Oct 5, 2022
 */

// Including the needed imports for this class
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

// Allows access to the Traveler entity
import model.Traveler;

/**
 * This is the data access object class for the traveler's entity.
 */
public class TravelerHelper {

	// Declaring a global manager factory variable to be used throughout the
	// database transaction methods
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WeekThreeAssignment");

	/**
	 * This method adds a customer record to the traveler's entity.
	 * 
	 * @param customer - the customer record/object to be added in the table
	 */
	public void addTraveler(Traveler customer) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(customer);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * This method persists the "Traveler" entity from the local database and
	 * populates a list with the found records.
	 * 
	 * @return the list containing all travelers
	 */
	public List<Traveler> showAllTravelers() {
		EntityManager em = emfactory.createEntityManager();
		@SuppressWarnings("unchecked") // Suppresses the warning for the JPQL statement
		List<Traveler> allTravelers = em.createQuery("SELECT t FROM Traveler t").getResultList();
		return allTravelers;
	}

	/**
	 * This method looks up for an existing traveler record in the database and if
	 * one is found, it will return that record. Otherwise, the name passed to this
	 * method will be set as the new traveler's name.
	 * 
	 * @param travelerFName - the traveler's first name
	 * @param travelerLName - the traveler's last name
	 * @return the new or found traveler record
	 */
	public Traveler findTraveler(String travelerFName, String travelerLName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Traveler> typedQuery = em.createQuery(
				"select t from Traveler t where t.firstName = :selectedFName AND t.lastName = :selectedLName",
				Traveler.class);

		typedQuery.setParameter("selectedFName", travelerFName);
		typedQuery.setParameter("selectedLName", travelerLName);
		typedQuery.setMaxResults(1);

		Traveler foundRecord;
		try { // Exception handling when no traveler records are found
			foundRecord = typedQuery.getSingleResult();
		} catch (NoResultException e) { // If so, set a new traveler
			System.out.println("ERROR!!! " + e.getMessage()); /* Diagnostic */
			foundRecord = new Traveler(travelerFName, travelerLName);
		}
		em.close();
		return foundRecord;
	}

	/**
	 * This method closes the entity factory manager to interrupt any connections
	 * between the application and the local database.
	 */
	public void cleanUp() {
		emfactory.close();
	}
}