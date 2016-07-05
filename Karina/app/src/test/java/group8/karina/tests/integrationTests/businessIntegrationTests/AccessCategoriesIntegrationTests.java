package group8.karina.tests.integrationTests.businessIntegrationTests;

import org.junit.After;
import org.junit.Before;

import java.util.List;

import group8.karina.Exceptions.DuplicateEntryException;
import group8.karina.Exceptions.unfoundResourceException;
import group8.karina.application.DatabaseService;
import group8.karina.business.AccessCategories;
import group8.karina.objects.Category;
import group8.karina.persistence.Database;
import group8.karina.tests.testHelpers.TestDataAccessObject;

public class AccessCategoriesIntegrationTests extends junit.framework.TestCase
{
	AccessCategories accessCategories;
	Database dataAccess;

	@Before
	public void setUp()
	{
		dataAccess = new TestDataAccessObject("db");
		DatabaseService.setDatabase(dataAccess);
		DatabaseService.setDBPathName("src/main/assets/db/");
		DatabaseService.openDataAccess();
		dataAccess = DatabaseService.getDataAccess();
		accessCategories = new AccessCategories();
	}

	@After
	public void tearDown()
	{
		DatabaseService.closeDataAccess();
	}

	public void testInsertCategoryInsertsCategories()
	{
		Category expectedCategory = new Category("definitelynotinthedatabase", true);
		Category actualCategory;

		try
		{
			accessCategories.insertCategory(expectedCategory);
		} catch (DuplicateEntryException e)
		{
			fail("inserting a valid Category should not throw an exception");
		}

		actualCategory = dataAccess.getCategoryByNameAndIsExpense(expectedCategory.getCategoryName(), expectedCategory.isExpense());

		assertNotNull(actualCategory);
		assertEquals("definitelynotinthedatabase", actualCategory.getCategoryName());
		assertEquals(true, actualCategory.isExpense());

	}

	public void testInsertCategoryThrowsExceptionWithNullCategoryName()
	{
		Category expectedCategory = new Category(null, true);
		try
		{
			accessCategories.insertCategory(expectedCategory);
			fail("insert user should throw exception when name is null");
		} catch (IllegalArgumentException iae)
		{
			//this is what we want
		} catch (Exception e)
		{
			fail("Expected IllegalArgumentException when inserting null name");
		}
	}

	public void testInsertUserThrowsExceptionWithEmptyUsername()
	{
		Category expectedCategory = new Category("", true);
		try
		{
			accessCategories.insertCategory(expectedCategory);
			fail("insert category should throw exception when name is empty");
		} catch (IllegalArgumentException iae)
		{
			//this is what we want
		} catch (Exception e)
		{
			fail("Expected IllegalArgumentException when inserting empty name");
		}
	}

	public void testGetUsersReturnsRightUsers()
	{
		try
		{
			dataAccess.insertCategory(new Category("thingsthatshouldnotbeinthedatabase", true));
		} catch (Exception e)
		{
			fail("insert category should not throw an exception for valid input");
		}

		List<Category> categories = accessCategories.getAllCategories();

		assertEquals(7, categories.size()); //this is 6 because we have some seed data in the database and our inserted data
	}

	public void testDeleteCategoryByIdDeletes()
	{
		Category c = new Category("Stuff", true);

		try
		{
			dataAccess.insertCategory(c);
		} catch (Exception e)
		{
			fail("This should not throw an exception");
		}

		c = dataAccess.getCategoryByNameAndIsExpense("Stuff", true);

		accessCategories.deleteCategoryById(c.getCategoryID());
		assertNull(dataAccess.getCategoryByNameAndIsExpense(c.getCategoryName(), c.isExpense()));
	}

	public void testUpdateCategoryUpdatesCategory()
	{
		Category c = new Category("Stuff", true);

		try
		{
			dataAccess.insertCategory(c);
		} catch (Exception e)
		{
			fail("This should not throw an exception");
		}

		c = dataAccess.getCategoryByNameAndIsExpense("Stuff", true);
		c.setCategoryName("Things");

		try
		{
			accessCategories.updateCategory(c);
		} catch (unfoundResourceException e)
		{
			fail("This should not throw an exception");
		}

		assertEquals("Things", dataAccess.getCategoryById(c.getCategoryID()).getCategoryName());
	}

}
