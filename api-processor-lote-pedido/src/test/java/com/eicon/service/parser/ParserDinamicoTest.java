package com.eicon.service.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.eicon.dto.LotePedidosDto;

class ParserDinamicoTest {

	private ParserDinamico<LotePedidosDto> parser = new ParserDinamico<LotePedidosDto>();
	
	private MultipartFile getMockMultipartFile(String name, String conteudo) throws IOException {
        InputStream is = org.apache.commons.io.IOUtils.toInputStream(conteudo, "UTF-8");
		MultipartFile multipartFile = new MockMultipartFile(name, name, "", is);
        return multipartFile;
	}
	
	@Test
	public void deveDeserializarLoteXml() throws IOException, InstantiationException, IllegalAccessException {
		MultipartFile arquivoLote = getMockMultipartFile("file", loteXml);
		LotePedidosDto dto = new LotePedidosDto();
		dto = parser.parse(arquivoLote, dto);
		assertEquals(1L, dto.getIdLote());  // testa id do lote
		assertEquals(3, dto.getPedidos().size());  // testa quantidade de pedidos
	}

	@Test
	public void deveDeserializarLoteJson() throws IOException, InstantiationException, IllegalAccessException {
		MultipartFile arquivoLote = getMockMultipartFile("file", loteJson);
		LotePedidosDto dto = new LotePedidosDto();
		dto = parser.parse(arquivoLote, dto);
		assertEquals(2L, dto.getIdLote());
		assertEquals(2, dto.getPedidos().size());
	}
	 
	private String loteXml = "<lote>\r\n"
			+ "	<idLote>1</idLote>\r\n"
			+ "	<pedidos>\r\n"
			+ "		<pedido>\r\n"
			+ "			<nPedido>980f7ec2-9ac6-472a-85be-96c871e6dedf</nPedido>\r\n"
			+ "			<data>2022-02-20</data>\r\n"
			+ "			<nomeProduto>produto teste 1</nomeProduto>\r\n"
			+ "			<valor>10.0</valor>\r\n"
			+ "			<qtd>1.0</qtd>\r\n"
			+ "			<idCliente>1</idCliente>\r\n"
			+ "			<vldTotal>10.0</vldTotal>\r\n"
			+ "		</pedido>\r\n"
			+ "		<pedido>\r\n"
			+ "			<nPedido>71d09366-da8a-47ea-b324-ad1576b3867a</nPedido>\r\n"
			+ "			<data>2022-02-20</data>\r\n"
			+ "			<nomeProduto>produto teste 2</nomeProduto>\r\n"
			+ "			<valor>10.1</valor>\r\n"
			+ "			<qtd>1.1</qtd>\r\n"
			+ "			<idCliente>1</idCliente>\r\n"
			+ "			<vldTotal>11.11</vldTotal>\r\n"
			+ "		</pedido>\r\n"
			+ "		<pedido>\r\n"
			+ "			<nPedido>045c49cd-e09d-49e9-95aa-1fc9e801f455</nPedido>\r\n"
			+ "			<data>2022-02-20</data>\r\n"
			+ "			<nomeProduto>produto teste 3</nomeProduto>\r\n"
			+ "			<valor>10.2</valor>\r\n"
			+ "			<qtd>1.2</qtd>\r\n"
			+ "			<idCliente>2</idCliente>\r\n"
			+ "			<vldTotal>14.64</vldTotal>\r\n"
			+ "		</pedido>\r\n"
			+ "	</pedidos>\r\n"
			+ "</lote>";
	
	private String loteJson = "{\r\n"
			+ "	\"idLote\" : 2,\r\n"
			+ "	\"pedidos\": [\r\n"
			+ "		{\r\n"
			+ "			\"nPedido\" : \"411564c0-367b-424b-8312-c7b6ebb910b0\",\r\n"
			+ "			\"data\" : \"2022-02-20\",\r\n"
			+ "			\"nomeProduto\" : \"produto json teste 1\",\r\n"
			+ "			\"valor\" : 10.1,\r\n"
			+ "			\"qtd\" : 1.1,\r\n"
			+ "			\"idCliente\" : 2,\r\n"
			+ "			\"vldTotal\" : 11.11\r\n"
			+ "		},\r\n"
			+ "		{\r\n"
			+ "			\"nPedido\" : \"c8cede58-9908-4bf7-bace-9e1e3f389ea6\",\r\n"
			+ "			\"data\" : \"2022-02-20\",\r\n"
			+ "			\"nomeProduto\" : \"produto json teste 2\",\r\n"
			+ "			\"valor\" : 10.2,\r\n"
			+ "			\"qtd\" : 1.2,\r\n"
			+ "			\"idCliente\" : 2,\r\n"
			+ "			\"vldTotal\" : 14.64\r\n"
			+ "		}\r\n"
			+ "	]\r\n"
			+ "}";
}
