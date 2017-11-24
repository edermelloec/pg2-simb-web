package com.pg2.simbweb.domain.matriz;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;



/**
 * Created by RafaelMq on 20/10/2016
 */

public class Inseminacao {

	
	private Long idInseminacao;
	
	@NotNull(message = "Tipo é obrigatório")
	private String monta;
	
	@NotBlank(message = "Touro é obrigatório")
	private String touro;
	
	@NotNull(message = "Data da inseminação é obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataDaInseminacao;
	
	private Inseminador inseminador;
	
	
	
	@NotNull(message = "Data de previsão de parto é obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date previsaoParto;
	
	
	
	
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInclusao;
	
	
	
	private Boolean status;
	

	private Long matriz;
	


	public Long getMatriz() {
		return matriz;
	}

	public void setMatriz(Long matriz) {
		this.matriz = matriz;
	}

	public Long getIdInseminacao() {
		return idInseminacao;
	}

	public void setIdInseminacao(Long idInseminacao) {
		this.idInseminacao = idInseminacao;
	}

	@Override
	public String toString() {
		return "Inseminacao [monta=" + monta + ", touro=" + touro + ", dataDaInseminacao=" + dataDaInseminacao
				+ ", inseminador=" + inseminador + ", previsaoParto=" + previsaoParto + ", dataInclusao=" + dataInclusao
				+ ", status=" + status + "]";
	}

	public Inseminador getInseminador() {
		return inseminador;
	}

	public void setInseminador(Inseminador inseminador) {
		this.inseminador = inseminador;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Inseminacao() {
		status = true;
	}

	public String getTouro() {
		return touro;
	}

	public void setTouro(String touro) {
		this.touro = touro;
	}

	public Date getDataDaInseminacao() {
		return dataDaInseminacao;
	}

	
	public void setDataDaInseminacao(Date dataDaInseminacao) {
		this.dataDaInseminacao = dataDaInseminacao;
	}

	

	public Date getPrevisaoParto() {
		return previsaoParto;
	}

	public void setPrevisaoParto(Date previsaoParto) {
		this.previsaoParto = previsaoParto;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMonta() {
		return monta;
	}

	public void setMonta(String monta) {
		this.monta = monta;
	}

	

	

	

	// public String id;
	// public String dataInseminacao;
	// public String previsaoParto;
	// public String parto;

	// public Inseminacao(String id, String dataInseminacao, String previsaoParto,
	// String parto) {
	//
	// this.id = id;
	// this.dataInseminacao = dataInseminacao;
	// this.previsaoParto = previsaoParto;
	// this.parto = parto;
	// }

	// public static List<Inseminacao> getInseminacoes() {
	//
	// List<Inseminacao> inseminacaos = new ArrayList<Inseminacao>();
	// inseminacaos.add(new Inseminacao("1", "10/10/2016" , "10/08/2017" , "NÃO"));
	// inseminacaos.add(new Inseminacao("2", "10/10/2016" , "10/08/2017" , "SIM"));
	// inseminacaos.add(new Inseminacao("3", "10/10/2016" , "10/08/2017" , "SIM"));
	// inseminacaos.add(new Inseminacao("4", "10/10/2016" , "10/08/2017" , "SIM"));
	// inseminacaos.add(new Inseminacao("5", "10/10/2016" , "10/08/2017" , "SIM"));
	// inseminacaos.add(new Inseminacao("6", "10/10/2016" , "10/08/2017" , "SIM"));
	// inseminacaos.add(new Inseminacao("7", "10/10/2016" , "10/08/2017" , "SIM"));
	// inseminacaos.add(new Inseminacao("8", "10/10/2016" , "10/08/2017" , "SIM"));
	// inseminacaos.add(new Inseminacao("9", "10/10/2016" , "10/08/2017" , "SIM"));
	// return inseminacaos;
	// }
}