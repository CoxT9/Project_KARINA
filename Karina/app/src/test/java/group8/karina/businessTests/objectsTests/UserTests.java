package group8.karina.businessTests.objectsTests;

import org.junit.Test;

import group8.karina.objects.User;

import static org.junit.Assert.*;

public class UserTests
{
    @Test
    public void users_with_equal_names_are_equal()
    {
        String name = "name";

        User u1 = new User(name);
        User u2 = new User(name);

        assertEquals(u1,u2);
    }

    @Test
    public void users_with_different_names_are_not_equal()
    {
        User u1 = new User("not");
        User u2 = new User("equal");

        assertNotEquals(u1,u2);
    }

    @Test
    public void user_with_null_name_is_not_equal()
    {
        User u1 = new User(null);
        User u2 = new User("not null");

        assertNotEquals(u1,u2);
    }

    @Test
    public void comparing_a_user_to_null_returns_false()
    {
        assertNotEquals(new User("name"),null);
        assertNotEquals(null,new User("name"));
    }
}
