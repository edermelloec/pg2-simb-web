package com.pg2.simbweb.domain.matriz;



import java.util.Date;

/**
 * Created by RafaelMq on 04/11/2016.
 */


public class Inseminador{

    private Long idInseminador;
    private String nomeInseminador;
    private Date dataInclusao;
    private Boolean status;

    public Inseminador(){
        status = true;

    }


    public Long getIdInseminador() {
        return idInseminador;
    }

    public void setIdInseminador(Long idInseminador) {
        this.idInseminador = idInseminador;
    }

    public String getNomeInseminador() {
        return nomeInseminador;
    }

    public void setNomeInseminador(String nomeInseminador) {
        this.nomeInseminador = nomeInseminador;
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
}
