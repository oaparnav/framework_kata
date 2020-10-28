package framework_kata;

import org.springframework.http.ResponseEntity;

public class LoggerCommand<R, S> implements CommandChain<R, S> {
    private CommandChain<R, S> next;

    public LoggerCommand(CommandChain<R, S> next) {
        this.next = next;
    }

    @Override
    public ResponseEntity<S> run(RequestData<R> request) {
        System.out.println("before request");
        ResponseEntity<S> response = next.run(request);
        System.out.println("got response");
        return response;
    }
}