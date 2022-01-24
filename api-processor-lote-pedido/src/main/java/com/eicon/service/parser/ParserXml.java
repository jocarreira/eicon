package com.eicon.service.parser;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class ParserXml<T> implements Deserializer<T> {

    private Class<? extends Object> type;
    
    public ParserXml(T obj) {
    	this.type = obj.getClass();
    }
    
    @Override
    public T deserialize(String s, byte[] bytes) {
		XmlMapper xmlMapper = new XmlMapper();
		xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		T obj = null;
		try {
			obj = (T) xmlMapper.readValue(s, this.type);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
        return obj;
    }
}
