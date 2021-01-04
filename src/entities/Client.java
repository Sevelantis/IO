package entities;

import java.util.ArrayList;
import java.util.List;

public class Client extends Entity
{
	//attributes

	private String firstName;
	private String lastName;
	private String phoneNr;
	private String email;
//	Reservation reservationList;
	List<Integer> reservationList;

	//methods

	public Client(String firstName, String lastName, String phoneNr, String email)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNr = phoneNr;
		this.email = email;
		reservationList = new ArrayList<>();
	}


	public void addReservation(int id_reservation)
	{
		reservationList.add(id_reservation);
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