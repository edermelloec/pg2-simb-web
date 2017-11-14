package com.pg2.simbweb.controller;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pg2.simbweb.client.BovinoClient;
import com.pg2.simbweb.client.EccClient;

import com.pg2.simbweb.client.PesoClient;
import com.pg2.simbweb.client.TarefaClient;
import com.pg2.simbweb.domain.bovino.Bovino;
import com.pg2.simbweb.domain.bovino.Ecc;
import com.pg2.simbweb.domain.bovino.Fazenda;
import com.pg2.simbweb.domain.bovino.Pelagem;
import com.pg2.simbweb.domain.bovino.Peso;
import com.pg2.simbweb.domain.bovino.Proprietario;
import com.pg2.simbweb.domain.bovino.Raca;
import com.pg2.simbweb.domain.tarefa.Tarefa;


@Controller
@RequestMapping("/bovinos")
public class BovinosController {
	
	private static final String ALTERAR_BOVINO_VIEW = "bovino/AlterarBovino";
	private static final String PESQUISA_BOVINO_VIEW = "/bovino/PesquisaBovinos";

	Bovino auxBovino;
	
	@Autowired
	private BovinoClient bovinoClient;
	
	@Autowired
	private TarefaClient tarefaClient;
	
	@Autowired
	private EccClient eccClient;
	
	@Autowired
	private PesoClient pesoClient;



	/* ----------------------------------- GET ---------------------------------------*/
	
	@RequestMapping("/alterar")
	public String alterar() {
		return "/bovino/AlterarBovino";
	}
	
	
	
	
	@RequestMapping
	public ModelAndView pesquisa(@RequestParam(defaultValue="todos") String descricao, String tipoBusca, RedirectAttributes attributes) {
		List<Bovino> bovinos  = bovinoClient.listarPorNome(descricao,tipoBusca);
		
		
		String mensDesmama = null;
		String mensParto = null;
		String mensagem = null;
		
		Date dataDesmama = new Date();
		Date dataParto = new Date();
		
		Calendar calDesmama = Calendar.getInstance();
		Calendar calParto = Calendar.getInstance();
		
		calDesmama.add(Calendar.DAY_OF_MONTH, -205);
		calParto.add(Calendar.DAY_OF_MONTH, -285);
		
		dataDesmama.setTime(calDesmama.getTime().getTime());
		dataParto.setTime(calParto.getTime().getTime());
		
		if(descricao.equals("todos")) {
			for(int i=0;i<bovinos.size();i++) {
				
				
				
				if(String.valueOf(bovinos.get(i).getDataNascimento().getTime()).substring(0, 5).equals(String.valueOf(dataDesmama.getTime()).substring(0, 5))) {
					if(mensDesmama!=null) {
						mensDesmama = mensDesmama +", ( Desmama, " +bovinos.get(i).getNomeBovino()+" ) ";
					}else {
						mensDesmama = " ( Desmama, " +bovinos.get(i).getNomeBovino()+" ) ";
					}
					
				}
				
				if(String.valueOf(bovinos.get(i).getDataNascimento().getTime()).substring(0,5).equals(String.valueOf(dataParto.getTime()).substring(0,5))) {
					if(mensParto != null) {
						mensParto  = mensParto + ", ( Parto, " + bovinos.get(i).getNomeBovino()+" ) ";
					}else {
						mensParto = " ( Parto, " + bovinos.get(i).getNomeBovino()+" ) ";;
					}
					
				}
			}
			System.out.println(mensDesmama + " - " + mensParto);
			if(mensDesmama != null && mensParto != null ) {
				mensagem = mensDesmama + mensParto;
			}else if(mensParto != null) {
				mensagem = mensParto;
			}else if(mensDesmama != null) {
				mensagem = mensDesmama;
			}
		
		}
		
		ModelAndView mv = new ModelAndView(PESQUISA_BOVINO_VIEW);
		mv.addObject("mensagem",mensagem);
		mv.addObject("bovinos", bovinos);
		
		
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Long codigo) {
		auxBovino = bovinoClient.listarUm(codigo);
		
		ModelAndView mv = new ModelAndView(ALTERAR_BOVINO_VIEW); 
		mv.addObject(auxBovino);
		return mv;
	}
	
	
	
	
	
	
	/* ----------------------------------- POST ---------------------------------------*/
	
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String alterar(@Validated Bovino bovino, RedirectAttributes attributes) {
		
		
		Bovino bovinoAux = bovinoClient.listarUm(bovino.getIdBovino());
		bovino.setEcc(bovinoAux.getEcc());
		bovino.setPeso(bovinoAux.getPeso());
		
		
		try {
			bovinoClient.alterar(bovino);
			attributes.addFlashAttribute("mensagem", "Bovino alterado com sucesso!");
			return "redirect:/bovinos";
		} catch (IllegalArgumentException e) {
			//errors.rejectValue("dataVencimento", null, e.getMessage());
			return ALTERAR_BOVINO_VIEW;
		}
	}
	

	

	
	
	/* ------------------------------------ PUT ----------------------------------------*/
	
	@RequestMapping(value="/editarEcc", method = RequestMethod.PUT)
	public String alterarEcc(@Validated Ecc ecc, Errors errors, RedirectAttributes attributes) {
	
		
		
		try {
			eccClient.alterar(ecc);
			attributes.addFlashAttribute("mensagem", "Ecc alterado com sucesso!");
			return "redirect:/bovinos/"+auxBovino.getIdBovino();
			
		} 	catch (HttpServerErrorException e) {
			//errors.rejectValue("dataVencimento", null, e.getMessage());
			String erro = ""+e.getStatusCode();
			return "redirect:/"+ erro;
			
		}	catch (IllegalArgumentException e) {
			//errors.rejectValue("dataVencimento", null, e.getMessage());
			return ALTERAR_BOVINO_VIEW;
		}
			
			
			
	}
	
	@RequestMapping(value="/editarPeso", method = RequestMethod.PUT)
	public String alterarEcc(@Validated Peso peso, Errors errors, RedirectAttributes attributes) {
		
			Peso auxPeso = pesoClient.listarUm(peso.getIdPeso());
			peso.setDataPesagem(auxPeso.getDataPesagem());
			
	
		try {
			pesoClient.alterar(peso);
			attributes.addFlashAttribute("mensagem", "Peso alterado com sucesso!");
			return "redirect:/bovinos/"+auxBovino.getIdBovino();
			
		} 	catch (HttpServerErrorException e) {
			//errors.rejectValue("dataVencimento", null, e.getMessage());
			String erro = ""+e.getStatusCode();
			return "redirect:/"+ erro;
			
		}	catch (IllegalArgumentException e) {
			//errors.rejectValue("dataVencimento", null, e.getMessage());
			return ALTERAR_BOVINO_VIEW;
		}
	}
	
	/* ----------------------------------- DELETE ---------------------------------------*/
	
	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluirBovino(@PathVariable Long codigo, RedirectAttributes attributes) {
		
		Bovino bovino = bovinoClient.listarUm(codigo);
		bovino.setStatus(false);
		
		List<Tarefa> tarefas = tarefaClient.listarTarefasBovinoMatriz(bovino.getIdBovino());

		try {
			
			bovinoClient.alterar(bovino);
			for(int i=0; i < tarefas.size() ; i++){
				Tarefa tarefa = tarefas.get(i);
				tarefa.setStatus(false);
				tarefaClient.alterar(tarefa);
				
			}
			attributes.addFlashAttribute("mensagem", "Bovino excluido com sucesso !");
			return "redirect:/bovinos";
		} catch (IllegalArgumentException e) {
			//errors.rejectValue("dataVencimento", null, e.getMessage());
			return PESQUISA_BOVINO_VIEW;
		}

	}
	
	
	@RequestMapping(value="/ecc/{idEcc}", method = RequestMethod.DELETE)
	public String excluireEccBovino(@PathVariable Long idEcc, RedirectAttributes attributes) {

		try {
			eccClient.deletar(idEcc);
			//eccClient.deletar(idEcc);
			attributes.addFlashAttribute("mensagem", "Ecc excluido com sucesso !");
			return "redirect:/bovinos/"+auxBovino.getIdBovino();
		} catch (HttpServerErrorException e) {
			//errors.rejectValue("dataVencimento", null, e.getMessage());
			String erro = ""+e.getStatusCode();
			return "redirect:/"+ erro;
			
		}
		
	}
	
	
	@RequestMapping(value="/peso/{idPeso}", method = RequestMethod.DELETE)
	public String excluirePesoBovino(@PathVariable Long idPeso, RedirectAttributes attributes) {

		try {
			pesoClient.deletar(idPeso);
			//eccClient.deletar(idEcc);
			attributes.addFlashAttribute("mensagem", "Peso excluido com sucesso !");
			return "redirect:/bovinos/"+auxBovino.getIdBovino();
		} catch (HttpServerErrorException e) {
			//errors.rejectValue("dataVencimento", null, e.getMessage());
			String erro = ""+e.getStatusCode();
			return "redirect:/"+ erro;
			
		}
		
	}
	
	
	
	/* ---------------------- Model Attributes ------------------------*/
	
	@ModelAttribute("todasRacasBovino")
	public List<Raca> todosRacasBovino() {
		List<Raca> racas = bovinoClient.listarRacas();
		return racas;
	}
	
	@ModelAttribute("todasPelagemsBovino")
	public List<Pelagem> todasPelagemsBovino() {
		List<Pelagem> pelagems = bovinoClient.listarPelagems();
		return pelagems;
	}
	
	@ModelAttribute("todosProprietariosBovino")
	public List<Proprietario> todosProprietariosBovino() {
		List<Proprietario> proprietarios = bovinoClient.listarProprietarios();
		return proprietarios;
	}
	
	@ModelAttribute("todasFazendasBovino")
	public List<Fazenda> todasFazendasBovino() {
		List<Fazenda> fazendas = bovinoClient.listarFazendas();
		return fazendas;
	}
	
	
	@ModelAttribute("todosGenerosBovino")
	public List<Boolean> todosGenerosBovino() {
		List<Boolean> generos = new ArrayList<Boolean>();
		generos.add(true);
		generos.add(false);
		return generos;
	}
	
	
	
	@ModelAttribute("todosEccs")
	public List<Ecc> todosEccsBovino() {
		List<Ecc> eccs = new ArrayList<Ecc>();

		for(int i=0; i<9 ; i++){
			Ecc ecc = new Ecc();
			ecc.setIdECC(0L);
			ecc.setEscore(i+1);
			eccs.add(ecc);
		}
		return eccs;
	}
	
	
}
