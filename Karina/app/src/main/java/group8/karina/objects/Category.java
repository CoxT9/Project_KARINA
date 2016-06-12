package group8.karina.objects;

import java.io.Serializable;

public class Category implements Serializable
{
	private int catID;
	private String catName;
	private boolean catExpense;

	public Category(String newCatName, Boolean exp)
	{
		catName = newCatName;
		catExpense = exp;
		catID = -1;

	}
	public Category(int newCatID, String newCatName, Boolean exp)
	{
		catName = newCatName;
		catExpense = exp;
		catID = newCatID;

	}

	public int getCategoryID()
	{
		return catID;
	}

	public void setCategoryID(int newID)
	{
		catID = newID;
	}

	public String getCategoryName()
	{
		return catName;
	}

	public void setCategoryName(String categoryName) { catName = categoryName; }

	public boolean isExpense() {return catExpense;}

	public Category clone()
	{
		Category c = new Category(this.catName, this.catExpense);
		c.setCategoryID(this.getCategoryID());
		return c;
	}

	public boolean equals(Object object)
	{
		boolean result;
		Category c;

		result = false;

		if (object instanceof Category)
		{
			c = (Category) object;
			if (c.catID == catID)
			{
				result = true;
			}
		}
		return result;
	}

	public String toString() { return catName; }
}
