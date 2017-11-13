package com.pg2.simbweb.domain.matriz;

public enum StatusEnum {

	Vivo("Vivo"),
	Morto("Morto");

	private String descricao;
	
	StatusEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
