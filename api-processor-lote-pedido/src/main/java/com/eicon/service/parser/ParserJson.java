package com.eicon.service.parser;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

public class ParserJson<T> implements Deserializer<T> {

    private Class<? extends Object> type;
    
    public ParserJson(T obj) {
    	this.type = obj.getClass();
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public T deserialize(String s, byte[] bytes) {
		JsonMapper jsonMapper = new JsonMapper();
		jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		T obj = null;
		try {
			obj = (T) jsonMapper.readValue(s, this.type);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        return obj;
    }
}
