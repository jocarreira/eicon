package com.eicon.service.parser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.web.multipart.MultipartFile;

import com.eicon.kafka.GsonDeserializer;
import com.eicon.kafka.XmlDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ParserDinamico<T>  {
	
    private final Gson gson = new GsonBuilder().create();
    
    GsonDeserializer<T> parserGson = new GsonDeserializer<T>();
    XmlDeserializer<T> parserXml = new XmlDeserializer<T>();

	@SuppressWarnings("unchecked")
	public T parse(MultipartFile arquivoLote, T objeto) throws IOException, InstantiationException, IllegalAccessException {
		String sObj = this.toString(arquivoLote);
		T obj = null;
		byte[] bytes = arquivoLote.getBytes();
		if (sObj.contains("<")) {	
			obj = new ParserXml<>(objeto).deserialize(sObj, bytes);
		} else {
			obj = new ParserJson<T>(objeto).deserialize(sObj, bytes);
		}
		return obj;
	}
            
	private String toString(MultipartFile arquivoLote) {
		String retorno = null;
		try {
        	byte[] bytes = arquivoLote.getBytes();
        	retorno = new String(bytes, StandardCharsets.UTF_8);
			System.out.println(retorno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

}
