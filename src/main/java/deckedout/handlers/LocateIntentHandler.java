package deckedout.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.attributes.AttributesManager;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.response.ResponseBuilder;

import deckedout.PhrasesAndConstants;
import deckedout.model.ObjectDO;

public class LocateIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("LocateIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        ResponseBuilder responseBuilder = input.getResponseBuilder();
        IntentRequest ir = (IntentRequest) input.getRequest();
        Map<String, Slot> slots = ir.getIntent().getSlots();
        AttributesManager attributesManager = input.getAttributesManager();
        Map<String, java.lang.Object> attributes = attributesManager.getPersistentAttributes();        

        ObjectDO objectToStore = new ObjectDO(slots.get(PhrasesAndConstants.OBJECT_KEY).getValue(), slots.get(PhrasesAndConstants.LOCATION_KEY).getValue());
        
        attributes.put(Integer.toString(objectToStore.hashCode()),objectToStore.toStringDO());
        attributesManager.setPersistentAttributes(attributes);        
        attributesManager.savePersistentAttributes();
        
        String speechText = "Der LocateIntent wurde erfolgreich aufgerufen";
        responseBuilder.withSpeech(speechText).withShouldEndSession(false);
        return responseBuilder.build();
    }

}
