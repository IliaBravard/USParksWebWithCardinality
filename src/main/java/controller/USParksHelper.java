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
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

// Allows access to the model class
import model.USNationalParks;

/** This is the helper/controller class that does all the persistence to the database (i.e. the DAO).
 */
public class USParksHelper {

	// Declaring a global manager factory variable to be used throughout the
	// database transaction methods
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WeekThreeAssignment");

	/**
	 * This method performs the insertion transaction to the database.
	 * 
	 * @param parkToInsert the national park to be inserted
	 */
	public void insertRecord(USNationalParks parkToInsert) {

		// The entity manager object provides the operations from and to the database
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin(); // Begins the database transaction
		em.persist(parkToInsert);
		em.getTransaction().commit(); // Commits the transaction
		em.close(); // The managed entities are in a detached state
	}

	/**
	 * This method displays all national parks added to the database by the user.
	 * 
	 * @return a list containing all parks in the database
	 */
	public List<USNationalParks> showAllParks() {
		EntityManager em = emfactory.createEntityManager();

		// Using a JPQL statement to list all national parks from the database
		@SuppressWarnings("unchecked") // Suppresses the 'Infer Generic Type Arguments' refactoring
		List<USNationalParks> allParks = em.createQuery("SELECT p FROM USNationalParks p").getResultList(); // We want the whole
																											// list
		return allParks;
	}

	/**
	 * This method deletes a park record from the database based on the provided
	 * name, state, and area fields.
	 * 
	 * @param parkToDelete the park object to be removed
	 */
	public void deletePark(USNationalParks parkToDelete) {

		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();

		// Using named parameters as a way to parameterize the queries
		TypedQuery<USNationalParks> parameterizedQuery = em.createQuery(
				"SELECT p FROM USNationalParks p WHERE p.name = :selectedName AND p.state = :selectedState AND p.area = :selectedArea",
				USNationalParks.class);

		// Substituting the parameters with actual data from the parkToDelete object
		parameterizedQuery.setParameter("selectedName", parkToDelete.getName()); // Data is only accessed through the
																					// accessors of the model class
		parameterizedQuery.setParameter("selectedState", parkToDelete.getState());
		parameterizedQuery.setParameter("selectedArea", parkToDelete.getArea());

		// Indicates that we want only one result from the query statement
		parameterizedQuery.setMaxResults(1);

		// Assigning the result to the list of items
		USNationalParks singleOutput = parameterizedQuery.getSingleResult();

		em.remove(singleOutput); // Finally removing the record from the database
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * This method searches for a particular national park based on its name
	 * provided.
	 * 
	 * @param parkName the name of the park
	 * @return the correpsonding parks based on the search criteria entered by the
	 *         user
	 */
	public List<USNationalParks> searchForParkByName(String parkName) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();

		TypedQuery<USNationalParks> parameterizedQuery = em
				.createQuery("SELECT p FROM USNationalParks p WHERE p.name = :selectedName", USNationalParks.class);
		parameterizedQuery.setParameter("selectedName", parkName);

		List<USNationalParks> foundParks = parameterizedQuery.getResultList();
		em.close();

		return foundParks;
	}

	/**
	 * This method searches for a particular national park based on the US state
	 * provided.
	 * 
	 * @param parkState the name of the US state
	 * @return the correpsonding parks based on the search criteria entered by the
	 *         user
	 */
	public List<USNationalParks> searchForParkByState(String parkState) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();

		TypedQuery<USNationalParks> parameterizedQuery = em
				.createQuery("SELECT p FROM USNationalParks p WHERE p.state = :selectedState", USNationalParks.class);
		parameterizedQuery.setParameter("selectedState", parkState);

		List<USNationalParks> foundParks = parameterizedQuery.getResultList();
		em.close();

		return foundParks;
	}

	/**
	 * This method searches for a park in the database based on the area provided by
	 * the user.
	 * 
	 * @param parkArea the area of the park (in acres)
	 * @return the park corresponding to the user's criteria
	 */
	public List<USNationalParks> searchForParkByArea(int parkArea) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();

		TypedQuery<USNationalParks> parameterizedQuery = em
				.createQuery("SELECT p FROM USNationalParks p WHERE p.area = :selectedArea", USNationalParks.class);
		parameterizedQuery.setParameter("selectedArea", parkArea);

		List<USNationalParks> foundParks = parameterizedQuery.getResultList();
		em.close();

		return foundParks;
	}

	/**
	 * This method searches for a park in the database based on the id provided by
	 * the user.
	 * 
	 * @param idString the id of the park record
	 * @retur the park corresponding to the user's criteria
	 */
	public USNationalParks searchForParkById(int idString) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();

		USNationalParks foundParks = em.find(USNationalParks.class, idString);
		em.close();

		return foundParks;
	}

	/**
	 * This method updates the park record specified by the user.
	 * 
	 * @param pIdToEdit the id of the record to be updated
	 */
	public void updatePark(USNationalParks pIdToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(pIdToEdit); // When entities are merged, the database updates automatically
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * This method closes the entity factory manager to interrupt any connections
	 * between the application and the local database.
	 */
	public void cleanUp() {
		emfactory.close();
	}
}