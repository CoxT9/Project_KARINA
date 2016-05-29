package group8.karina.business;
import java.util.List;

import group8.karina.Exceptions.DuplicateEntryException;
import group8.karina.application.KarinaApp;
import group8.karina.application.Services;
import group8.karina.objects.User;
import group8.karina.persistence.DataAccessStub;

public class AccessUsers
{
    private DataAccessStub dataAccess;

    public AccessUsers()
    {
        dataAccess = Services.getDataAccess();
    }

    public List<User> getUsers()
    {
        return dataAccess.getUserSequential();
    }

    public String insertUser(User currentUser) throws DuplicateEntryException
    {
        if(currentUser.getUserName() == null || currentUser.getUserName().isEmpty())
        {
            throw new IllegalArgumentException("Null or empty value for user");
        }

        return dataAccess.insertUser(currentUser);
    }

    public String updateUser(User currentUser)
    {
        return dataAccess.updateUser(currentUser);
    }

    public String deleteUser(User currentUser)
    {
        return dataAccess.deleteUser(currentUser);
    }
}