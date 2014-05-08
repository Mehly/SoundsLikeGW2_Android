package at.mehlox.guildwars.rest;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.client.HttpClientErrorException;

import android.util.Log;
import at.mehlox.guildwars.rest.wrappers.BufferingClientHttpResponseWrapper;

public class RestInterceptor implements ClientHttpRequestInterceptor {

	private final String TAG = getClass().getSimpleName();

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body,
			ClientHttpRequestExecution execution) throws IOException {

		return execution.execute(request, body);
		
//		HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
//
//		ClientHttpResponse response = new BufferingClientHttpResponseWrapper(
//				execution.execute(requestWrapper, body));
//
//		HttpStatus httpStatus = null;
//		try {
//			httpStatus = response.getStatusCode();
//			Log.d(TAG, response.getStatusCode().toString());
//		} catch (Exception ex) {
//			Log.w(TAG, "Status code of response could not be evaluated!", ex);
//		}
//
//		if (httpStatus != null
//				&& response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
//			throw new HttpClientErrorException(response.getStatusCode());
//		}
//
//		Log.d(TAG, "Fetched data from URL: " + requestWrapper.getURI().toString());
//		Log.d(TAG, "Received response with status code: " + response.getStatusCode());
//		Log.d(TAG, response.getHeaders().toString());
//
//		StringBuffer bodyString = new StringBuffer();
//
//		if (response.getStatusCode() != HttpStatus.NO_CONTENT) {
//			java.util.Scanner s = new java.util.Scanner(response.getBody(), "UTF-8");
//
//			while (s.hasNext()) {
//				bodyString.append(s.next());
//			}
//
//			Log.d(TAG, bodyString.toString());
//		}
//
//		return response;
	}
}
