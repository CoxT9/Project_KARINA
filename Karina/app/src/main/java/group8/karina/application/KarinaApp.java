package group8.karina.application;

import android.app.Application;

//here we can do stuff we need to do when starting the app
public class KarinaApp extends Application
{
    public static final String dbName = "???"; //well need to fill this in when we make the 'real' database
    @Override
    public void onCreate()
    {
        super.onCreate();

        Services.createDataAccess();
    }
}
