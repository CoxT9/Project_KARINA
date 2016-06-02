package group8.karina.businessTests.businessTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import group8.karina.Exceptions.DuplicateEntryException;
import group8.karina.application.Services;
import group8.karina.business.AccessCategories;
import group8.karina.objects.Category;
import group8.karina.persistence.DataAccessStub;

import static org.junit.Assert.*;

public class AccessCategoriesTests
{
    AccessCategories accessCategories;
    DataAccessStub dataAccess;

    @Before
    public void setUp()
    {
        dataAccess = Services.getDataAccess();
        accessCategories = new AccessCategories();
    }

    @After
    public void tearDown()
    {
        Services.closeDataAccess();
    }

    @Test
    public void insert_category_inserts_categories()
    {
        Category expectedCategory = new Category("definitelynotinthedatabase", true);
        Category actualCategory;

        try
        {
            accessCategories.insertCategory(expectedCategory);
        }
        catch (DuplicateEntryException e)
        {
            fail("inserting a valid Category should not throw an exception");
        }

        actualCategory = dataAccess.getCategoryByNameAndIsExpense(expectedCategory.getCategoryName(), expectedCategory.isExpense());

        assertNotNull(actualCategory);
        assertEquals(actualCategory.getCategoryName(),expectedCategory.getCategoryName());
        assertEquals(actualCategory.isExpense(),expectedCategory.isExpense());

    }

    @Test
    public void insert_Category_throws_exception_with_null_CategoryName()
    {
        Category expectedCategory = new Category(null, true);
        try
        {
            accessCategories.insertCategory(expectedCategory);
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
        Category expectedCategory = new Category("", true);
        try
        {
            accessCategories.insertCategory(expectedCategory);
            fail("insert category should throw exception when name is empty");
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
            dataAccess.insertCategory(new Category("thingsthatshouldnotbeinthedatabase", true));
        }
        catch(Exception e)
        {
            fail("insert category should not throw an exception for valid input");
        }

        List<Category> categories = accessCategories.getAllCategories();

        assertEquals(6,categories.size()); //this is 6 because we have some seed data in the database and our inserted data
    }

}