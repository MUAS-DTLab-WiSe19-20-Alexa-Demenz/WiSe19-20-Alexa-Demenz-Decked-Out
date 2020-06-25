package deckedout.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Map.Entry;
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

public class RelocateIntentHandler implements RequestHandler {
	
	@Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("RelocateIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	ResponseBuilder responseBuilder = input.getResponseBuilder();
        IntentRequest ir = (IntentRequest) input.getRequest();
        Map<String, Slot> slots = ir.getIntent().getSlots();
        AttributesManager attributesManager = input.getAttributesManager();
        Map<String, Object> attributes = attributesManager.getPersistentAttributes();        	
        
        ObjectDO o = new ObjectDO(slots.get(PhrasesAndConstants.OBJECT_KEY).getValue(), slots.get(PhrasesAndConstants.CURRENT_LOCATION_KEY).getValue());
        ObjectDO storedObject = null;
        Entry<String, Object> entryToDelete = null;
        for(Entry<String, Object> e : attributes.entrySet()) {
        	if(e.getValue().toString().equals(o.getName() + ":" + o.getLocation())) {        		
        		storedObject = ObjectDO.fromString(e.getValue().toString());
        		entryToDelete = e;
        	}
        }        
        
        if(storedObject != null) {
        	storedObject.setLocation(slots.get(PhrasesAndConstants.NEW_LOCATION_KEY).getValue());
        	attributes.put(Integer.toString(storedObject.hashCode()), storedObject.toStringDO());
        	attributes.remove(entryToDelete.getKey());
        } else {
            attributes.put(Integer.toString(o.hashCode()),o.toStringDO());
            attributesManager.setPersistentAttributes(attributes);        
            attributesManager.savePersistentAttributes();
        }
        
        attributesManager.setPersistentAttributes(attributes);
        attributesManager.savePersistentAttributes();

        String speechText = "Der RelocateIntent wurde erfolgreich aufgerufen";                
        responseBuilder.withSpeech(speechText).withShouldEndSession(false);
        return responseBuilder.build();
    }

}
