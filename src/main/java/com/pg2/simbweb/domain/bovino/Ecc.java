package com.pg2.simbweb.domain.bovino;


import java.util.Date;

/**
 * Created by RafaelMq on 03/11/2016.
 */


public class Ecc {


    private Long idECC;
    private Integer escore;
    private Date dataInclusao;
    private Boolean status;

    public Ecc(){
        status = true;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Long getIdECC() {
        return idECC;
    }

    public void setIdECC(Long idECC) {
        this.idECC = idECC;
    }

    public int getEscore() {
        return escore;
    }

    public void setEscore(int escore) {
        this.escore = escore;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idECC == null) ? 0 : idECC.hashCode());
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
		Ecc other = (Ecc) obj;
		if (idECC == null) {
			if (other.idECC != null)
				return false;
		} else if (!idECC.equals(other.idECC))
			return false;
		return true;
	}
    
    
}
