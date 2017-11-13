package com.pg2.simbweb.domain.bovino;


import java.util.Date;

/**
 * Created by RafaelMq on 03/11/2016.
 */


public class Pelagem {


    private Long idPelagem;
    private String nomePelagem;
    private Date dataInclusao;
    private Boolean status;

    public Pelagem(){
        status = true;
    }


    public Long getIdPelagem() {
        return idPelagem;
    }

    public String getNomePelagem() {
        return nomePelagem;
    }

    public void setIdPelagem(Long idPelagem) {
        this.idPelagem = idPelagem;
    }

    public void setNomePelagem(String nomePelagem) {
        this.nomePelagem = nomePelagem;
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
