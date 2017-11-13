package com.pg2.simbweb.gestao;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class Venda {
	private Long idVenda;
	
	@NotNull(message = "Peso é obrigatório")
    private Double peso;
	
	@NotNull(message = "Valor é obrigatório")
    private Double valor;
	
	@NotNull(message = "Data da Venda é obrigatório")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataVenda;
	
	private String idBovino;
	
	

	public String getIdBovino() {
		return idBovino;
	}

	public void setIdBovino(String idBovino) {
		this.idBovino = idBovino;
	}

	public Long getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(Long idVenda) {
		this.idVenda = idVenda;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	
	
	
}
