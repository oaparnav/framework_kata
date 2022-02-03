package framework_kata;

import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

public class RequestData<T> {
    private UriComponentsBuilder uriBuilder;
    private T body;
    private HttpHeaders headers;

    public RequestData(UriComponentsBuilder uriBuilder, T body, HttpHeaders headers) {
        this.uriBuilder = uriBuilder;
        this.body = body;
        this.headers = headers;
    }

    public UriComponentsBuilder getUriBuilder() {
        return uriBuilder;
    }

    public T getBody() {
        return body;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }
}
