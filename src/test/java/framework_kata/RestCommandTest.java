package framework_kata;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;

/* Part 1
 * 
 * Implement a generic rest command which can make
 * any request to any REST endpoint (no mutual auth) and 
 * return whatever response of that endpoint. It needs to 
 * support the common functionality like setting headers 
 * for trace id, application id etc. Does not need to handle 
 * authentication
 */

/* Part 2
* 
* Add support for authentication. Needs to support FIG
* authentication as well as Azure. Should set the proper
* auth headers before making the call.
* 
* Design should be Open/Closed to be able to support other
* authentication mechanisms in the future
*/

/* Part 3
 * 
 * Add support for logging. Logging should be extensible,
 * so that we can log only requests, or only responses, or both.
 * We should be abe to log only specific fields 
 * or specific headers from the request or response. We should
 * be able to always log, or log only on error. If we need to
 * log certain items in info mode and other in debug mode 
 * (eg: end point url as info, and auth header field as debug),
 * then it should be possible to do that
 */

/* Part 4
 * 
 * Add support for making calls to  mutual auth REST endpoints
 */

public class RestCommandTest {
    private RestTemplate restTemplate;
    private AppController controller;

    @Before
    public void setup() {
        ResponseEntity<String> response = new ResponseEntity<String>("Hello, world", HttpStatus.OK);
        restTemplate = Mockito.mock(RestTemplate.class);
        Mockito.when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class),  eq(String.class)))
            .thenReturn(response);
        controller = new AppController(restTemplate);
    }

    @Test
    public void testResponse() throws Exception {
        String response = controller.endpoint1();
        assertEquals("Hello, world", response);
    }

    @Test
    public void shouldIncludeTraceId() throws Exception {
        controller.endpoint1();
        ArgumentCaptor<HttpEntity<String>> entityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        Mockito.verify(restTemplate).exchange(eq("https://www.google.co.in/"), eq(HttpMethod.GET), entityCaptor.capture(), eq(String.class));
        HttpEntity<String> entity = entityCaptor.getValue();
        HttpHeaders headers = entity.getHeaders();
        assertEquals("123", headers.get("trace-id").get(0));
    }

    @Test
    public void shouldIncludeAuthHeader() throws Exception {
        controller.endpoint1();
        ArgumentCaptor<HttpEntity<String>> entityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        Mockito.verify(restTemplate).exchange(eq("https://www.google.co.in/"), eq(HttpMethod.GET), entityCaptor.capture(), eq(String.class));
        HttpEntity<String> entity = entityCaptor.getValue();
        HttpHeaders headers = entity.getHeaders();
        assertEquals("Bearer abc", headers.get("auth").get(0));
    }
}
