package at.mehlox.guildwars.data;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import at.mehlox.guildwars.rest.RestClient;

@EBean
public class DataManager {

	@Bean
	RestClient mRestClient;
	
	

}
