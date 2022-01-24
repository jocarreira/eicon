package com.eicon.service.normalizer;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.eicon.dto.LotePedidosDto;
import com.eicon.service.parser.ParserDinamico;

class PedidosLoteNormalizerTest {

	private ParserDinamico<LotePedidosDto> parser = new ParserDinamico<LotePedidosDto>();
	
	private PedidosLoteNormalizer normalizador = new PedidosLoteNormalizer();
	
	private MultipartFile getMockMultipartFile(String name, String conteudo) throws IOException {
        InputStream is = org.apache.commons.io.IOUtils.toInputStream(conteudo, "UTF-8");
		MultipartFile multipartFile = new MockMultipartFile(name, name, "", is);
        return multipartFile;
	}
	
	@Test
	public void validaDadosQuantidadeIgualUmQuandoVemVazioOuZero() throws InstantiationException, IllegalAccessException, IOException {
		MultipartFile arquivoLote = getMockMultipartFile("file", loteXml);
		LotePedidosDto dto = new LotePedidosDto();
		dto = parser.parse(arquivoLote, dto);
		dto.getPedidos().get(0).setQtd(BigDecimal.ONE);
		LotePedidosDto normalizado = normalizador.normalizar(dto);
		BigDecimal qtd = normalizado.getPedidos().get(0).getQtd();
		assertEquals(new BigDecimal("1"), qtd);
	}
	
	@Test
	public void validaDadosValorTotalQuandoQuantidadeMaiorQueCinco() throws InstantiationException, IllegalAccessException, IOException {
		MultipartFile arquivoLote = getMockMultipartFile("file", loteXml);
		LotePedidosDto dto = new LotePedidosDto();
		dto = parser.parse(arquivoLote, dto);
		dto.getPedidos().get(0).setQtd(new BigDecimal("6"));
		LotePedidosDto normalizado = normalizador.normalizar(dto);
		BigDecimal valorTotal = normalizado.getPedidos().get(0).getVldTotal();
		assertEquals(new BigDecimal("9.499999999999999972244424384371086489409208297729492187500"), valorTotal);
	}
	
	@Test
	public void validaDadosValorTotalQuandoQuantidadeMaiorQueDez() throws InstantiationException, IllegalAccessException, IOException {
		MultipartFile arquivoLote = getMockMultipartFile("file", loteXml);
		LotePedidosDto dto = new LotePedidosDto();
		dto = parser.parse(arquivoLote, dto);
		dto.getPedidos().get(0).setQtd(new BigDecimal("11"));
		LotePedidosDto normalizado = normalizador.normalizar(dto);
		BigDecimal valorTotal = normalizado.getPedidos().get(0).getVldTotal();
		assertEquals(new BigDecimal("8.99999999999999994448884876874217297881841659545898437500"), valorTotal);
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
}
