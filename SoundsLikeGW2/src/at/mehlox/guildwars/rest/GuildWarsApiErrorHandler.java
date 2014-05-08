package at.mehlox.guildwars.rest;

import org.androidannotations.api.rest.RestErrorHandler;
import org.springframework.web.client.RestClientException;

import android.util.Log;

public class GuildWarsApiErrorHandler implements RestErrorHandler {

	private final String TAG = getClass().getSimpleName();

	@Override
	public void onRestClientExceptionThrown(RestClientException ex) {

		Log.e(TAG, ex.getMessage());

	}

}
