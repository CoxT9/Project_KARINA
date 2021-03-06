package group8.karina.objects;

import java.io.Serializable;

public class User implements Serializable, Cloneable
{
	private int userID;
	private String userName;

	public User(int newUserID,String newUserName)
	{
		userName = newUserName;
		userID = newUserID;
	}
	public User(String newUserName)
	{
		userName = newUserName;
		userID = -1;
	}

	public int getUserID()
	{
		return (userID);
	}

	public String getUserName()
	{
		return (userName);
	}

	public void setUserID(int newID)
	{
		userID = newID;
	}

	public Object clone()
	{
		try
		{
			return super.clone();
		}
		catch(CloneNotSupportedException cns)
		{
			System.out.println("Cloning Not Supported For Users!");
			return this;
		}
	}

	public boolean equals(Object object)
	{
		boolean result;
		User u;

		result = false;

		if (object instanceof User)
		{
			u = (User) object;
			if (u.userID == userID)
			{
				result = true;
			}
		}
		return result;
	}

	public String toString()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}
}
