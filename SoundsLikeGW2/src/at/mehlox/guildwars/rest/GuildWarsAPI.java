package at.mehlox.guildwars.rest;

import java.util.List;

import org.androidannotations.annotations.rest.Accept;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.MediaType;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

import at.mehlox.guildwars.rest.entities.BuildInfo;
import at.mehlox.guildwars.rest.entities.GuildWarsEvent;
import at.mehlox.guildwars.rest.entities.MapName;
import at.mehlox.guildwars.rest.entities.WorldName;
import at.mehlox.guildwars.rest.entities.base.ItemBase;
import at.mehlox.guildwars.rest.wrappers.EventListWrapper;
import at.mehlox.guildwars.rest.wrappers.ItemIdsWrapper;

/**
 * Represents the offical Guild Wars 2 API interface and specifies it's
 * connectors.
 */
@Accept(MediaType.APPLICATION_JSON)
@Rest(rootUrl = "https://api.guildwars2.com/v1", converters = { MappingJacksonHttpMessageConverter.class }, interceptors = { RestInterceptor.class })
public interface GuildWarsAPI extends RestClientErrorHandling {

	// *******************************************
	// * DYNAMIC EVENTS
	// *******************************************

	@Get("/events.json")
	public EventListWrapper getEvents();

	@Get("/event_names.json?lang={lang}")
	public List<GuildWarsEvent> getEventNames(String lang);

	@Get("/map_names.json?lang={lang}")
	public List<MapName> getMapNames(String lang);

	@Get("/world_names.json?lang={lang}")
	public List<WorldName> getWorldNames(String lang);

	// *******************************************
	// * ITEMS
	// *******************************************

	@Get("/items.json")
	public ItemIdsWrapper getItemIds();

	@Get("/item_details.json?item_id={itemId}&lang={lang}")
	public ItemBase getItemDetails(int itemId, String lang);

	// *******************************************
	// * MISC
	// *******************************************

	@Get("/build.json")
	public BuildInfo getBuildInfo();

}
