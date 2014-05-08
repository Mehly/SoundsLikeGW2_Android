package at.mehlox.guildwars.rest.wrappers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;

public final class BufferingClientHttpResponseWrapper implements
		ClientHttpResponse {

	private final ClientHttpResponse response;

	private byte[] body;

	public BufferingClientHttpResponseWrapper(ClientHttpResponse response) {
		this.response = response;
	}

	public HttpStatus getStatusCode() throws IOException {
		return this.response.getStatusCode();
	}

	public int getRawStatusCode() throws IOException {
		return this.response.getRawStatusCode();
	}

	public String getStatusText() throws IOException {
		return this.response.getStatusText();
	}

	public HttpHeaders getHeaders() {
		HttpHeaders headers = this.response.getHeaders();
		return headers;
	}

	public InputStream getBody() throws IOException {
		if (this.body == null) {
			InputStream bodyStream = this.response.getBody();
			if (bodyStream != null) {
				this.body = FileCopyUtils.copyToByteArray(bodyStream);
			} else {
				this.body = new byte[0];
			}
		}
		return new ByteArrayInputStream(this.body);
	}

	public void close() {
		this.response.close();
	}

}
