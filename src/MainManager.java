import interfaces.*;
import managers.*;
import entities.*;
import enums.*;

import java.util.Date;
import java.util.List;

public class MainManager implements IMainManager
{
	//attributes

	ProductManager productManager;
	ItemManager itemManager;
	ReservationManager reservationManager;
	ClientManager clientManager;

	//methods

	MainManager()
	{
		//init managers
		productManager = ProductManager.getInstance();
		itemManager = ItemManager.getInstance();
		reservationManager = ReservationManager.getInstance();
		clientManager= ClientManager.getInstance();
	}

	@Override
	public Product searchProduct(int id_product)
	{
		return null;
	}

	@Override
	public List<Product> searchProducts(String name)
	{
		return null;
	}

	@Override
	public List<Product> searchProducts(float price)
	{
		return null;
	}

	@Override
	public void addProduct(String id_product, int numberOfItems, float price)
	{

	}

	@Override
	public void modifyProduct(int id_product, String name, int numberOfItems, float price)
	{

	}

	@Override
	public void removeProduct(int id_product)
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

	}

	@Override
	public Client searchClient(int id_client)
	{
		return null;
	}

	@Override
	public void addClient(String firstName, String lastName, String phoneNr, String email)
	{

	}

	@Override
	public void modifyClient(int id_client, String firstName, String lastName, String phoneNr, String email)
	{

	}

	@Override
	public void addItem(int id_product)
	{

	}

	@Override
	public void removeItem(int id_item)
	{

	}

	@Override
	public void addReservation(int id_reservation, Date dateStart, Date dateEnd, Status status, List<Integer> ids_itemList)
	{

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