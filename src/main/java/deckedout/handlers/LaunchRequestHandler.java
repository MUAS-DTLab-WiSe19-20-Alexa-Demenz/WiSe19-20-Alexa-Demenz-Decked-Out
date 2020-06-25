package deckedout.handlers;

import static com.amazon.ask.request.Predicates.requestType;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;

import deckedout.model.ObjectDO;
import deckedout.PhrasesAndConstants;


public class LaunchRequestHandler implements RequestHandler {


    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        ResponseBuilder responseBuilder = input.getResponseBuilder();

        //see if an object is deposited already
        AttributesManager attributesManager = input.getAttributesManager();
        Map<String, Object> persistentAttributes = attributesManager.getPersistentAttributes();
        Optional<Entry<String, Object>> a = persistentAttributes.entrySet().stream().findAny();
        Entry<String, Object> depositedObjectString = null;
        if(a.isPresent()) {
        	depositedObjectString = a.get();
        }
        
        if(depositedObjectString != null){
        	ObjectDO depositedObject = ObjectDO.fromString(depositedObjectString.getValue().toString());
            input.getAttributesManager().setSessionAttributes(Collections.singletonMap(PhrasesAndConstants.OBJECT_KEY,
                    depositedObject));
            String speechText =
                    String.format(PhrasesAndConstants.OBJECT_DEPOSITED, depositedObject.getName(), depositedObject.getLocation(),
                            PhrasesAndConstants.CHANGE_LOCATION);
            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, speechText)
                    .withSpeech(speechText)
                    .withReprompt(PhrasesAndConstants.HELP_REPROMPT)
                    .withShouldEndSession(false);
        } else {
            responseBuilder.withSimpleCard(PhrasesAndConstants.CARD_TITLE, PhrasesAndConstants.WELCOME)
                    .withSpeech(PhrasesAndConstants.WELCOME)
                    .withReprompt(PhrasesAndConstants.HELP_REPROMPT)
                    .withShouldEndSession(false);
        }

        return responseBuilder.build();
    }
}
