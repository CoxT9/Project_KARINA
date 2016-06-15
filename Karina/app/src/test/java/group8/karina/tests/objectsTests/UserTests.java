package group8.karina.tests.objectsTests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import group8.karina.objects.User;

public class UserTests extends junit.framework.TestCase
{
	private final int USER_ORIGINAL_ID = 1;
	private final String USER_ORIGINAL_NAME = "name";
	private final int INITIAL_USER_ID = -1;

	private User user;
	private User otherUser;
	private Object cloneUser;

	@Before
	public void setUp()
	{
		user = new User(USER_ORIGINAL_NAME);
		user.setUserID(USER_ORIGINAL_ID);

	}

	public void testEquality()
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

	public void testGettersGetCorrectValues()
	{
		otherUser = new User("name");
		assertEquals(otherUser.getUserID(), INITIAL_USER_ID);

		assertEquals(user.getUserID(), USER_ORIGINAL_ID);
		assertEquals(user.getUserName(), USER_ORIGINAL_NAME);
	}

	public void testSetIDSetsIDs()
	{
		user.setUserID(2);
		assertNotEquals(user.getUserID(), USER_ORIGINAL_ID);
		assertEquals(user.getUserID(), 2);

		user.setUserID(USER_ORIGINAL_ID);

	}

	public void testCloning()
	{
		cloneUser = user.clone();
		assertTrue(cloneUser instanceof User);
		otherUser = (User)cloneUser;
		assertEquals(otherUser.getUserID(), USER_ORIGINAL_ID);
		assertEquals(otherUser.getUserName(), USER_ORIGINAL_NAME);
		assertNotSame(user, otherUser);

		otherUser.setUserID(2);//separate from original
		assertFalse(user.equals(otherUser));
	}

}