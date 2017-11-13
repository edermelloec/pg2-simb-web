package com.pg2.simbweb.domain.tarefa;


import java.util.Date;

import com.pg2.simbweb.domain.bovino.Bovino;

/**
 * Created by RafaelMq on 16/11/2016.
 */


public class Tarefa  {


    private Long idTarefa;
    private Bovino bovinoMatriz;
    private TipoTarefaEnum tipoTarefa;
    private Date dataInclusao = new Date();
    private String imei;
    private boolean statusDaTarefa;
    private Date dataConclusao;
    private boolean status;


    public Tarefa(){
    	status = true;
    }
    
    public Long getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(Long idTarefa) {
        this.idTarefa = idTarefa;
    }

    public Bovino getBovinoMatriz() {
        return bovinoMatriz;
    }

    public void setBovinoMatriz(Bovino bovinoMatriz) {
        this.bovinoMatriz = bovinoMatriz;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Date getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatusDaTarefa() {
        return statusDaTarefa;
    }

    public void setStatusDaTarefa(boolean statusDaTarefa) {
        this.statusDaTarefa = statusDaTarefa;
    }

    public TipoTarefaEnum getTipoTarefa() {
        return tipoTarefa;
    }

    public void setTipoTarefa(TipoTarefaEnum tipoTarefa) {
        this.tipoTarefa = tipoTarefa;
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
		result = prime * result + ((idTarefa == null) ? 0 : idTarefa.hashCode());
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
		Tarefa other = (Tarefa) obj;
		if (idTarefa == null) {
			if (other.idTarefa != null)
				return false;
		} else if (!idTarefa.equals(other.idTarefa))
			return false;
		return true;
	}


    
    
    
    
}
