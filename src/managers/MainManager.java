package managers;

import entities.Client;
import entities.Item;
import entities.Product;
import entities.Reservation;
import interfaces.IMainManager;

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
		//TODO konflikt merge
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
	public void addReservation(int id_client, Date dateStart, Date dateEnd, List<Item> itemList)
	{
		Reservation reservation = new Reservation(id_client, dateStart, dateEnd, itemList);
		reservationManager.add(reservation);

		//rezerwacja rezerwowanych egzemplarzy
		for(Item item : itemList)
			item.reserveItem(reservation.getId());

		try
		{
			reservationManager.createAgreementFile(reservation);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Reservation searchReservation(int id_reservation)
	{
		return reservationManager.get(id_reservation);
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
		return clientManager.get(id_client);
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
		//todo
		Item item = itemManager.get(id_item);
	}

	//getters
	public static MainManager getInstance()
	{
		if(instance==null) instance = new MainManager();
		return instance;
	}

	@Override
	//TODO na potrzebe symulacji należy tworzyc odpowiednie egzemplarze produktow
	public void addItems(int id_product, int numberOfItems)
	{
		for (int i = 0; i < numberOfItems; i++)
		{
			Item item = new Item(id_product);
			itemManager.add(item);
			productManager.get(id_product).addItem(item);
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