package at.mehlox.guildwars;

import java.sql.SQLException;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import at.mehlox.guildwars.data.ormlite.DatabaseHelper;
import at.mehlox.guildwars.rest.RestClient;
import at.mehlox.guildwars.rest.entities.base.ItemBase;

@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity {

	@Bean
	RestClient mRestClient;

	@Bean
	DatabaseHelper mDatabase;

	@AfterViews
	void afterViews() {
		loadEvents();
	}

	@Background
	protected void loadEvents() {

		int[] itemIds = mRestClient.getItemIds();
		try {
			ItemBase itemBase = mDatabase.getItem(itemIds[0]);
			if (itemBase == null) {

				itemBase = mRestClient.getItemDetails(itemIds[0]);

				mDatabase.createOrUpdateItem(itemBase);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Log.i(getClass().getSimpleName(), "Loaded items: " + itemIds.length);

	}

}
