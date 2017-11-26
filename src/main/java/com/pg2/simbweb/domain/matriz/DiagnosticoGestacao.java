package com.pg2.simbweb.domain.matriz;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by RafaelMq on 23/10/2016.
 */
public class DiagnosticoGestacao {

	private String idFichaMatriz;

	
	private String resultado;

	private String descricao;
	
	private Long idInseminacao;
	
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInclusao;
	
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataResultado;

	
	
	
	@Override
	public String toString() {
		return "DiagnosticoGestacao [idFichaMatriz=" + idFichaMatriz + ", resultado=" + resultado + ", descricao="
				+ descricao + ", idInseminacao=" + idInseminacao + ", dataInclusao=" + dataInclusao + ", dataResultado="
				+ dataResultado + "]";
	}


	public Long getIdInseminacao() {
		return idInseminacao;
	}


	public void setIdInseminacao(Long idInseminacao) {
		this.idInseminacao = idInseminacao;
	}


	public String getResultado() {
		return resultado;
	}


	public void setResultado(String resultado) {
		this.resultado = resultado;
	}


	public Date getDataResultado() {
		return dataResultado;
	}

	public void setDataResultado(Date dataResultado) {
		this.dataResultado = dataResultado;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
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
