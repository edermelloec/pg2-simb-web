package com.pg2.simbweb.domain.bovino;

import java.util.Date;

public class Desmama {
	private Long idDesmama;
    private Date dataDesmama;
    private Boolean status;

    public Desmama(){
        status = true;
    }

    

	public Long getIdDesmama() {
		return idDesmama;
	}

	public void setIdDesmama(Long idDesmama) {
		this.idDesmama = idDesmama;
	}

	public Date getDataDesmama() {
		return dataDesmama;
	}

	public void setDataDesmama(Date dataDesmama) {
		this.dataDesmama = dataDesmama;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idDesmama == null) ? 0 : idDesmama.hashCode());
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
		Desmama other = (Desmama) obj;
		if (idDesmama == null) {
			if (other.idDesmama != null)
				return false;
		} else if (!idDesmama.equals(other.idDesmama))
			return false;
		return true;
	}
}
