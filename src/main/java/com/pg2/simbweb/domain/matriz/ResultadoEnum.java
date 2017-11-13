package com.pg2.simbweb.domain.matriz;

public enum ResultadoEnum {

	Cheia("Cheia"),
	Vazia("Vazia");

	private String descricao;
	
	ResultadoEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
