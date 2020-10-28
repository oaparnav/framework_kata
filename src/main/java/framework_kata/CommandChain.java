package framework_kata;

import org.springframework.http.ResponseEntity;

public interface CommandChain<R, S> {
	public ResponseEntity<S> run(RequestData<R> request);
}
