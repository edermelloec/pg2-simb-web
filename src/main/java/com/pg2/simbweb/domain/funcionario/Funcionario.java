package com.pg2.simbweb.domain.funcionario;

public class Funcionario {
	Long idFuncionario;
	String nome;
	String imei;
	
	public Funcionario(){
		
	}
	
	
	
	public Long getIdFuncionario() {
		return idFuncionario;
	}



	public void setIdFuncionario(Long idFuncionario) {
		this.idFuncionario = idFuncionario;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idFuncionario == null) ? 0 : idFuncionario.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (idFuncionario == null) {
			if (other.idFuncionario != null)
				return false;
		} else if (!idFuncionario.equals(other.idFuncionario))
			return false;
		return true;
	}
	
	

}
