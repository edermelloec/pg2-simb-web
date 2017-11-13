package com.pg2.simbweb.gestao;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class Abatido {
	
	private Long idAbatido;
	
	@NotNull(message = "Peso é obrigatório")
    private Double peso;
	
	@NotNull(message = "Data do Abate é obrigatório")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataAbate;
	
	
	@NotBlank(message = "Descrição é obrigatório")
	private String descricao;
	
	private String idBovino;

	@Override
	public String toString() {
		return "Abatido [peso=" + peso + ", dataAbate=" + dataAbate + ", descricao=" + descricao + ", idBovino="
				+ idBovino + "]";
	}
	
	
	public Long getIdAbatido() {
		return idAbatido;
	}

	public void setIdAbatido(Long idAbatido) {
		this.idAbatido = idAbatido;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Date getDataAbate() {
		return dataAbate;
	}

	public void setDataAbate(Date dataAbate) {
		this.dataAbate = dataAbate;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIdBovino() {
		return idBovino;
	}

	public void setIdBovino(String idBovino) {
		this.idBovino = idBovino;
	}
	
	
	
}
