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

// Allows access to the TravelPlan entity
import model.TravelPlan;

/**
 * This is the data access object class for the travel plan's entity.
 */
public class TravelPlanHelper {

	// Declaring a global manager factory variable to be used throughout the
	// database transaction methods
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WeekThreeAssignment");

	/**
	 * This method adds a new travel plan to the 'Travel_Plan' entity.
	 * 
	 * @param plan - the new plan object/record to be added
	 */
	public void addNewPlan(TravelPlan plan) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(plan);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * This method persists the 'Travel_Plan' entity from the local database and
	 * populates a list with the found records.
	 * 
	 * @return the list containing all travel plans
	 */
	public List<TravelPlan> showAllPlans() {
		EntityManager em = emfactory.createEntityManager();
		@SuppressWarnings("unchecked") // Suppresses the warning for the JPQL statement
		List<TravelPlan> allPlans = em.createQuery("SELECT tp FROM TravelPlan tp").getResultList();
		return allPlans;
	}

	/**
	 * This helper method deletes a plan record from the local database.
	 * 
	 * @param planToDelete - the object/record to be deleted
	 */
	public void deletePlans(TravelPlan planToDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<TravelPlan> paramQuery = em.createQuery("select p from TravelPlan p where p.planId = :selectedId",
				TravelPlan.class);

		// Substituting the data to the created parameter
		paramQuery.setParameter("selectedId", planToDelete.getPlanId());

		// We need a single record only
		paramQuery.setMaxResults(1);

		// Saving the results into a new travel plan
		TravelPlan result = paramQuery.getSingleResult();

		// Removing the plan to be deleted from the database
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * This method searches the database for a given travel plan by its ID number.
	 * 
	 * @param planId - the plan ID of the record to be searched
	 * @return the found record
	 */
	public TravelPlan searchForPlanById(Integer planId) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TravelPlan found = em.find(TravelPlan.class, planId);
		em.close();
		return found;
	}

	/**
	 * This method merges the plan changes into a record/object.
	 * 
	 * @param planToUpdate - the travel plan to be updated by the user
	 */
	public void updatePlan(TravelPlan planToUpdate) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(planToUpdate);
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