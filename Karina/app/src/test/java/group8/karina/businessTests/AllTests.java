package group8.karina.businessTests;

import junit.framework.Test;
import junit.framework.TestSuite;

import group8.karina.businessTests.businessTests.AccessCategoriesTests;
import group8.karina.businessTests.businessTests.AccessUsersTests;
import group8.karina.businessTests.objectsTests.UserTests;

public class AllTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("All tests");
        testObjects();
        testBusiness();

        return suite;
    }

    private static void testObjects()
    {
        suite.addTestSuite(UserTests.class);
    }

    private static void testBusiness()
    {
        suite.addTestSuite(AccessUsersTests.class);
        suite.addTestSuite(AccessCategoriesTests.class);
    }
}
