package managers;

import entities.Client;
import entities.Item;
import entities.Product;
import entities.Reservation;
import interfaces.IManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReservationManager implements IManager
{
	//attributes

	private static ReservationManager instance;
	List<Reservation> reservationList = new Vector<>();

	//methods

	@Override
	public void add(Object obj)
	{
		reservationList.add(	(Reservation) obj	);
		updateDatabaseFromLocal();
	}

	@Override
	public Reservation get(int id_reservation)
	{
		for(Reservation reservation : reservationList)
			if(reservation.getId() == id_reservation)
			{
				return reservation;
			}
		return null;
	}

	public void generateAgreement(Reservation reservation) throws IOException
	{
		String folderAgreementsName = "umowy";
		String nameOfAgreement = ClientManager.getInstance().get(reservation.getId_client()).getFirstName()
                + "_" + ClientManager.getInstance().get(reservation.getId_client()).getLastName() + " - umowa "
				+ reservation.getId() + ".txt";


		try {
			Files.createDirectory(Paths.get("C:/" + folderAgreementsName));
		}
		catch (IOException ignored) { }

		MainManager m = MainManager.getInstance();
		ClientManager c = m.clientManager;
		Client client = c.get(reservation.getId_client());

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<String> protocol = Arrays.asList(
				"=== WYPORZYCZALNIA KOSTIUMÓW ===",
				"UMOWA AKTU WYPORZYCZENIA " + "id_reservation = " + reservation.getId(),
				"",
				"Wypożyczający zobowiązuje się do oddania wypożyczonych egzemplarzy " +
                        "w terminie oraz\n w stanie nie gorszym niż zostały mu otrzymane. ",
				"Rezerwacja wykonana dnia: " + dateFormat.format(reservation.getDateStart()),
				"Data do zwrotu: " + dateFormat.format(reservation.getDateEnd()),
				"Wypożyczający: " + client.getFirstName() + " " + client.getLastName(),
				"Numer telefonu: " + client.getPhoneNr(),
				"Email: " + client.getEmail(),
				"",
				"Wypozyczone rekwizyty: "
		);

		List<String> agreement = new ArrayList<>(protocol);

		HashMap<Integer, Integer> products = new HashMap<Integer, Integer>();

		for (Item item: reservation.getItems()) {
			Integer id = item.getId_product();
			if(products.containsKey(id))
				products.put(id, products.get(id) + 1);
			else
				products.put(id, 1);
		}

		for (Integer id: products.keySet())
		{
			Product product = MainManager.getInstance().productManager.get(id);
			String sb = "Nazwa: " + product.getName() + " Cena: " + String.format("%.2f", product.getPrice()) + " x" + products.get(id);
			agreement.add(sb);
		}

		agreement.add("Koszt łączny rezerwacji: " + String.format("%.2f", reservation.getPrice()) + "zł");

		Files.write(Paths.get("C:/" + folderAgreementsName + "/" + nameOfAgreement), agreement,
				StandardCharsets.UTF_8);
	}

	//getters

	public static ReservationManager getInstance()
	{
		if(instance == null) instance = new ReservationManager();
		return instance;
	}

	public List<Reservation> getReservationList()
	{
		return this.reservationList;
	}

	@Override
	public boolean updateDatabaseFromLocal() {
		return true;
	}

	@Override
	public boolean updateLocalFromDatabase() {
		return true;
	}

	// methods handled by database -------------
	// .
}