package group8.karina.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import group8.karina.tests.integrationTests.AccessCategoriesIntegrationTests;
import group8.karina.tests.integrationTests.AccessTransactionsIntegrationTests;
import group8.karina.tests.integrationTests.AccessUsersIntegrationTests;
import group8.karina.tests.persistenceTests.DataAccessObjectTests;

public class AllIntegrationTests
{
	public static TestSuite suite;

	public static Test suite()
	{
		suite = new TestSuite("All integration tests");
		testIntegrationBusiness();
		testIntegrationPersistance();

		return suite;
	}

	private static void testIntegrationBusiness()
	{

		suite.addTestSuite(AccessUsersIntegrationTests.class);
		suite.addTestSuite(AccessCategoriesIntegrationTests.class);
		suite.addTestSuite(AccessTransactionsIntegrationTests.class);
	}

	private static void testIntegrationPersistance()
	{
		suite.addTestSuite(DataAccessObjectTests.class);
	}
}
