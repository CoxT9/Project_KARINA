package group8.karina.application;

import android.app.Application;


public class KarinaApp extends Application
{
	public static final String dbName = "database/db1";

	@Override
	public void onCreate()
	{
		super.onCreate();

		Services.createDataAccess();
	}
}
