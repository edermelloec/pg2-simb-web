package com.pg2.simbweb.gestao;

import java.util.Date;

public class Touro {
	 	private Long idFichaTouro;
	 	private Long idBovino;
	    private Date dataInclusao = new Date();
	    private Boolean status;
	    
	    
	    
	    public Touro() {
	    	status = true;
	    }
		public Long getIdFichaTouro() {
			return idFichaTouro;
		}
		public void setIdFichaTouro(Long idFichaTouro) {
			this.idFichaTouro = idFichaTouro;
		}
		public Long getIdBovino() {
			return idBovino;
		}
		public void setIdBovino(Long idBovino) {
			this.idBovino = idBovino;
		}
		public Date getDataInclusao() {
			return dataInclusao;
		}
		public void setDataInclusao(Date dataInclusao) {
			this.dataInclusao = dataInclusao;
		}
		public Boolean getStatus() {
			return status;
		}
		public void setStatus(Boolean status) {
			this.status = status;
		}
	    
	    
	    
	    
}
