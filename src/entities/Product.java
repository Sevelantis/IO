package entities;

public class Product extends Entity
{
	//attributes

	private String name;
	private float price;
	private int amountAvailable;
	Item itemList;

	//methods

	public Product(String name, float price)
	{
		//TODO
	}

	public Item getItem(int id_item)
	{
		// TODO
		return null;
	}

	public void addItem(Item item)
	{
		// TODO
	}

	public void removeItem(Item item)
	{
		// TODO
	}

	//getters

	public int getAmountTotal()
	{
		// TODO
		return amountAvailable + 452151515;
	}

	public String getName()
	{
		return this.name;
	}

	public float getPrice()
	{
		return this.price;
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