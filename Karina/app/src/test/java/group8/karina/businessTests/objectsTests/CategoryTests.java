package group8.karina.businessTests.objectsTests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import group8.karina.objects.Category;

import static org.junit.Assert.*;

public class CategoryTests
{
    private final int CAT_ORIGINAL_ID = 1;
    private final String CAT_ORIGINAL_NAME = "expense";
    private final boolean CAT_ORIGINAL_ISEXP = true;
    private final int INITIAL_CAT_ID = -1;

    private Category cat;
    private Category otherCat;

    @Before
    public void setUp()
    {
        cat = new Category(CAT_ORIGINAL_NAME, CAT_ORIGINAL_ISEXP);
        cat.setCategoryID(CAT_ORIGINAL_ID);

    }
    @Test
    public void equality()
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

    @Test
    public void GettersGetCorrectValues()
    {
        otherCat = new Category("name", true);
        assertEquals(otherCat.getCategoryID(), INITIAL_CAT_ID);

        assertEquals(cat.isExpense(),CAT_ORIGINAL_ISEXP);
        assertEquals(cat.getCategoryID(), CAT_ORIGINAL_ID);
        assertEquals(cat.getCategoryName(), CAT_ORIGINAL_NAME);
    }

    @Test
    public void SetIDSetsIDs()
    {
        cat.setCategoryID(2);
        assertNotEquals(cat.getCategoryID(), CAT_ORIGINAL_ID);
        assertEquals(cat.getCategoryID(), 2);

        cat.setCategoryID(CAT_ORIGINAL_ID);

    }

    @Test
    public void testCloning()
    {
        otherCat = cat.clone();
        assertEquals(cat.isExpense(),CAT_ORIGINAL_ISEXP);
        assertEquals(cat.getCategoryID(), CAT_ORIGINAL_ID);
        assertEquals(cat.getCategoryName(), CAT_ORIGINAL_NAME);

        otherCat.setCategoryID(2);//separate from original
        assertFalse(cat.equals(otherCat));
    }

}