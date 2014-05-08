package at.mehlox.guildwars.rest.entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GuildWarsEvent {

	@JsonProperty("event_id")
	private String mId;
	
	@JsonProperty("name")
	private String mName;

	@JsonProperty("state")
	private String mState;

	@JsonProperty("world_id")
	private int mWorldId;

	@JsonProperty("map_id")
	private int mMapId;

}
