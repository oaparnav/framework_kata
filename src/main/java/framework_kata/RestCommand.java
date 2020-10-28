package framework_kata;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestCommand<R, S> implements CommandChain<R, S> {
	private RestTemplate restTemplate;
	private Class<S> responseType;

	public RestCommand(RestTemplate restTemplate, Class<S> responseType) {
		this.restTemplate = restTemplate;
		this.responseType = responseType;
	}

	public ResponseEntity<S> run(RequestData<R> request) {
		String uri = request.getUriBuilder().build().toUriString();
		HttpEntity<R> httpEntity = new HttpEntity<R>(request.getBody(), request.getHeaders());
		return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, responseType);
	}
}
