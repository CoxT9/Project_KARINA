package group8.karina.tests.unitTests.objectsTests;

import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import group8.karina.objects.Category;

public class CategoryTests extends junit.framework.TestCase
{
	private final int CAT_ORIGINAL_ID = 1;
	private final String CAT_ORIGINAL_NAME = "expense";
	private final boolean CAT_ORIGINAL_ISEXP = true;
	private final int INITIAL_CAT_ID = -1;

	private Category cat;
	private Category otherCat;
	private Object cloneCat;

	@Before
	public void setUp()
	{
		cat = new Category(CAT_ORIGINAL_NAME, CAT_ORIGINAL_ISEXP);
		cat.setCategoryID(CAT_ORIGINAL_ID);

	}

	public void testEquality()
	{

		//equals compares category ids
		otherCat = new Category("otherstuff", false);
		otherCat.setCategoryID(CAT_ORIGINAL_ID);

		assertTrue(otherCat.equals(cat));
		otherCat.setCategoryID(2);
		assertFalse(otherCat.equals(cat));

		assertFalse(cat.equals(CAT_ORIGINAL_ID));
		assertFalse(cat.equals(null));
	}

	public void testGettersGetCorrectValues()
	{
		otherCat = new Category("name", true);
		assertEquals(otherCat.getCategoryID(), INITIAL_CAT_ID);

		assertEquals(cat.isExpense(), CAT_ORIGINAL_ISEXP);
		assertEquals(cat.getCategoryID(), CAT_ORIGINAL_ID);
		assertEquals(cat.getCategoryName(), CAT_ORIGINAL_NAME);
	}

	public void testSetIDSetsIDs()
	{
		cat.setCategoryID(2);
		assertNotEquals(cat.getCategoryID(), CAT_ORIGINAL_ID);
		assertEquals(cat.getCategoryID(), 2);

		cat.setCategoryID(CAT_ORIGINAL_ID);

	}

	public void testCloning()
	{
		cloneCat = cat.clone();
		assertTrue(cloneCat instanceof Category);
		otherCat = (Category)cloneCat;
		assertEquals(otherCat.isExpense(), CAT_ORIGINAL_ISEXP);
		assertEquals(otherCat.getCategoryID(), CAT_ORIGINAL_ID);
		assertEquals(otherCat.getCategoryName(), CAT_ORIGINAL_NAME);
		assertNotSame(cat, otherCat);

		otherCat.setCategoryID(2);//separate from original
		assertFalse(cat.equals(otherCat));
	}

}