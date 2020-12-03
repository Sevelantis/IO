package entities;

public abstract class Entity
{
	//attributes

	protected int id;
	private static int idCounter = 0;

	//methods

	Entity()
	{
		this.id = idCounter++;
	}

	public int getId()
	{
		return this.id;
	}

}