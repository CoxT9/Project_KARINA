package group8.karina.application;

import group8.karina.persistence.DataAccessObject;
import group8.karina.persistence.DataAccessStub;
import group8.karina.persistence.Database;

public class DatabaseService
{
	public static String dbName = "database5";
	public static String dbPathName = "database/";
	private static Database dataAccessService = null;

	public static Database createDataAccess()
	{
		if (dataAccessService == null)
		{
			dataAccessService = new DataAccessObject(dbName);
			dataAccessService.open(dbPathName+dbName);
		}
		return dataAccessService;
	}

	public static void setDBPathName(String pathName)
	{
		System.out.println("Setting DB path to: " + pathName);
		dbPathName = pathName;
	}

	public static void openDataAccess()
	{
		dataAccessService.open(dbPathName+dbName);
	}

	public static Database getDataAccess()
	{
		if (dataAccessService == null)
		{
			return createDataAccess();
		}

		return dataAccessService;
	}

	public static void closeDataAccess()
	{
		if (dataAccessService != null)
		{
			dataAccessService.close();
		}
		dataAccessService = null;
	}

	public static void setDatabase(Database d)
	{
		dataAccessService = d;
	}

	public static void setDbName(String name)
	{
		dbName = name;
	}
}

