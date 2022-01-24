package com.eicon.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pedido {

    public Pedido(Long numPedido, LocalDate data, String nomeProduto, BigDecimal valor, Long qtd, Long idCliente) {
		this.numPedido = numPedido;
		this.data = data;
		NomeProduto = nomeProduto;
		this.valor = valor;
		this.qtd = qtd;
		this.idCliente = idCliente;
	}
    
    private Long numPedido;
    private LocalDate data;
    private String NomeProduto;
    private BigDecimal valor;
    private Long qtd;
    private Long idCliente;
    
}
