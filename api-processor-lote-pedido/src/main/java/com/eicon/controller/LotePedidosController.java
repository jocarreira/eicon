package com.eicon.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eicon.dto.ResponseSuccessDto;
import com.eicon.service.LotePedidosService;

//import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api/v1")
public class LotePedidosController  {

	@Autowired
	private LotePedidosService service;
	
	@PostMapping(path = "/lote/{idLote}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseSuccessDto> recuperarLote(
			/* @ApiParam(example = "1", required = true) */
			@PathVariable(name =  "idLote")
			Long idLote,
			@RequestParam("arqLote")
			MultipartFile arqLote) {
        
        System.out.println(arqLote.getOriginalFilename());
        
        Serializable retorno = null;
        try {
        	retorno = service.processar(arqLote);
        } catch (Exception e) {
        	return ResponseEntity.badRequest().body(new ResponseSuccessDto("Não foi possivel desserializar lote id : " + idLote));
		}

        System.out.println(retorno);
        System.out.println(arqLote.getOriginalFilename());
        ResponseSuccessDto ret = new ResponseSuccessDto("Lote id : " + idLote + " precessado com sucesso.");
        return ResponseEntity.ok(ret);
        
	}
}
