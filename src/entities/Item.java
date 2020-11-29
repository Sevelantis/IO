package entities;

import enums.Condition;

public class Item extends Entity
{
	//attributes

	private int id_product;
	private int id_reservation;
	private Condition condition;

	//methods

	public Item(int id_product)
	{
		// TODO
	}


	public void reserveItem(int id_reservation)
	{
		// TODO
	}

	public void returnItem()
	{
		// TODO
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