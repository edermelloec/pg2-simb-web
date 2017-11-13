package com.pg2.simbweb.domain.matriz;




import java.util.Date;

/**
 * Created by RafaelMq on 04/11/2016.
 */


public class IntervaloParto  {


    private Long idIntervaloParto;
    private int intervalo;
    private Date dataInclusao;
    private Boolean status;

    public IntervaloParto(){
        status = true;
    }



    public Long getIdIntervaloParto() {
        return idIntervaloParto;
    }

    public void setIdIntervaloParto(Long idIntervaloParto) {
        this.idIntervaloParto = idIntervaloParto;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
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
