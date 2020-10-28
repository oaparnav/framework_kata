package framework_kata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class AppController {
    RestTemplate restTemplate;

    @Autowired
    public AppController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/")
    public String endpoint1() {
        CommandChain<String, String> command = 
            new LoggerCommand<>(
                new AuthCommand<>(
                    new TracerCommand<>(
                        new RestCommand<>(restTemplate, String.class))));
        RequestData<String> request = new RequestData<String>(UriComponentsBuilder.fromHttpUrl("https://www.google.co.in/"), "some data", new HttpHeaders());
        ResponseEntity<String> response = command.run(request);
        return response.getBody();
    }
}
