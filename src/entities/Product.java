package entities;

import enums.Condition;

import java.util.List;
import java.util.Vector;

////todo? do produktu moze jednak niekoniecznie useful.
//class ProductException extends Exception
//{
//	private static final long serialVersionUID = 1L;
//	public ProductException(String message)
//	{
//		super(message);
//	}
//}

public class Product extends Entity
{
	private static int idCounter = 0;
	//attributes

	private String name;
	private float price;
	List<Item> itemList = new Vector<>();

	//methods

	public Product(String name, float price)
	{
		this.id = idCounter++;
		this.name = name;
		this.price = price;
	}

	public Item getItem(int id_item)
	{
		for(Item i : itemList)
			if(i.getId() == id_item)
				return i;
		return null;
	}

	public void addItem(Item item)
	{
		itemList.add(item);
	}

	public void removeItem(Item item)
	{
		itemList.remove(item);
	}

	public void removeItem(int id_item)
	{
		itemList.remove(id_item);
	}

	//getters

	public int getNumberOfAvailableItems()
	{
		int numberOfAvailableItems = 0;
		for (Item i : itemList)
			if(i.getId_reservation() == -1 && i.getCondition() == Condition.dobry)	//egzemplarze niewypozyczone i dobre = egzemplarze dostepne
				numberOfAvailableItems++;

		return numberOfAvailableItems;
	}

	public int getNumberOfReservedItems()
	{
		int numberOfReservedItems = 0;
		for(Item i: itemList)
		{
			if(i.getId_reservation() != -1) // zliczaj egzemplarze wypozyczone
				numberOfReservedItems++;
		}

		return numberOfReservedItems;
	}

	public boolean isAvailable()
	{
		return getNumberOfAvailableItems() > 0;
	}

	public void reserveInCart()
	{
		for(Item i : itemList)	//ten kod rezerwuje pierwszy znaleziony egzemplarz ktory jest niezarezerwowany i dobry.
			if(i.getId_reservation() == -1 && i.getCondition() == Condition.dobry)
			{
				i.reserveItem(-2);	//poniewaz rezerwacja tworzona bedzie po zakonczeniu rezerwacji w koszyku
												// 	jest brak rezerwacji
												// 		wprowadzono tymczasowe id_rezerwacji = -2
												//			ktore oznacza, ze egzemplarz znajduje sie w koszyku
												//				wartosc ta zostanie nadpisana w momencie stworzenia faktycznej rezerwacji
				break;
			}
	}

	public void  returnInCart()	//return	// ten kod zwalnia pierwszy znaleziony egzemplarz ktory aktualnie jest w koszyku
	{
		for(Item i : itemList)
			if(i.getId_reservation() == -2)	//warunek bycia w koszyku
			{
				i.returnItem();	//ustawia id rezerwacji na -1
				break;
			}
	}

	public String getName()
	{
		return this.name;
	}

	public float getPrice()
	{
		return this.price;
	}

	public List<Item> getItems()
	{
		return this.itemList;
	}

	@Override
	public String toString()
	{
		String str = "[Product: " + name + ", id: " + id + ", price: " + price + "]";
		return str;
	}

	//setters

	public void setName(String name)
	{
		this.name = name;
	}

	public void setPrice(float price)
	{
		this.price = price;
	}

}