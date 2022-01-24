package com.eicon.service.interfaces;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public interface LoteProcessor {

	Serializable processar(MultipartFile arquivoLote) throws Exception;
	
}
