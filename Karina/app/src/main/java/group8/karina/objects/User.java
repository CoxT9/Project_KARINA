package group8.karina.objects;

public class User
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

    public User clone() { return new User( userName ); }

    public boolean equals(Object object)
    {
        boolean result;
        User u;

        result = false;

        if (object instanceof User)
        {
            u = (User) object;
            if (u.userID==userID)
            {
                result = true;
            }
        }
        return result;
    }

}
