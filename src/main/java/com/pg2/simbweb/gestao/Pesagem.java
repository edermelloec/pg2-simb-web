package com.pg2.simbweb.gestao;

import java.util.Date;

public class Pesagem {
	String nome;
	Date dataNascimento;
	Date dataPesagem;
	Double peso;
	Double ganho;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Date getDataPesagem() {
		return dataPesagem;
	}
	public void setDataPesagem(Date dataPesagem) {
		this.dataPesagem = dataPesagem;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public Double getGanho() {
		return ganho;
	}
	public void setGanho(Double ganho) {
		this.ganho = ganho;
	}

}
