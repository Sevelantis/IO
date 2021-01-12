package entities;

import java.util.List;
import java.util.Vector;

public class Client extends Entity
{
	private static int idCounter = 0;
	//attributes

	private String firstName;
	private String lastName;
	private String phoneNr;
	private String email;
	List<Reservation> reservationList = new Vector<>();

	//methods

	public Client(String firstName, String lastName, String phoneNr, String email)
	{
		this.id = idCounter++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNr = phoneNr;
		this.email = email;
	}


	public void addReservation(Reservation reservation)
	{
		reservationList.add(reservation);
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

	public List<Reservation> getReservationList()
	{
		return reservationList;
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