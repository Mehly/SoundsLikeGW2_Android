package at.mehlox.guildwars.rest;

import java.util.List;
import java.util.Locale;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;

import at.mehlox.guildwars.rest.entities.GuildWarsEvent;
import at.mehlox.guildwars.rest.entities.base.ItemBase;

/**
 * This REST client is used to load data from the {@link GuildWarsAPI}.
 */
@EBean
public class RestClient extends GuildWarsApiErrorHandler {

	@RestService
	GuildWarsAPI mRestInterface;

	@AfterInject
	void afterInject() {
		mRestInterface.setRestErrorHandler(this);
	}

	// *******************************************
	// * DYNAMIC EVENTS
	// *******************************************

	public List<GuildWarsEvent> getEvents() {
		return mRestInterface.getEvents().getList();
	}

	public List<GuildWarsEvent> getEventNames() {
		return mRestInterface.getEventNames(Locale.getDefault().getLanguage());
	}

	// *******************************************
	// * ITEMS
	// *******************************************

	public int[] getItemIds() {
		return mRestInterface.getItemIds().getAsArray();
	}

	public ItemBase getItemDetails(int itemId) {
		return mRestInterface.getItemDetails(itemId, getLocaleLanguage());
	}

	// *******************************************
	// * HELPER
	// *******************************************

	public String getLocaleLanguage() {
		return Locale.getDefault().getLanguage();
	}

}
