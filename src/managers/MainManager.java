package managers;

import entities.Client;
import entities.Item;
import entities.Product;
import entities.Reservation;
import interfaces.IMainManager;

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
		productManager.add(new Product(name, price, numberOfItems));
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
		// TODO w ogóle czemu tutaj jest List< --->>Integer <<<---  > ids_itemList, a nie List<Items>
			//zmienilem, makes more sense
		// TODO w ogóle po cholere tutaj jest wprowadzany status jak to miało chyba być wprowadzone właśnie w tej funkcji??????
			//usunalem, bylo tak jak mowisz
		// TODO dlaczego id_reservation jest argumentem metody jak to też ma być w tej funkcji nadawane chyba (albo nie, wytłumacz mi)
			//id_client, to mialo byc id_client
		reservationManager.add(new Reservation(id_client, dateStart, dateEnd, itemList));
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
	}

	@Override
	public void removeItem(int id_item)
	{
		Item item = itemManager.get(id_item);
	}

	//getters
	public static MainManager getInstance()
	{
		if(instance==null) instance = new MainManager();
		return instance;
	}

	//----------------------- handled by database ---------------------

	@Override
	public void addItems(int id_product, int numberOfItems)
	{

	}

	@Override
	public void changeConditionItems(List<Integer> ids_itemList)
	{

	}

	@Override
	public void removeItems(List<Integer> ids_itemList)
	{

	}
}