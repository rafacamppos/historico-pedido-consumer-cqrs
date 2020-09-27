package com.example.demo.dto;

import java.util.Date;

import com.example.demo.domain.HistoricoPedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoricoPedidoDTO extends  HistoricoPedido{//Data Transfer Object
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")	
	private Integer id;
	
	@JsonProperty("instante")	
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm")
	private Date instante;
	
	@JsonProperty("nomeProduto")	
	private String nomeProduto;
	
	@JsonProperty("valorProduto")	
	private Double valorProduto;
	
	public HistoricoPedidoDTO(HistoricoPedido obj) {
		id = obj.getId();
		nomeProduto = obj.getNomeProduto();
		instante = obj.getInstante();
		valorProduto = obj.getValorProduto();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Double getValorProduto() {
		return valorProduto;
	}

	public void setValorProduto(Double valorProduto) {
		this.valorProduto = valorProduto;
	}

	
}
