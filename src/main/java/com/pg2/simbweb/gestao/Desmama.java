package com.pg2.simbweb.gestao;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class Desmama {
	
	@NotNull(message = "Bezerro é obrigatório")
	private String idBovino;
	
	private String idFichaMatriz;
	
	@NotNull(message = "Data da Desmama é obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataDesmama;
	
	@NotNull(message = "Peso do Bovino é obrigatório")
	private Double peso;

	

	public String getIdFichaMatriz() {
		return idFichaMatriz;
	}
	public void setIdFichaMatriz(String idFichaMatriz) {
		this.idFichaMatriz = idFichaMatriz;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public String getIdBovino() {
		return idBovino;
	}
	public void setIdBovino(String idBovino) {
		this.idBovino = idBovino;
	}

	public Date getDataDesmama() {
		return dataDesmama;
	}
	public void setDataDesmama(Date dataDesmama) {
		this.dataDesmama = dataDesmama;
	}
	
	
}
