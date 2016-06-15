package group8.karina.application;

import group8.karina.persistence.DataAccessObject;
import group8.karina.persistence.Database;

public class Services
{
//	private static DataAccessStub dataAccessService = null;
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
}

