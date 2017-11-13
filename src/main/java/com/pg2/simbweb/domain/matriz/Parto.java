package com.pg2.simbweb.domain.matriz;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by RafaelMq on 23/10/2016.
 */
public class Parto {

	
	private String status;
	private String descricao;
	private String idFichaMatriz;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataParto;
	
	

	public Date getDataParto() {
		return dataParto;
	}

	public void setDataParto(Date dataParto) {
		this.dataParto = dataParto;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getIdFichaMatriz() {
		return idFichaMatriz;
	}

	public void setIdFichaMatriz(String idFichaMatriz) {
		this.idFichaMatriz = idFichaMatriz;
	}

	

}
