package com.pg2.simbweb.gestao;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class Morte {
	
	
	private Long idMorte;
	
	@NotNull(message = "Data da Morte é obrigatório")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataMorte;
	
	@NotBlank(message = "Causa da Morte é obrigatório")
	private String causa;
	
	
	private String idBovino;
	
	private Boolean status;
	
	
	
	
	
	public Long getIdMorte() {
		return idMorte;
	}
	public void setIdMorte(Long idMorte) {
		this.idMorte = idMorte;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public String getIdBovino() {
		return idBovino;
	}
	public void setIdBovino(String idBovino) {
		this.idBovino = idBovino;
	}
	public String getCausa() {
		return causa;
	}
	public void setCausa(String causa) {
		this.causa = causa;
	}

	public Date getDataMorte() {
		return dataMorte;
	}
	public void setDataMorte(Date dataMorte) {
		this.dataMorte = dataMorte;
	}
	

}
