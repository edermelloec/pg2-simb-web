package com.pg2.simbweb.domain.bovino;





//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.pg2.simbweb.domain.matriz.FichaMatriz;





/**
 * Created by RafaelMq on 26/09/2016.
 */

/**
 * @author RafaelMq
 *
 */
public class Bovino {
    



    /**
	 * 
	 */

	private Long idBovino;
    private String tag;
    
    @NotBlank(message = "Nome do bovino é obrigatório")
    private String nomeBovino;
    private Boolean genero;
    
    @NotBlank(message = "Nome do pai é obrigatório")
    private String pai;
    
    @NotBlank(message = "Nome do mãe é obrigatório")
    private String mae;
    
    @NotNull(message = "Data de Nascimento é obrigatório")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataNascimento;
    private Raca raca;
    private Pelagem pelagem;
    private Fazenda fazenda;
    private Proprietario proprietario;
    private List<Ecc> ecc;
    private List<Peso> peso;
    private FichaMatriz fichaMatriz;
    private Boolean status;
    private Date dataInclusao;
    private String urlFoto;



    public Bovino(){
        status = true;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Long getIdBovino() {
        return idBovino;
    }

    public void setIdBovino(Long idBovino) {
        this.idBovino = idBovino;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getNomeBovino() {
        return nomeBovino;
    }

    public void setNomeBovino(String nomeBovino) {
        this.nomeBovino = nomeBovino;
    }

    public Boolean getGenero() {
        return genero;
    }

    public void setGenero(Boolean genero) {
        this.genero = genero;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public Pelagem getPelagem() {
        return pelagem;
    }

    public void setPelagem(Pelagem pelagem) {
        this.pelagem = pelagem;
    }

    public Fazenda getFazenda() {
        return fazenda;
    }

    public void setFazenda(Fazenda fazenda) {
        this.fazenda = fazenda;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    public List<Ecc> getEcc() {
        return ecc;
    }

    public void setEcc(List<Ecc> ecc) {
        this.ecc = ecc;
    }

    public List<Peso> getPeso() {
        return peso;
    }

    public void setPeso(List<Peso> peso) {
        this.peso = peso;
    }

    public FichaMatriz getFichaMatriz() {
        return fichaMatriz;
    }

    public void setFichaMatriz(FichaMatriz fichaMatriz) {
        this.fichaMatriz = fichaMatriz;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }


//	@Override
//	public String toString() {
//		
//		String generoString = genero ? "macho" : "femea";
//		String statusString = status ? "ativo" : "Inativo";
//		String matrizString = fichaMatriz == null ? "matriz" : "";
//		String dataNascimentoString = "";
//		try {
//			dataNascimentoString = formataDateToString(dataNascimento);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return "" + nomeBovino + "." + generoString + "." + pai + "." + mae
//				+ "." + dataNascimentoString + "." + raca.getNomeRaca() + "." + pelagem.getNomePelagem() + "."
//				+ fazenda.getNomeFazenda() + "." + proprietario.getNomeProprietario() + ".ecc" + ecc.get(ecc.size()-1).getEscore() 
//				+ ".peso" + peso.get(peso.size()-1).getPeso() + "."
//				+ matrizString + "." + statusString + "";
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idBovino == null) ? 0 : idBovino.hashCode());
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
		Bovino other = (Bovino) obj;
		if (idBovino == null) {
			if (other.idBovino != null)
				return false;
		} else if (!idBovino.equals(other.idBovino))
			return false;
		return true;
	}

//	public static String formataDateToString(Date data) throws Exception {
//        if (data == null || data.equals(""))
//            return null;
//        String Auxdate = null;
//        try {
//            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//            Auxdate = formatter.format(data);
//        } catch (Exception e) {
//            throw e;
//        }
//        return Auxdate;
//    }


}
