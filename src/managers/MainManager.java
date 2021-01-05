package managers;

import interfaces.*;
import entities.*;
import enums.*;
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
	public void addProduct(String name, float price)//int numberOfItems...
	{
		productManager.add(new Product(name, price));
	}

	@Override
	public void modifyProduct(int id_product, String name, int numberOfItems, float price)
	{

	}

	@Override
	public void removeProduct(int id_product)
	{
		productManager.remove(id_product);
	}

	//reservation
	@Override
	public void addReservation(int id_reservation, Date dateStart, Date dateEnd, Status status, List<Integer> ids_itemList)
	{

	}

	@Override
	public Reservation searchReservation(int id_reservation)
	{
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
		itemManager.buildItems(productManager.get(id_product));
	}

	@Override
	public void removeItem(int id_item)
	{

	}

	//getters
	public static MainManager getInstance()
	{
		if(instance==null) instance = new MainManager();
		return instance;
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

	@Override
	public void addItems(int id_product, int numberOfItems)
	{

	}

}