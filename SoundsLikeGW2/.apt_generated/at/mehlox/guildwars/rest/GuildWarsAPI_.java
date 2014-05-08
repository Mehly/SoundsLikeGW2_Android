//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.0.1.
//


package at.mehlox.guildwars.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import at.mehlox.guildwars.rest.entities.BuildInfo;
import at.mehlox.guildwars.rest.entities.GuildWarsEvent;
import at.mehlox.guildwars.rest.entities.MapName;
import at.mehlox.guildwars.rest.entities.WorldName;
import at.mehlox.guildwars.rest.entities.base.ItemBase;
import at.mehlox.guildwars.rest.java.util.List_GuildWarsEvent;
import at.mehlox.guildwars.rest.java.util.List_MapName;
import at.mehlox.guildwars.rest.java.util.List_WorldName;
import at.mehlox.guildwars.rest.wrappers.EventListWrapper;
import at.mehlox.guildwars.rest.wrappers.ItemIdsWrapper;
import org.androidannotations.api.rest.RestErrorHandler;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public final class GuildWarsAPI_
    implements GuildWarsAPI
{

    private RestErrorHandler restErrorHandler;
    private String rootUrl;
    private RestTemplate restTemplate;

    public GuildWarsAPI_() {
        rootUrl = "https://api.guildwars2.com/v1";
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
        restTemplate.setInterceptors(new ArrayList<ClientHttpRequestInterceptor>());
        restTemplate.getInterceptors().add(new RestInterceptor());
    }

    @Override
    public void setRestErrorHandler(RestErrorHandler arg0) {
        this.restErrorHandler = arg0;
    }

    @Override
    public EventListWrapper getEvents() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.parseMediaType("application/json")));
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(httpHeaders);
        try {
            return restTemplate.exchange(rootUrl.concat("/events.json"), HttpMethod.GET, requestEntity, EventListWrapper.class).getBody();
        } catch (RestClientException e) {
            if (restErrorHandler!= null) {
                restErrorHandler.onRestClientExceptionThrown(e);
                return null;
            } else {
                throw e;
            }
        }
    }

    @Override
    public List<WorldName> getWorldNames(String lang) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.parseMediaType("application/json")));
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(httpHeaders);
        HashMap<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("lang", lang);
        try {
            return restTemplate.exchange(rootUrl.concat("/world_names.json?lang={lang}"), HttpMethod.GET, requestEntity, List_WorldName.class, urlVariables).getBody();
        } catch (RestClientException e) {
            if (restErrorHandler!= null) {
                restErrorHandler.onRestClientExceptionThrown(e);
                return null;
            } else {
                throw e;
            }
        }
    }

    @Override
    public List<MapName> getMapNames(String lang) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.parseMediaType("application/json")));
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(httpHeaders);
        HashMap<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("lang", lang);
        try {
            return restTemplate.exchange(rootUrl.concat("/map_names.json?lang={lang}"), HttpMethod.GET, requestEntity, List_MapName.class, urlVariables).getBody();
        } catch (RestClientException e) {
            if (restErrorHandler!= null) {
                restErrorHandler.onRestClientExceptionThrown(e);
                return null;
            } else {
                throw e;
            }
        }
    }

    @Override
    public List<GuildWarsEvent> getEventNames(String lang) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.parseMediaType("application/json")));
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(httpHeaders);
        HashMap<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("lang", lang);
        try {
            return restTemplate.exchange(rootUrl.concat("/event_names.json?lang={lang}"), HttpMethod.GET, requestEntity, List_GuildWarsEvent.class, urlVariables).getBody();
        } catch (RestClientException e) {
            if (restErrorHandler!= null) {
                restErrorHandler.onRestClientExceptionThrown(e);
                return null;
            } else {
                throw e;
            }
        }
    }

    @Override
    public ItemIdsWrapper getItemIds() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.parseMediaType("application/json")));
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(httpHeaders);
        try {
            return restTemplate.exchange(rootUrl.concat("/items.json"), HttpMethod.GET, requestEntity, ItemIdsWrapper.class).getBody();
        } catch (RestClientException e) {
            if (restErrorHandler!= null) {
                restErrorHandler.onRestClientExceptionThrown(e);
                return null;
            } else {
                throw e;
            }
        }
    }

    @Override
    public ItemBase getItemDetails(int itemId, String lang) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.parseMediaType("application/json")));
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(httpHeaders);
        HashMap<String, Object> urlVariables = new HashMap<String, Object>();
        urlVariables.put("itemId", itemId);
        urlVariables.put("lang", lang);
        try {
            return restTemplate.exchange(rootUrl.concat("/item_details.json?item_id={itemId}&lang={lang}"), HttpMethod.GET, requestEntity, ItemBase.class, urlVariables).getBody();
        } catch (RestClientException e) {
            if (restErrorHandler!= null) {
                restErrorHandler.onRestClientExceptionThrown(e);
                return null;
            } else {
                throw e;
            }
        }
    }

    @Override
    public BuildInfo getBuildInfo() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.parseMediaType("application/json")));
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(httpHeaders);
        try {
            return restTemplate.exchange(rootUrl.concat("/build.json"), HttpMethod.GET, requestEntity, BuildInfo.class).getBody();
        } catch (RestClientException e) {
            if (restErrorHandler!= null) {
                restErrorHandler.onRestClientExceptionThrown(e);
                return null;
            } else {
                throw e;
            }
        }
    }

}
