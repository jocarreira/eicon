package com.eicon.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eicon.dispatcher.KafkaDispatcher;
import com.eicon.dto.LotePedidosDto;
import com.eicon.dto.PedidoDto;
import com.eicon.service.interfaces.LoteProcessor;
import com.eicon.service.normalizer.PedidosLoteNormalizer;
import com.eicon.service.parser.ParserDinamico;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


@Service
public class LotePedidosService implements LoteProcessor {

	private ObjectMapper objectMapper;
	
	@Override
	public Serializable processar(MultipartFile arquivoLote) throws Exception {

        if (arquivoLote.isEmpty()) {
        	throw new RuntimeException("Arquivo vazio!!");
        }
    
        String s = "";
        try {
        	LotePedidosDto lote = new LotePedidosDto();
        	lote = (LotePedidosDto) new ParserDinamico<LotePedidosDto>().parse(arquivoLote, lote);
        	if (!isValido(lote)) {
        		throw new RuntimeException("Lote inválida.");
        	}
        	lote = new PedidosLoteNormalizer().normalizar(lote);  // aplica normalização dos dados conforme regras de negócio
            dispatcher(lote);
        	return lote;
        } catch (Exception e) {
            throw e;
        }
        
	}

	private boolean isValido(LotePedidosDto lote) {
		int numMaxPedidos = 10;  //TODO: Colocar número máximo de pedidos no properties
		if (lote.getPedidos().size() > numMaxPedidos) {
			return false;
		}
		return true;
	}

	private void dispatcher(LotePedidosDto lote) throws ExecutionException, InterruptedException {
		try (KafkaDispatcher orderDispatcher = new KafkaDispatcher<LotePedidosDto>()) {
		    try (KafkaDispatcher emailDispatcher = new KafkaDispatcher<String>()) {
	        	
		    	//Gera chave única para o lote despachado
	        	String loteUUID = UUID.randomUUID().toString();
	        	
	            orderDispatcher.send("EICON_NOVOS_PEDIDOS", loteUUID, lote);

	            //Mensagem de email (apenas para simular um envio).
	            String email = "Obrigado. O lote de id : " + lote.getIdLote() + " foi recebido com sucesso e já estamos processando.";
	            emailDispatcher.send("EICON_SEND_EMAIL", loteUUID, email);
		    }
		}
	}


}
