package model; // The package where this entity is located at

/**
 * @author Ilia Bravard - igbravard
 * CIS175 - Fall 2022
 * Oct 5, 2022
 */

// Including the needed imports for this class
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * This is the third entity used in the "parks" database.
 */
@Entity
@Table(name = "travel_plans")
public class TravelPlan {
	@Id
	@GeneratedValue

	@Column(name = "PLAN_ID")
	private int planId; // The team's ID number

	@Column(name = "PLAN_NAME")
	private String planName; // The team's name

	@Column(name = "ACTIVITY")
	private String activity; // The activity to perform at the park (climbing, fishing, boating, etc.)

	@Column(name = "TRAVEL_DATE")
	private LocalDate travelDate; // The team's travel date

	@ManyToOne(cascade = CascadeType.PERSIST) // One traveler can have many trip plans, but every trip plan can be only
												// owned by a single traveler
	@JoinColumn(name = "TRAVELER_ID")
	private Traveler traveler; // The team member/traveler

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER) // One plan, many availble parks to choose from
	@JoinColumn(name = "PARK_ID")
	private List<USNationalParks> parkList; // The list containing all available parks to choose from

	/**
	 * This is the defualt, no-argument constructor.
	 */
	public TravelPlan() {
	}

	/**
	 * This is the non-default constructor that sets three fields of this entity.
	 * 
	 * @param name   - the group's assigned name
	 * @param action - the activity to perform at the park
	 * @param date   - the group's travel data
	 */
	public TravelPlan(String name, String action, LocalDate date, Traveler member) {
		setPlanName(name);
		setActivity(action);
		setTravelDate(date);
		setTraveler(member);
	}

	// Generating the accessors and mutators for this entity
	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int groupId) {
		this.planId = groupId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String groupName) {
		this.planName = groupName;
	}

	public LocalDate getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(LocalDate travelDate) {
		this.travelDate = travelDate;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Traveler getTraveler() {
		return traveler;
	}

	public void setTraveler(Traveler groupMember) {
		this.traveler = groupMember;
	}

	public List<USNationalParks> getParkList() {
		return parkList;
	}

	public void setParkList(List<USNationalParks> parkList) {
		this.parkList = parkList;
	}

	/**
	 * This is a helper method that prints the details for each field used in this
	 * entity class.
	 * 
	 * @return a string line listing the values held in each field of the class, if
	 *         any
	 */
	@Override
	public String toString() {
		return "Plan [PLAN_ID = " + planId + ", PLAN_NAME = " + planName + ", ACTIVITY = " + activity
				+ ", TRAVEL_DATE = " + travelDate + ", TRAVELER_ID = " + traveler + "]";
	}
}