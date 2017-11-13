package com.pg2.simbweb.domain.bovino;


import java.util.Date;

/**
 * Created by RafaelMq on 06/11/2016.
 */
public class Proprietario {


    private Long idProprietario;
    private String nomeProprietario;
    private Date dataInclusao = new Date();
    private Boolean status;

    public Proprietario(){
        status = true;
    }


    public Long getIdProprietario() {
        return idProprietario;
    }

    public void setIdProprietario(Long idProprietario) {
        this.idProprietario = idProprietario;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
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
