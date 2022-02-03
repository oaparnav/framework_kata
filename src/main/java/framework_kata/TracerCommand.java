package framework_kata;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public class TracerCommand<R, S> implements CommandChain<R, S> {
    private CommandChain<R, S> next;

    public TracerCommand(CommandChain<R, S> next) {
        this.next = next;
    }

    @Override
    public ResponseEntity<S> run(RequestData<R> request) {
        HttpHeaders headers = request.getHeaders();
        headers.add("trace-id", "123");
        return next.run(request);
    }
}
