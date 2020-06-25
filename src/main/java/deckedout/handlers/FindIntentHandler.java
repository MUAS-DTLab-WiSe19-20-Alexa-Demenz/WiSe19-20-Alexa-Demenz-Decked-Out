package deckedout.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.ArrayList;
import java.util.List;
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

public class FindIntentHandler implements RequestHandler {	

	@Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("FindIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	ResponseBuilder responseBuilder = input.getResponseBuilder();
        IntentRequest ir = (IntentRequest) input.getRequest();
        Map<String, Slot> slots = ir.getIntent().getSlots();
        AttributesManager attributesManager = input.getAttributesManager();
        Map<String, Object> attributes = attributesManager.getPersistentAttributes();        
        String resp = "";
        List<ObjectDO> listOb = new ArrayList<>();
        
        for(Entry<String, Object> e : attributes.entrySet()) {
        	if(e.getValue().toString().contains(slots.get(PhrasesAndConstants.OBJECT_KEY).getValue())) {        		
        		listOb.add(ObjectDO.fromString(e.getValue().toString()));        		
        	}
        }
        
        if(listOb.isEmpty()) {
        	resp = "Ich konnte nichts in der Richtung finden";
        } else if(listOb.size() == 1) {
        	resp = listOb.get(0).getName() + " liegt dort: " + listOb.get(0).getLocation();
        } else {
        	resp = "Ich habe leider zu viel gefunden und kann dir nciht genau sagen wo es liegt";
        }
        
        responseBuilder.withSpeech(resp).withShouldEndSession(false);
        return responseBuilder.build();
    }

}
