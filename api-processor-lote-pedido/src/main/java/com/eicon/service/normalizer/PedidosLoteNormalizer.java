package com.eicon.service.normalizer;

import java.math.BigDecimal;
import java.util.Date;

import com.eicon.dto.LotePedidosDto;
import com.eicon.dto.PedidoDto;

public class PedidosLoteNormalizer {

	public PedidosLoteNormalizer () {}
	
	public LotePedidosDto normalizar(LotePedidosDto lote) {
		for (PedidoDto pedido : lote.getPedidos()) {
			if (pedido.getData() == null) {
				pedido.setData(new Date()); // Quando a data estiver nula, coloca data atual
			}
			if (pedido.getQtd() == null || pedido.getQtd().equals(BigDecimal.ZERO)) {  // Quando a quantidade não for enviada, assume 1
				pedido.setQtd(new BigDecimal("1"));
			}
			//Caso a quantidade seja maior que 5 aplicar 5% de desconto no valor total, para quantidades a 
			//partir de 10 aplicar 10% de desconto no valor total.
			BigDecimal valorTotal = pedido.getVldTotal();
			if (pedido.getQtd().compareTo(new BigDecimal("5")) > 0 && pedido.getQtd().compareTo(new BigDecimal("10")) < 0) {
				valorTotal = valorTotal.subtract(valorTotal.multiply(new BigDecimal(0.05))); // subtrai 5% do valor
				pedido.setVldTotal(valorTotal);
			} else if (pedido.getQtd().compareTo(new BigDecimal("10")) > 0) {
				valorTotal = valorTotal.subtract(valorTotal.multiply(new BigDecimal(0.10))); // subtrai 5% do valor
				pedido.setVldTotal(valorTotal);
			} 
		}
		return lote;
	}
}
