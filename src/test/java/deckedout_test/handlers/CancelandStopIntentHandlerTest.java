package deckedout_test.handlers;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

import deckedout.handlers.CancelandStopIntentHandler;

public class CancelandStopIntentHandlerTest {

    private CancelandStopIntentHandler handler;

    @Before
    public void setup() {
        handler = new CancelandStopIntentHandler();
    }
//return input.matches(intentName("AMAZON.StopIntent").or(intentName("AMAZON.CancelIntent")));
    @Test
    public void testCanHandle() {
        final HandlerInput inputMock = Mockito.mock(HandlerInput.class);
        when(inputMock.matches(any())).thenReturn(true);
        //when(inputMock.matches(intentName("AMAZON.CancelIntent"))).thenReturn(true);
        //when(inputMock.matches(intentName("AMAZON.StopIntent"))).thenReturn(true);
        assertTrue(handler.canHandle(inputMock));
    }
/*
    @Test
    public void testHandle() {
        final HandlerInput inputMock = TestUtil.mockHandlerInput(null, null, null, null);
        final Optional<Response> res = handler.handle(inputMock);

        assertTrue(res.isPresent());
        final Response response = res.get();

        assertTrue(response.getShouldEndSession());
        assertNotNull(response.getOutputSpeech());
        assertTrue(response.getOutputSpeech().toString().contains(PhrasesAndConstants.CANCEL_AND_STOP));
    }
    */
}

