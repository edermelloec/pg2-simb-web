package com.pg2.simbweb.domain.bovino;


import java.util.Date;

/**
 * Created by RafaelMq on 08/11/2016.
 */
public class Raca  {


    private Long idRaca;
    private String nomeRaca;
    private Date dataInclusao;
    private Boolean status;

    public Raca(){
        status = true;
    }


    public Long getIdRaca() {
        return idRaca;
    }

    public void setIdRaca(Long idRaca) {
        this.idRaca = idRaca;
    }

    public String getNomeRaca() {
        return nomeRaca;
    }

    public void setNomeRaca(String nomeRaca) {
        this.nomeRaca = nomeRaca;
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
