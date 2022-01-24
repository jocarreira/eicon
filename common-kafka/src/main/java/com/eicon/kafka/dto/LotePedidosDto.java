package com.eicon.kafka.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LotePedidosDto extends LoteDto {
	
	@XmlElement(name="pedidos")
	private List<PedidoDto> pedidos;

	public List<PedidoDto> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<PedidoDto> pedidos) {
		this.pedidos = pedidos;
	}
	@Override
	public String toString() {
		return "LotePedidosXmlDto [pedidos=" + pedidos + "]";
	}
	
}
