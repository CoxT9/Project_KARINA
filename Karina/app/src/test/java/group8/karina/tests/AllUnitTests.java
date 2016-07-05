package group8.karina.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import group8.karina.tests.businessTests.AccessCategoriesTests;
import group8.karina.tests.businessTests.AccessTransactionsTests;
import group8.karina.tests.businessTests.AccessUsersTests;
import group8.karina.tests.objectsTests.CategoryTests;
import group8.karina.tests.objectsTests.TransactionTests;
import group8.karina.tests.objectsTests.UserTests;
import group8.karina.tests.integrationTests.persistenceIntegrationTests.DataAccessObjectTests;

public class AllUnitTests
{
	public static TestSuite suite;

	public static Test suite()
	{
		suite = new TestSuite("All unit tests");
		testObjects();
		testBusiness();
		testPersistance();

		return suite;
	}

	private static void testObjects()
	{
		suite.addTestSuite(UserTests.class);
		suite.addTestSuite(TransactionTests.class);
		suite.addTestSuite(CategoryTests.class);
	}

	private static void testBusiness()
	{
		suite.addTestSuite(AccessUsersTests.class);
		suite.addTestSuite(AccessCategoriesTests.class);
		suite.addTestSuite(AccessTransactionsTests.class);
	}

	private static void testPersistance()
	{
		suite.addTestSuite(DataAccessObjectTests.class);
	}
}
