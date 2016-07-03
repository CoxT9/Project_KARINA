package group8.karina;


import junit.framework.Test;
import junit.framework.TestSuite;

import group8.karina.AcceptanceTests.CompareSpendingBetweenCategoriesTests;
import group8.karina.AcceptanceTests.CompareSpendingBetweenUsersTests;

public class AllAcceptanceTests
{
	public static TestSuite suite;

	public static Test suite()
	{
		suite = new TestSuite("All unit tests");
		suite.addTestSuite(CompareSpendingBetweenCategoriesTests.class);
		suite.addTestSuite(CompareSpendingBetweenUsersTests.class);

		return suite;
	}
}
