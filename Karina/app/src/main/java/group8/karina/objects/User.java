package group8.karina.objects;

import java.io.Serializable;

public class User implements Serializable
{
	private int userID;
	private String userName;

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

	public User clone()
	{
		User u = new User(userName);
		u.setUserID(this.userID);

		return u;
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
