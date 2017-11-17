package com.pg2.simbweb.domain.bovino;






import java.util.Date;

import javax.validation.constraints.NotNull;


import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by RafaelMq on 03/11/2016.
 */



public class Peso  {


    private Long idPeso;
    
    @NotNull(message = "Data da Pesagem é obrigatório")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataPesagem;
    
    @NotNull(message = "Peso é obrigatório")
    private Double peso;
    
    private Date dataInclusao;
    private Boolean status;

    public Peso(){
        status = true;
    }


    public Long getIdPeso() {
        return idPeso;
    }

    public void setIdPeso(Long idPeso) {
        this.idPeso = idPeso;
    }

    public Date getDataPesagem() {
        return dataPesagem;
    }
    public void setDataPesagem(Date dataPesagem) {
        this.dataPesagem = dataPesagem;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
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
