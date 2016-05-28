package group8.karina.businessTests.businessTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import group8.karina.Exceptions.DuplicateEntryException;
import group8.karina.application.Services;
import group8.karina.business.AccessUsers;
import group8.karina.objects.User;
import group8.karina.persistence.DataAccessStub;

import static org.junit.Assert.*;

public class AccessUsersTests
{
    AccessUsers accessUsers;
    DataAccessStub dataAccess;

    @Before
    public void setUp()
    {
        dataAccess = Services.getDataAccess();
        accessUsers = new AccessUsers();

    }

    @After
    public void tearDown()
    {
        Services.closeDataAccess();
    }

    @After
    public void tearDown()
    {
        Services.closeDataAccess();
    }

    @Test
    public void insert_user_inserts_user()
    {
        User expectedUser = new User("bob");
        User actualUser;

        try
        {
            accessUsers.insertUser(expectedUser);
        }
        catch (DuplicateEntryException e)
        {
            fail("inserting a valid user should not throw an exception");
        }

        actualUser = dataAccess.getUserByName(expectedUser.getUserName());

        assertNotNull(actualUser);
        assertEquals(actualUser.getUserName(),expectedUser.getUserName());
    }

    @Test
    public void insert_user_throws_exception_with_null_username()
    {
        User expectedUser = new User(null);
        try
        {
            accessUsers.insertUser(expectedUser);
            fail("insert user should throw exception when name is null");
        }
        catch(IllegalArgumentException iae)
        {
            //this is what we want
        }
        catch(Exception e)
        {
            fail("Expected IllegalArgumentException when inserting null name");
        }
    }

    @Test
    public void insert_user_throws_exception_with_empty_username()
    {
        User expectedUser = new User("");
        try
        {
            accessUsers.insertUser(expectedUser);
            fail("insert user should throw exception when name is empty");
        }
        catch(IllegalArgumentException iae)
        {
            //this is what we want
        }
        catch(Exception e)
        {
            fail("Expected IllegalArgumentException when inserting empty name");
        }
    }

    @Test
    public void get_users_returns_right_users()
    {
        try
        {
            dataAccess.insertUser(new User("billybob"));
        }
        catch(Exception e)
        {
            fail("insert user should not throw an exception for valid input");
        }

        List<User> users = accessUsers.getUsers();

        assertEquals(6,users.size()); //this is 6 because we have some seed data in the database and out inserted data
    }

}
