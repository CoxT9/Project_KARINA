package group8.karina.businessTests.objectsTests;

import org.junit.Test;

import group8.karina.objects.User;

import static org.junit.Assert.*;

public class UserTests extends junit.framework.TestCase
{
    public void test_users_with_equal_names_are_equal()
    {
        String name = "name";

        User u1 = new User(name);
        User u2 = new User(name);

        assertEquals(u1,u2);
    }

    public void test_users_with_different_names_are_not_equal()
    {
        User u1 = new User("not");
        User u2 = new User("equal");

        assertNotEquals(u1,u2);
    }

    public void test_user_with_null_name_is_not_equal()
    {
        User u1 = new User(null);
        User u2 = new User("not null");

        assertNotEquals(u1,u2);
    }

    @Test
    public void test_comparing_a_user_to_null_returns_false()
    {
        assertNotEquals(new User("name"),null);
        assertNotEquals(null,new User("name"));
    }
}
