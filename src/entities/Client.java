package entities;

import java.util.List;

public class Client extends Entity
{
	//attributes

	private String firstName;
	private String lastName;
	private String phoneNr;
	private String email;
	Reservation reservationList;

	//methods

	public Client(String firstName, String lastName, String phoneNr, String email)
	{
		// TODO
	}


	public void addReservation(int id_reservation)
	{
		// TODO
	}

	//getters

	public String getFirstName()
	{
		return this.firstName;
	}

	public String getLastName()
	{
		return this.lastName;
	}

	public String getPhoneNr()
	{
		return this.phoneNr;
	}

	public String getEmail()
	{
		return this.email;
	}

	public List<Integer> getReservationList()
	{
		// TODO
		return null;
	}

	//setters

	public void setFirstName(String firstName)

	{
		this.firstName = firstName;
	}

	public void setLastName(String lastName)

	{
		this.lastName = lastName;
	}

	public void setPhoneNr(String phoneNr)
	{

		this.phoneNr = phoneNr;
	}

	public void setEmail(String email)

	{
		this.email = email;
	}

}