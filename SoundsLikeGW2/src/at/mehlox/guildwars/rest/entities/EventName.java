package at.mehlox.guildwars.rest.entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventName {

	// *******************************************
	// * FIELDS
	// *******************************************

	@JsonProperty("id")
	private String mId;

	@JsonProperty("name")
	private String mName;

	// *******************************************
	// * GETTERS
	// *******************************************

	public String getId() {
		return mId;
	}

	public String getName() {
		return mName;
	}

}
