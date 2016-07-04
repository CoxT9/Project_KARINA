package group8.karina.tests.testHelpers;

import group8.karina.persistence.DataAccessObject;

public class TestDataAccessObject extends DataAccessObject
{
	public TestDataAccessObject(String dbName)
	{
		super(dbName);
	}

	public void open(String dbPath)
	{
		super.open(dbPath);

		removeExistingData();
		populateSeedData();
	}

	private void removeExistingData()
	{
		String statement = "DELETE FROM Transactions;";
		statement += " DELETE FROM Categories;";
		statement += " DELETE FROM Users;";

		updateDatabase(statement);
	}

	private void populateSeedData()
	{
		String statement = "INSERT INTO USERS VALUES(1,'Default');";
		statement += "INSERT INTO USERS VALUES(2,'Jon');";
		statement += "INSERT INTO USERS VALUES(3,'Aria');";
		statement += "INSERT INTO USERS VALUES(4,'Bran');";
		statement += "INSERT INTO CATEGORIES VALUES(0,'Default',FALSE);";
		statement += "INSERT INTO CATEGORIES VALUES(1,'Default',TRUE);";
		statement += "INSERT INTO CATEGORIES VALUES(2,'groceries',TRUE);";
		statement += "INSERT INTO CATEGORIES VALUES(3,'weapons',TRUE);";
		statement += "INSERT INTO CATEGORIES VALUES(4,'income',FALSE);";
		statement += "INSERT INTO TRANSACTIONS VALUES(1,'2016-06-11',440.0E0,TRUE,2,4,'blahblah');";
		statement += "INSERT INTO TRANSACTIONS VALUES(2,'2016-06-11',380.95E0,FALSE,4,2,'blahblah');";
		statement += "INSERT INTO TRANSACTIONS VALUES(3,'2016-06-11',95.5E0,TRUE,2,2,'blahblah');";

		updateDatabase(statement);
	}


}
