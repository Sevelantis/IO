package managers;

import entities.Reservation;
import interfaces.IManager;

import java.util.List;
import java.util.Vector;

public class ReservationManager implements IManager
{
	//attributes

	private static ReservationManager instance;
	List<Reservation> reservationList = new Vector<>();

	//methods

	@Override
	public void add(Object obj)
	{
		reservationList.add((Reservation) obj);
	}

	@Override
	public Reservation get(int id_reservation)
	{
		for(Reservation reservation : reservationList)
			if(reservation.getId() == id_reservation) {
				reservationList.remove(reservation);
				break;
			}
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