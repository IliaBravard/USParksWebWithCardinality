package model; // The package where this entity is located at

/**
 * @author Ilia Bravard - igbravard
 * CIS175 - Fall 2022
 * Oct 5, 2022
 */

// Including the needed imports 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This is the second entity used in the "parks" database.
 */
@Entity
@Table(name = "traveler")
public class Traveler {
	@Id
	@GeneratedValue

	@Column(name = "TRAVELER_ID")
	private int id; // The traveler's ID number

	@Column(name = "FIRST_NAME")
	private String firstName; // The traveler's first name

	@Column(name = "LAST_NAME")
	private String lastName; // The traveler's last name

	private String email; // The traveler's email address

	/**
	 * This is the default, no-argument constructor.
	 */
	public Traveler() {
		super();
	}

	/**
	 * This is the non-default constructor that sets the needed fields of this
	 * entity.
	 * 
	 * @param idNumber - the traveler's ID number
	 * @param fName    - the traveler's first name
	 * @param lName    - the traveler's last name
	 */
	public Traveler(int idNumber, String fName, String lName) {
		setId(idNumber);
		setFirstName(fName);
		setLastName(lName);
	}

	/**
	 * This is a helper constructor used to auto-generate the traveler's ID number
	 * and email address.
	 * 
	 * @param fName - the traveler's first name
	 * @param lName - the traveler's last name
	 */
	public Traveler(String fName, String lName) {
		setFirstName(fName);
		setLastName(lName);
		setEmail();
	}

	// Generating the needed accessors and mutators for this POJO
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	/**
	 * This method generates the email address for each traveler. For example, Bob
	 * Frank's email address will be 'bFrank@usparks.com'.
	 */
	public void setEmail() {
		email = this.firstName.substring(0, 1).toLowerCase() + this.lastName.substring(0, 1).toUpperCase()
				+ this.lastName.substring(1).toLowerCase() + "@usparks.com";
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
		return "Traveler [TRAVELER_ID = " + id + ", FIRST_NAME = " + firstName + ", LAST_NAME = " + lastName
				+ ", TRAVEL_EMAIL = " + email + "]";
	}
}