package framework_kata;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public class AuthCommand<R, S> implements CommandChain<R, S> {
    private CommandChain<R, S> next;

    public AuthCommand(CommandChain<R, S> next) {
        this.next = next;
    }

    @Override
    public ResponseEntity<S> run(RequestData<R> request) {
        HttpHeaders headers = request.getHeaders();
        headers.add("auth", "Bearer abc");
        return next.run(request);
    }
}
