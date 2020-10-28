package framework_kata;

import org.junit.Test;

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
    @Test public void testSomeLibraryMethod() {
        RestCommand command = new RestCommand();
        command.run();
    }
}
