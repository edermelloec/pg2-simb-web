package com.pg2.simbweb.domain.tarefa;

/**
 * Created by RafaelMq on 16/11/2016.
 */
public enum TipoTarefaEnum {
	
	Inseminação("Inseminação"),
    DiagnosticoDeGestacao("Diagnostico de Gestacao"),
    Parto("Parto"),
    CadastrarBovino("Cadastrar Bovino");

	private String descricao;
	
	TipoTarefaEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

   

}
