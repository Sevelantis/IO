package managers;

import interfaces.*;
import entities.*;

public class ReservationManager implements IManager
{
	//attributes

	private static ReservationManager instance;
	Reservation reservationList;

	//methods

	@Override
	public void add(Object obj)
	{

	}

	@Override
	public Reservation get(int id_reservation)
	{
		// TODO
		return null;
	}

	public void generateAgreement(int id_reservation)
	{
		// TODO
	}

	//getters

	public static ReservationManager getInstance()
	{
		if(instance == null) instance = new ReservationManager();
		return instance;
	}

	// methods handled by database -------------
	// .
}