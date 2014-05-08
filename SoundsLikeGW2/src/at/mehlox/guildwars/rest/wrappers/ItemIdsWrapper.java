package at.mehlox.guildwars.rest.wrappers;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemIdsWrapper {

	@JsonProperty("items")
	private int[] itemIds;

	public int[] getAsArray() {
		return itemIds;
	}

}
