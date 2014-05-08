package at.mehlox.guildwars.data.ormlite;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import at.mehlox.guildwars.rest.entities.base.ItemBase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseInitializationHelper extends OrmLiteSqliteOpenHelper {

	private final String TAG = getClass().getSimpleName();

	private static final String DATABASE_NAME = "guildwars.db";
	private static final int DATABASE_VERSION = 1;

	public DatabaseInitializationHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {

		try {
			TableUtils.createTable(connectionSource, ItemBase.class);
		} catch (SQLException e) {

			Log.e(TAG, "Could not create database.", e);
			throw new RuntimeException(e);
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {

	}

}
