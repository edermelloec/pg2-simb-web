package com.pg2.simbweb.domain.bovino;



import java.util.Date;

/**
 * Created by RafaelMq on 03/11/2016.
 */


public class Fazenda  {


    private Long idFazenda;
    private String nomeFazenda;
    private String enderecoFazenda;
    private Date dataInclusao;
    private Boolean status;


    public Fazenda(){
        status = true;
    }


    public Long getIdFazenda() {
        return idFazenda;
    }

    public void setIdFazenda(Long idFazenda) {
        this.idFazenda = idFazenda;
    }

    public String getNomeFazenda() {
        return nomeFazenda;
    }

    public void setNomeFazenda(String nomeFazenda) {
        this.nomeFazenda = nomeFazenda;
    }

    public String getEnderecoFazenda() {
        return enderecoFazenda;
    }

    public void setEnderecoFazenda(String enderecoFazenda) {
        this.enderecoFazenda = enderecoFazenda;
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
