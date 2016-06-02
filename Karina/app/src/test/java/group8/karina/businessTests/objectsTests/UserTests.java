package group8.karina.businessTests.objectsTests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import group8.karina.objects.User;

import static org.junit.Assert.*;

public class UserTests
{
    private final int USER_ORIGINAL_ID = 1;
    private final String USER_ORIGINAL_NAME = "name";
    private final int INITIAL_USER_ID = -1;

    private User user;
    private User otherUser;

    @Before
    public void setUp()
    {
        user = new User(USER_ORIGINAL_NAME);
        user.setUserID(USER_ORIGINAL_ID);

    }
    @Test
    public void equality()
    {

        //equals compares user ids
        otherUser = new User("otherName");
        otherUser.setUserID(USER_ORIGINAL_ID);

        assertTrue(otherUser.equals(user));
        otherUser.setUserID(2);
        assertFalse(otherUser.equals(user));

        assertFalse(user.equals(USER_ORIGINAL_ID));
        assertFalse(user.equals(null));
    }

    @Test
    public void GettersGetCorrectValues()
    {
        otherUser = new User("name");
        assertEquals(otherUser.getUserID(), INITIAL_USER_ID);

        assertEquals(user.getUserID(), USER_ORIGINAL_ID);
        assertEquals(user.getUserName(), USER_ORIGINAL_NAME);
    }

    @Test
    public void SetIDSetsIDs()
    {
        user.setUserID(2);
        assertNotEquals(user.getUserID(), USER_ORIGINAL_ID);
        assertEquals(user.getUserID(), 2);

        user.setUserID(USER_ORIGINAL_ID);

    }

    @Test
    public void testCloning()
    {
        otherUser = user.clone();
        assertEquals(user.getUserID(), USER_ORIGINAL_ID);
        assertEquals(user.getUserName(), USER_ORIGINAL_NAME);

        otherUser.setUserID(2);//separate from original
        assertFalse(user.equals(otherUser));
    }

}