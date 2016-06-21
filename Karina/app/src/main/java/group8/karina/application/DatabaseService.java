package group8.karina.application;

import group8.karina.persistence.DataAccessObject;
import group8.karina.persistence.DataAccessStub;
import group8.karina.persistence.Database;

public class DatabaseService
{
	private static Database dataAccessService = null;

	public static Database createDataAccess()
	{
		if (dataAccessService == null)
		{
			dataAccessService = new DataAccessObject(KarinaApp.dbName);
			dataAccessService.open(KarinaApp.dbPathName);
		}
		return dataAccessService;
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

	public static void setDatabaseToStub()
	{
		dataAccessService = new DataAccessStub("test");
		dataAccessService.open("test");
	}
}
