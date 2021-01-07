package entities;

import enums.Condition;

public class Item extends Entity
{
	private static int idCounter = 0;
	//attributes

	private int id_product;
	private int id_reservation = -1; //-1 znaczy, ze egzemplarz nie jest wypozyczony
	private Condition condition;

	//methods

	public Item(int id_product)
	{
		this.id = idCounter++;
		this.id_product = id_product;
		this.condition = Condition.dobry; //domyslnie kazdy jest dobry
	}


	public void reserveItem(int id_reservation)
	{
		this.id_reservation = id_reservation;
	}

	public void returnItem()
	{
		this.id_reservation = -1;
	}

	//getters

	public int getId_product()
	{
		return this.id_product;
	}

	public int getId_reservation()
	{
		return this.id_reservation;
	}

	public Condition getCondition()
	{
		return this.condition;
	}

	//setters

	public void setCondition(Condition condition)
	{
		this.condition = condition;
	}

	public void setId_reservation(int id_reservation)
	{
		this.id_reservation = id_reservation;
	}

}