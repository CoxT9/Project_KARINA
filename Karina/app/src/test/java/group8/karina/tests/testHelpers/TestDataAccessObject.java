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
		statement += "INSERT INTO USERS VALUES(5,'Sansa');";
		statement += "INSERT INTO CATEGORIES VALUES(0,'Default',FALSE)";
		statement += "INSERT INTO CATEGORIES VALUES(1,'Default',TRUE)";
		statement += "INSERT INTO CATEGORIES VALUES(2,'groceries',TRUE)";
		statement += "INSERT INTO CATEGORIES VALUES(3,'weapons',TRUE)";
		statement += "INSERT INTO CATEGORIES VALUES(4,'entertainment',TRUE)";
		statement += "INSERT INTO CATEGORIES VALUES(5,'income',FALSE)";
		statement += "INSERT INTO TRANSACTIONS VALUES(1,'2016-06-11',50.0E0,TRUE,1,1,'blahblah')";
		statement += "INSERT INTO TRANSACTIONS VALUES(2,'2016-06-11',30.0E0,TRUE,3,4,'blahblah')";
		statement += "INSERT INTO TRANSACTIONS VALUES(3,'2016-06-11',80.0E0,TRUE,2,3,'blahblah')";
		statement += "INSERT INTO TRANSACTIONS VALUES(4,'2016-06-11',12.75E0,TRUE,1,2,'blahblah')";
		statement += "INSERT INTO TRANSACTIONS VALUES(5,'2016-06-11',100.0E0,FALSE,5,1,'blahblah')";
		statement += "INSERT INTO TRANSACTIONS VALUES(6,'2016-06-11',51.34E0,FALSE,5,4,'blahblah')";

		updateDatabase(statement);
	}


}
