package com.pg2.simbweb.gestao;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class Desfrute {
	
	@NotNull(message = "Data inicial é obrigatório")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataInicio;
	
	@NotNull(message = "Data final é obrigatório")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dataFinal;

}
