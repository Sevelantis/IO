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

	public void generateAgreement(int id_reservation){
		Reservation reservation = null;
		for(Reservation r : reservationList)
			if(r.getId() == id_reservation) {
				reservation = r;
				break;
			}

		if(reservation != null) try {
			createAgreementFile(reservation);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// TODO should be private
	public void createAgreementFile(Reservation reservation) throws IOException
	{
		String folderAgreementsName = "umowy";
		String nameOfAgreement = reservation.getId_client() + "_" + reservation.getId() + ".txt";


		try {
			Files.createDirectory(Paths.get("C:/" + folderAgreementsName));
		}
		catch (IOException ignored) { }

		MainManager m = MainManager.getInstance();
		ClientManager c = m.clientManager;
		Client client = c.get(reservation.getId_client());

		List<String> protocol = Arrays.asList(
				"=== WYPORZYCZALNIA KOSTIUMÓW ===",
				"UMOWA AKTU WYPOŻYCZENIA " + "RSRV" + reservation.getId(),
				"",
				"Treść umowy",
				"Rezerwacja wykonanna dnia: " + reservation.getDateStart().toString(),
				"Data do zwrotu: " + reservation.getDateEnd().toString(),
				"Wypożyczający: " + client.getFirstName() + " " + client.getLastName(),
				"Numer telefonu: " + client.getPhoneNr(),
				"Email: " + client.getEmail(),
				"",
				"Wyporzyczone rekwizyty: "
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
			String sb = "Nazwa: " + product.getName() + " Cena: " + product.getPrice() + " x" + products.get(id);
			agreement.add(sb);
		}

		agreement.add("Koszt rezerwacji" + reservation.getPrice());

		Files.write(Paths.get("C:/" + folderAgreementsName + "/" + nameOfAgreement), agreement,
				StandardCharsets.UTF_8);
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