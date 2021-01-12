package interfaces;

import entities.Client;
import entities.Item;
import entities.Product;
import entities.Reservation;

import java.util.Date;
import java.util.List;

public interface IMainManager
{

	/**
	 * 
	 * @param id_product
	 */
	Product searchProduct(int id_product);

	/**
	 * 
	 * @param name
	 */
	List<Product> searchProducts(String name);

	/**
	 *
	 * @param price
	 */
	List<Product> searchProducts(double price);

	/**
	 * 
	 * @param name
	 * @param price
	 * @param numberOfItems
	 */
	public void addProduct(String name, double price, int numberOfItems);

	/**
	 * 
	 * @param id_product
	 * @param name
	 * @param price
	 */
	void modifyProduct(int id_product, String name, double price);

	/**
	 * 
	 * @param id_product
	 */
	void removeProduct(int id_product);

	/**
	 * 
	 * @param id_reservation
	 */
	Reservation searchReservation(int id_reservation);

	/**
	 *
	 * @param id_client
	 * @param dateStart
	 * @param dateEnd
	 * @param itemList
	 */
	public void addReservation(int id_client, Date dateStart, Date dateEnd, List<Item> itemList);

	/**
	 * 
	 * @param id_reservation
	 */
	boolean returnReservation(int id_reservation);

	/**
	 * 
	 * @param id_client
	 */
	Client searchClient(int id_client);

	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param phoneNr
	 * @param email
	 */
	void addClient(String firstName, String lastName, String phoneNr, String email);

	/**
	 * 
	 * @param id_client
	 * @param firstName
	 * @param lastName
	 * @param phoneNr
	 * @param email
	 */
	void modifyClient(int id_client, String firstName, String lastName, String phoneNr, String email);

	/**
	 * 
	 * @param id_product
	 */
	void addItem(int id_product);

	/**
	 * 
	 * @param id_product
	 * @param numberOfItems
	 */
	void addItems(int id_product, int numberOfItems);

	/**
	 * 
	 * @param ids_itemList
	 */
	void changeConditionItems(List<Integer> ids_itemList);

	/**
	 * 
	 * @param id_item
	 */
	void removeItem(int id_item);

	/**
	 * 
	 * @param ids_itemList
	 */
	void removeItems(List<Integer> ids_itemList);

}