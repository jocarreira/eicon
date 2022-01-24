package com.eicon.kafka.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class PedidoDto {

    private String nPedido;
	
    private Date data;
	
    private String nomeProduto;
	
    private BigDecimal valor;
	
    private BigDecimal qtd;
	
    private BigDecimal idCliente;
    
    private BigDecimal vldTotal;

	public String getnPedido() {
		return nPedido;
	}

	public void setnPedido(String nPedido) {
		this.nPedido = nPedido;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getQtd() {
		return qtd;
	}

	public void setQtd(BigDecimal qtd) {
		this.qtd = qtd;
	}

	public BigDecimal getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(BigDecimal idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public String toString() {
		return "PedidoXmlDto [nPedido=" + nPedido + ", data=" + data + ", nomeProduto=" + nomeProduto + ", valor="
				+ valor + ", qtd=" + qtd + ", idCliente=" + idCliente + "]";
	}

	public BigDecimal getVldTotal() {
		return vldTotal;
	}

	public void setVldTotal(BigDecimal vldTotal) {
		this.vldTotal = vldTotal;
	}

    
	
}
