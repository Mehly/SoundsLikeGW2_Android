package at.mehlox.guildwars.rest.wrappers;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import at.mehlox.guildwars.rest.entities.GuildWarsEvent;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventListWrapper {

	@JsonProperty("events")
	private List<GuildWarsEvent> mEvents;

	public List<GuildWarsEvent> getList() {
		return mEvents;
	}

}
