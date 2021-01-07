package managers;

import interfaces.*;
import entities.*;
import enums.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class MainManager implements IMainManager
{
	//attributes
	private static MainManager instance;
	ProductManager productManager;
	ItemManager itemManager;
	ReservationManager reservationManager;
	ClientManager clientManager;

	//methods

	public MainManager()
	{
		//init managers
		productManager = ProductManager.getInstance();
		itemManager = ItemManager.getInstance();
		reservationManager = ReservationManager.getInstance();
		clientManager= ClientManager.getInstance();
	}

	//product
	@Override
	public Product searchProduct(int id_product)
	{
		return productManager.get(id_product);
	}

	@Override
	public List<Product> searchProducts(String name)
	{
		return productManager.search(name, 0);
	}

	@Override
	public List<Product> searchProducts(float price)
	{
		return productManager.search("", price);
	}

	@Override
	public void addProduct(String name, float price, int numberOfItems)
	{
		Product product = new Product(name, price);
		productManager.add(product);
		addItems(product.getId(), numberOfItems);
	}

	@Override
	public void modifyProduct(int id_product, String name, int numberOfItems, float price)
	{
		Product product = productManager.get(id_product);
		product.setName(name);
		product.setPrice(price);
		// TODO użyć amountAvailable i numberOfItems
	}

	@Override
	public void removeProduct(int id_product)
	{
		productManager.remove(id_product);
	}

	//reservation
	@Override
	public void addReservation(int id_reservation, Date dateStart, Date dateEnd, Status s, List<Integer> ids_itemList)
	{




		// TODO w ogóle czemu tutaj jest List< --->>Integer <<<---  > ids_itemList, a nie List<Items>
		// TODO dlaczemu nie ma id_wypożyczającego
		// TODO w ogóle po cholere tutaj jest wprowadzany status jak to miało chyba być wprowadzone właśnie w tej funkcji??????
		// TODO dlaczego id_reservation jest argumentem metody jak to też ma być w tej funkcji nadawane chyba (albo nie, wytłumacz mi)
		//reservationManager.add(new Reservation(id_reservation, dateStart, dateEnd, ids_itemList));

		/////// Do wywalenia test

		Client client = new Client("Adam", "Małysz", "592876090", "mammalego@.gumeil.dom");
		addProduct("Zabawkowe Narty", (float) 15.99, 5);
		addProduct("Pistolet maszynowy RKM", (float) 2999.99, 6);
		addProduct("Hamburger 100% plastik", (float) 4.99, 9);

		Reservation res = new Reservation(client.getId(), new Date(2019, 12, 10), new Date(2020, 12, 10),
				itemManager.itemList);

		try {
			reservationManager.createAgreementFile(res);
		} catch (IOException e) {
			e.printStackTrace();
		}

		///////


	}

	@Override
	public Reservation searchReservation(int id_reservation)
	{
		//TODo
		return null;
	}

	@Override
	public void returnReservation(int id_reservation)
	{
		reservationManager.get(id_reservation);
	}

	//client
	@Override
	public Client searchClient(int id_client)
	{
		//TODo
		return null;
	}

	@Override
	public void addClient(String firstName, String lastName, String phoneNr, String email)
	{
		clientManager.add(new Client(firstName, lastName, phoneNr, email));
	}

	@Override
	public void modifyClient(int id_client, String firstName, String lastName, String phoneNr, String email)
	{
		Client client = clientManager.get(id_client);
		client.setFirstName(firstName);
		client.setLastName(lastName);
		client.setPhoneNr(phoneNr);
		client.setEmail(email);
	}

	//items
	@Override
	public void addItem(int id_product)
	{
		itemManager.add(new Item(id_product));
		//todo
	}

	@Override
	public void removeItem(int id_item)
	{
		Item item = itemManager.get(id_item);
	}

	//getters
	public static MainManager getInstance()
	{
		if(instance==null)
			instance = new MainManager();
		return instance;
	}

	@Override
	public void addItems(int id_product, int numberOfItems)
	{
		for (int i = 0; i < numberOfItems; i++) {
			itemManager.add(new Item(id_product));
		}
	}

	//----------------------- handled by database ---------------------

	@Override
	public void changeConditionItems(List<Integer> ids_itemList)
	{

	}

	@Override
	public void removeItems(List<Integer> ids_itemList)
	{

	}

}