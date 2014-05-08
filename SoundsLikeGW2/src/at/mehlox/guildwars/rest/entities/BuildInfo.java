package at.mehlox.guildwars.rest.entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BuildInfo {

	@JsonProperty("build_id")
	private int mBuildId;

	public int getBuildId() {
		return mBuildId;
	}

}
