package group8.karina.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import group8.karina.tests.integrationTests.businessIntegrationTests.AccessCategoriesIntegrationTests;
import group8.karina.tests.integrationTests.businessIntegrationTests.AccessTransactionsIntegrationTests;
import group8.karina.tests.integrationTests.businessIntegrationTests.AccessUsersIntegrationTests;
import group8.karina.tests.integrationTests.persitenceIntegrationTests.DataAccessObjectTests;

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
