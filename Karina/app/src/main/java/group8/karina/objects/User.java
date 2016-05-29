package group8.karina.objects;

public class User
{
    private int userID;
    private String userName;

    public User(String newUserName)
    {
        userName = newUserName;
    }

    public int getUserID()
    {
        return (userID);
    }

    public String getUserName()
    {
        return (userName);
    }

    public User clone() { return new User( userName ); }

    public boolean equals(Object object)
    {
        boolean result;
        User u;

        if ( object instanceof User)
        {
            u = (User) object;

            return u.getUserName() != null && this.userName != null && this.userName.equals(u.getUserName());

        }

        return false;
    }

}
