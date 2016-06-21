package group8.karina.application;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class KarinaApp extends Application
{
	public static final String dbName = "database0";
	public static String dbPathName = "database/database0";

	@Override
	public void onCreate()
	{
		super.onCreate();
		copyDatabaseToDevice();

		DatabaseService.createDataAccess();
	}



	public static void setDBPathName(String pathName) {
		System.out.println("Setting DB path to: " + pathName);
		dbPathName = pathName;
	}

	private void copyDatabaseToDevice() {
		final String DB_PATH = "db";

		String[] assetNames;
		Context context = getApplicationContext();
		File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
		AssetManager assetManager = getAssets();

		try {

			assetNames = assetManager.list(DB_PATH);
			for (int i = 0; i < assetNames.length; i++) {
				assetNames[i] = DB_PATH + "/" + assetNames[i];
			}

			copyAssetsToDirectory(assetNames, dataDirectory);

			setDBPathName(dataDirectory.toString() + "/" + dbName);

		} catch (IOException ioe) {
			System.out.print("Unable to access application data: " + ioe.getMessage());
		}
	}

	public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
		AssetManager assetManager = getAssets();

		for (String asset : assets) {
			String[] components = asset.split("/");
			String copyPath = directory.toString() + "/" + components[components.length - 1];
			char[] buffer = new char[1024];
			int count;

			File outFile = new File(copyPath);

			if (!outFile.exists()) {
				InputStreamReader in = new InputStreamReader(assetManager.open(asset));
				FileWriter out = new FileWriter(outFile);

				count = in.read(buffer);
				while (count != -1) {
					out.write(buffer, 0, count);
					count = in.read(buffer);
				}

				out.close();
				in.close();
			}
		}
	}
}
