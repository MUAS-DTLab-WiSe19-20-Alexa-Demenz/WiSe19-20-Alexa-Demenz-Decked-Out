package deckedout_test.handlers;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

import deckedout.handlers.SessionEndedRequestHandler;

public class SessionEndedRequestHandlerTest {
    private SessionEndedRequestHandler handler;

    @Before
    public void setup() {
        handler = new SessionEndedRequestHandler();
    }

    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }
/*
    @Test
    public void testHandle() {
        final Response response = TestUtil.sessionEndedTestForHandle(handler);
        assertTrue(response.getShouldEndSession());
    }
  */
}