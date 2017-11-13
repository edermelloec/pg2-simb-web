package com.pg2.simbweb.domain.bovino;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;

public class Gestao {
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal fertilidade;
	private String nome;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Gestao() {
		
	}
	public BigDecimal getFertilidade() {
		return fertilidade;
	}

	public void setFertilidade(BigDecimal fertilidade) {
		this.fertilidade = fertilidade;
	}
}
