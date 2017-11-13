package com.pg2.simbweb.domain.matriz;




import java.util.Date;
import java.util.List;

/**
 * Created by RafaelMq on 04/11/2016.
 */


public class FichaMatriz {



    private Long idFichaMatriz;
    private List<Inseminacao> inseminacao;
    private List<DiagnosticoGestacao> diagnosticoGestacao;
    private List<IntervaloParto> intervaloParto;
    private Date dataInclusao;
    private Boolean status;

    public FichaMatriz(){
        status = true;
    }


    public Long getIdFichaMatriz() {
        return idFichaMatriz;
    }

    public void setIdFichaMatriz(Long idFichaMatriz) {
        this.idFichaMatriz = idFichaMatriz;
    }

    public List<Inseminacao> getInseminacao() {
        return inseminacao;
    }

    public void setInseminacao(List<Inseminacao> inseminacao) {
        this.inseminacao = inseminacao;
    }

    public List<DiagnosticoGestacao> getDiagnosticoGestacao() {
        return diagnosticoGestacao;
    }

    public void setDiagnosticoGestacao(List<DiagnosticoGestacao> diagnosticoGestacao) {
        this.diagnosticoGestacao = diagnosticoGestacao;
    }

    public List<IntervaloParto> getIntervaloParto() {
        return intervaloParto;
    }

    public void setIntervaloParto(List<IntervaloParto> intervaloParto) {
        this.intervaloParto = intervaloParto;
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


