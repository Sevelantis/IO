package entities;

import enums.Status;

import java.util.Date;
import java.util.List;

public class Reservation extends Entity
{
	//attributes

	private final int id_client;
	private Date dateStart;
	private Date dateEnd;
	private float price;
	private Status status;
	private final List<Item> itemList;

	//methods

	public Reservation(int id_client, Date dateEnd, Date dateStart, List<Item> items)
	{
		this.id_client = id_client;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.itemList = items;
	}

	//getters

	public int getId_client()
	{
		return this.id_client;
	}

	public List<Item> getItems()
	{
		return itemList;
	}

	public Date getDateStart()
	{
		return this.dateStart;
	}

	public Date getDateEnd()
	{
		return this.dateEnd;
	}

	public float getPrice()
	{
		return this.price;
	}

	public Status getStatus()
	{
		return this.status;
	}

	//setters

	public void setDateStart(Date dateStart)
	{
		this.dateStart = dateStart;
	}

	public void setDateEnd(Date dateEnd)
	{
		this.dateEnd = dateEnd;
	}

	public void setPrice(float price)
	{
		this.price = price;
	}

	public void setStatus(Status status)
	{
		this.status = status;
	}

}