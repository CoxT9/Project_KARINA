package group8.karina.objects;

import java.util.Date;
import java.io.Serializable;

public class Transaction implements Serializable, Cloneable
{
	private int transID;
	private Boolean expense;
	private double amount;
	private int categoryID;
	private int userID;
	private Date date;
	private String comments;

	public Transaction(Date newDate, int usr, Boolean exp, double amt, int cat, String com)
	{
		date = newDate;
		expense = exp;
		amount = amt;
		categoryID = cat;
		userID = usr;
		transID = -1;
		comments = com;
	}
	public Transaction(int id, Date newDate, int usr, Boolean exp, double amt, int cat, String com)
	{
		date = newDate;
		expense = exp;
		amount = amt;
		categoryID = cat;
		userID = usr;
		transID = id;
		comments = com;
	}

	public int getTransactionID()
	{
		return (transID);
	}

	public Date getDate() { return (date); }

	public int getUserID()
	{
		return (userID);
	}

	public boolean isExpense()
	{
		return (expense);
	}

	public double getAmount()
	{
		return (amount);
	}

	public int getCategoryID()
	{
		return (categoryID);
	}

	public String getComments()
	{
		return this.comments;

	}

	public Object clone()
	{
		try
		{
			return super.clone();
		}
		catch(CloneNotSupportedException cns)
		{
			System.out.println("Cloning not supported for Transactions!");
			return this;
		}
	}

	public boolean equals(Object object)
	{
		boolean result;
		Transaction t;

		result = false;

		if (object instanceof Transaction)
		{
			t = (Transaction) object;
			if (t.transID == transID)
			{
				result = true;
			}
		}
		return result;
	}


	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	public void setIsExpense(boolean isExpense) { this.expense = isExpense; }

	public void setComments(String comments) { this.comments = comments; }

	public void setAmount(double amount) { this.amount = amount; }

	public void setCategoryID(int categoryID) { this.categoryID = categoryID; }

	public void setDate(Date date) { this.date = date; }

	public void setTransactionID(int transID) { this.transID = transID; }

	public String toString()
	{
		return "$" + getAmount() + " on " + getDate();
	}
}
