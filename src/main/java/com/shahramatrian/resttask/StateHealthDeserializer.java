package com.shahramatrian.resttask;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * Custom JSON string deserializer
 */
public class StateHealthDeserializer extends StdDeserializer<StateHealth> {

    /**
     *
     */
    private static final long serialVersionUID = 74908070894243200L;

    public StateHealthDeserializer() {
        this(null);
    }

    public StateHealthDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public StateHealth deserialize(JsonParser parser, DeserializationContext deserializer) {
        StateHealth stateHealth = new StateHealth();
        ObjectCodec codec = parser.getCodec();
        JsonNode node;
        try {
            node = codec.readTree(parser);
            JsonNode regionNode = node.get("region");
            stateHealth.setRegion(regionNode.asText());

            JsonNode regionCodeNode = node.get("region_code");
            stateHealth.setRegionCode(regionCodeNode.asText());

            JsonNode periodNode = node.get("period");
            stateHealth.setPeriod(periodNode.asText());

            JsonNode pctBasicEhrNode = node.get("pct_hospitals_basic_ehr_notes");
            stateHealth.setPctHospitalsBasicEhrNotes(pctBasicEhrNode.asText());
    
        } catch (IOException e) {
            // TODO log errors and generate proper error message
            e.printStackTrace();
        }
        return stateHealth;
    }
}
