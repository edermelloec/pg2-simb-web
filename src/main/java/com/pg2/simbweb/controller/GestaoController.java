package com.pg2.simbweb.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pg2.simbweb.client.BovinoClient;
import com.pg2.simbweb.client.FuncionarioClient;
import com.pg2.simbweb.client.GestaoClient;
import com.pg2.simbweb.client.TarefaClient;
import com.pg2.simbweb.domain.bovino.Bovino;
import com.pg2.simbweb.domain.bovino.Ecc;
import com.pg2.simbweb.domain.bovino.Fazenda;
import com.pg2.simbweb.domain.bovino.Pelagem;
import com.pg2.simbweb.domain.bovino.Peso;
import com.pg2.simbweb.domain.bovino.Proprietario;
import com.pg2.simbweb.domain.bovino.Raca;
import com.pg2.simbweb.domain.funcionario.Funcionario;
import com.pg2.simbweb.domain.matriz.DiagnosticoGestacao;
import com.pg2.simbweb.domain.matriz.FichaMatriz;
import com.pg2.simbweb.domain.matriz.Inseminacao;
import com.pg2.simbweb.domain.matriz.Parto;
import com.pg2.simbweb.domain.matriz.TrueAndFalse;
import com.pg2.simbweb.domain.tarefa.Tarefa;
import com.pg2.simbweb.domain.tarefa.TipoTarefaEnum;
import com.pg2.simbweb.gestao.Abatido;
import com.pg2.simbweb.gestao.Desmama;
import com.pg2.simbweb.gestao.Morte;
import com.pg2.simbweb.gestao.Pesagem;
import com.pg2.simbweb.gestao.Touro;
import com.pg2.simbweb.gestao.Venda;

@Controller
@RequestMapping("/gestao")
public class GestaoController {
	@Autowired
	private FuncionarioClient funcionarioClient;

	@Autowired
	private BovinoClient bovinoClient;

	@Autowired
	private GestaoClient gestaoClient;

	@Autowired
	private TarefaClient tarefaClient;

	// ------------------------------ GESTÃO GERAL
	// --------------------------------------------------------
	@RequestMapping("/geral")
	public ModelAndView gestaoFertilidadetTotal() {

		GestaoClient gc = new GestaoClient();

		ModelAndView mv = new ModelAndView("gestao/Geral");

		mv.addObject("touroVaca", gc.touroVaca());

		mv.addObject("fertilidadeTotal", gc.fertilidadeTotal());
		mv.addObject("fertilidadePrimi", gc.fertilidadePrimi());
		mv.addObject("fertilidadeNovi", gc.fertilidadeNovi());
		mv.addObject("fertilidadeMult", gc.fertilidadeMult());

		mv.addObject("natalidadeTotal", gc.natalidadeTotal());
		mv.addObject("natalidadePrimi", gc.natalidadePrimi());
		mv.addObject("natalidadeNovi", gc.natalidadeNovi());
		mv.addObject("natalidadeMult", gc.natalidadeMult());

		mv.addObject("motalidadeTotal", gc.mortalidadeTodos());
		mv.addObject("motalidadePreParto", gc.mortalidadePreParto());
		mv.addObject("motalidadeDesmama", gc.mortalidadeDesmama());
		mv.addObject("motalidadeJovens", gc.mortalidadeJovens());
		mv.addObject("motalidadeAdulto", gc.mortalidadeAdulto());

		mv.addObject("desmama", gc.desmama());
		mv.addObject("abate", gc.abate());

		return mv;
	}

	// ------------------------------ DESMAMA
	// --------------------------------------------------------
	@RequestMapping("/desmama")
	public ModelAndView gestaoDesmama(@RequestParam(defaultValue = "SIMB11") String descricao) {

		GestaoClient gc = new GestaoClient();

		ModelAndView mv = new ModelAndView("gestao/desmama");
		mv.addObject("idadeDesmama", gc.idadeDesmame(descricao));

		return mv;
	}

	@RequestMapping("/adicionar/desmama")
	public ModelAndView adicionarDesmama(Desmama desmama) {
		ModelAndView mv = new ModelAndView("gestao/SalvarDesmama");
		return mv;
	}

	@RequestMapping(value = "/desmama", method = RequestMethod.POST)
	public ModelAndView salvarDesmama(@Valid Desmama desmama, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return adicionarDesmama(desmama);
		}

		gestaoClient.salvarDesmama(desmama);
		attributes.addFlashAttribute("mensagem", "Desmama salva com sucesso!");
		return new ModelAndView("redirect:adicionar/desmama");
	}

	// ------------------------------ PRIMEIRA CRIA
	// --------------------------------------------------------
	@RequestMapping("/primeiraCria")
	public ModelAndView gestaoPrimeiraCria(@RequestParam(defaultValue = "SIMB01") String descricao) {

		GestaoClient gc = new GestaoClient();

		ModelAndView mv = new ModelAndView("gestao/primeiraCria");
		mv.addObject("idadePrimeiraCria", gc.idadePrimeiraCria(descricao));

		return mv;
	}

	// ------------------------------ DESFRUTE
	// --------------------------------------------------------

	@RequestMapping("/desfrute")
	public ModelAndView gestaoDesfrute(@RequestParam(defaultValue = "31-12-2014") String dataInicial,
			@RequestParam(defaultValue = "01-01-2016") String dataFinal) {

		GestaoClient gc = new GestaoClient();

		ModelAndView mv = new ModelAndView("gestao/desfrute");
		mv.addObject("desfrute", gc.desfrute(dataInicial, dataFinal));
		mv.addObject("dataFinal", dataFinal);
		return mv;
	}

	// ------------------------------ TAREFAS
	// --------------------------------------------------------
	@RequestMapping("/tarefas")
	public ModelAndView gestaoTarefas(RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("gestao/tarefas");
		return mv;
	}

	@RequestMapping(value = "/adicionar/tarefa", method = RequestMethod.POST)
	public String adicionarTarefa(Tarefa tarefa, RedirectAttributes attributes) {

		if (tarefa.getTipoTarefa().toString().equals("DiagnosticoDeGestacao")) {

			tarefa.setStatusDaTarefa(true);
			tarefa.setDataConclusao(new Date());
			tarefaClient.alterar(tarefa);
			return "redirect:gestacao";
		} else if (tarefa.getTipoTarefa().toString().equals("Inseminação")) {

			tarefa.setStatusDaTarefa(true);
			tarefa.setDataConclusao(new Date());
			tarefaClient.alterar(tarefa);
			return "redirect:inseminacao";
		} else if (tarefa.getTipoTarefa().toString().equals("Parto")) {

			tarefa.setStatusDaTarefa(true);
			tarefa.setDataConclusao(new Date());
			tarefaClient.alterar(tarefa);
			return "redirect:parto";
		} else if (tarefa.getTipoTarefa().toString().equals("CadastrarBovino")) {

			tarefa.setStatusDaTarefa(true);
			tarefa.setDataConclusao(new Date());
			tarefaClient.alterar(tarefa);
			return "redirect:criarBovino";

		}

		return "redirect:adicionar/tarefa";
	}

	// ------------------------------ GESTAÇÃO
	// --------------------------------------------------------

	@RequestMapping("/adicionar/gestacao")
	public ModelAndView adicionarGestacao(DiagnosticoGestacao diagnosticoGestacao) {
		Calendar cal = Calendar.getInstance();
		diagnosticoGestacao.setDataResultado(cal.getTime());
		ModelAndView mv = new ModelAndView("gestao/salvardiagnosticogestacao");

		return mv;
	}

	@RequestMapping(value = "/salvarDiagnostico", method = RequestMethod.POST)
	public ModelAndView salvarDiagnostico(@Valid DiagnosticoGestacao diagnosticoGestacao,
			RedirectAttributes attributes) {

		gestaoClient.salvarDiagnosticoGestacao(diagnosticoGestacao);
		attributes.addFlashAttribute("mensagem", "Diagnostico salvo com sucesso!");
		return new ModelAndView("redirect:adicionar/gestacao");
	}

	// ------------------------------ Listar dados Tarefas - Gestao
	// --------------------------------------------------------

	@RequestMapping("/finalizadas")
	public ModelAndView escolhaTarefaGestao() {

		ModelAndView mv = new ModelAndView("gestao/menuTarefaGestao");

		return mv;
	}

	@RequestMapping(value = "/listar/inseminacao", method = RequestMethod.GET)
	public ModelAndView listaInseminacao(@RequestParam(defaultValue = "todos") String descricao, String tipoBusca) {
		List<Inseminacao> inseminacoes = gestaoClient.listarInseminacao(descricao, tipoBusca);
		for(int i=0;i<inseminacoes.size();i++) {
			if(inseminacoes.get(i).getMatriz()==null) {
				inseminacoes.get(i).setMatriz("-1");
			}
			if(inseminacoes.get(i).getPrevisaoParto()==null) {
				inseminacoes.get(i).setPrevisaoParto(new Date());
			}
		}
		ModelAndView mv = new ModelAndView("gestao/listarInseminacao");
		mv.addObject("inseminacoes", todasInseminacao(inseminacoes));

		return mv;
	}

	@RequestMapping(value = "/listar/gestacao", method = RequestMethod.GET)
	public ModelAndView listaDiagnosticoGestacao(@RequestParam(defaultValue = "todos") String descricao,
			String tipoBusca) {
		String gest="[0,0]";
		String resultado;
		int c=0;
		int v=0;
		
		List<DiagnosticoGestacao> dg = gestaoClient.listarDG(descricao, tipoBusca);
		if(dg!=null) {
			for(int i=0;i<dg.size();i++) {
				resultado = String.valueOf(dg.get(i).getResultado());
				if("Cheia".equals(resultado)) {
					c++;
				}else {
					v++;
				}
			}
			gest = "["+c+","+v+"]";
		}
		
		ModelAndView mv = new ModelAndView("gestao/listarGestacao");
		mv.addObject("diagGest", todosDG(dg));
		mv.addObject("gestacao", gest);
		return mv;
	}

	@RequestMapping(value = "/listar/parto", method = RequestMethod.GET)
	public ModelAndView listaParto(@RequestParam(defaultValue = "todos") String descricao, String tipoBusca) {
		List<Parto> partos = gestaoClient.listarParto(descricao, tipoBusca);
		String parto="[0,0]";
		String resultado;
		int v=0;
		int m=0;
		if(partos!=null) {
			for(int i=0;i<partos.size();i++) {
				resultado = String.valueOf(partos.get(i).getStatus());
				if("Vivo".equals(resultado)) {
					v++;
				}else {
					m++;
				}
			}
			parto = "["+v+","+m+"]";
		}
		
		
		
		ModelAndView mv = new ModelAndView("gestao/listarParto");
		mv.addObject("partos", todosPartos(partos));
		mv.addObject("parto", parto);
		return mv;
	}

	@RequestMapping(value = "/listar/morte", method = RequestMethod.GET)
	public ModelAndView listaMorte(@RequestParam(defaultValue = "todos") String descricao, String tipoBusca) {
		List<Morte> morte = gestaoClient.listarMorte(descricao, tipoBusca);

		ModelAndView mv = new ModelAndView("gestao/listarMorte");

		mv.addObject("mortos", todosMortos(morte));

		return mv;
	}

	@RequestMapping(value = "/listar/desmama", method = RequestMethod.GET)
	public ModelAndView listaDesmama(@RequestParam(defaultValue = "todos") String descricao, String tipoBusca) {
		List<Desmama> desmama = gestaoClient.listarDesmam(descricao, tipoBusca);
		ModelAndView mv = new ModelAndView("gestao/listarDesmama");
		mv.addObject("desmamas", todasDesmama(desmama));

		return mv;
	}

	@RequestMapping(value = "/listar/vendido", method = RequestMethod.GET)
	public ModelAndView listaVendido(@RequestParam(defaultValue = "todos") String descricao, String tipoBusca) {
		List<Venda> venda = gestaoClient.listarVenda(descricao, tipoBusca);
		ModelAndView mv = new ModelAndView("gestao/listarVendidos");
		mv.addObject("vendidos", todosVendidos(venda));
		return mv;
	}

	@RequestMapping(value = "/listar/abatido", method = RequestMethod.GET)
	public ModelAndView listaAbatido(@RequestParam(defaultValue = "todos") String descricao, String tipoBusca) {
		List<Abatido> abatidos = gestaoClient.listarAbatido(descricao, tipoBusca);
		ModelAndView mv = new ModelAndView("gestao/listarAbatido");
		mv.addObject("abatidos", todosAbatidos(abatidos));
		return mv;
	}

	@RequestMapping(value = "/listar/pesagem", method = RequestMethod.GET)
	public ModelAndView listaPesagem(@RequestParam(defaultValue = "todos") String descricao) {
		Double ganho = 0d;
		List<Bovino> bovinos = bovinoClient.listarPorNome(descricao, "nome");
		organizaLista(bovinos.get(0));
		List<Pesagem> pesos = new ArrayList<>();
		String peso = "[";
		if (bovinos != null) {
			Pesagem p;
			
			if (bovinos.get(0).getPeso().size() > 1) {
				for (int i = 0; i < bovinos.get(0).getPeso().size()-1; i++) {
					ganho = bovinos.get(0).getPeso().get(i+1).getPeso()
							- bovinos.get(0).getPeso().get(i).getPeso();
					
					if(i==0) {
						p = new Pesagem();
						p.setGanho(bovinos.get(0).getPeso().get(i).getPeso());
						p.setPeso(Double.valueOf(String.valueOf(i+1)));
						pesos.add(p);
						peso = peso +bovinos.get(0).getPeso().get(i).getPeso();
					}
					p = new Pesagem();
					p.setGanho(ganho);
					p.setPeso(Double.valueOf(String.valueOf(i+2)));
					pesos.add(p);
					peso = peso +","+bovinos.get(0).getPeso().get(i+1).getPeso();
				}
				peso = peso +"]";
				
			}else {
				peso = "]]]]]";
			}
		}
		
		ModelAndView mv = new ModelAndView("gestao/listarPesagem");
		mv.addObject("bovinopeso", bovinos.get(0));
		mv.addObject("ganhoPeso", pesos);
		mv.addObject("pesos", peso);
		
		return mv;
	}

	// ------------------------------ PARTO
	// --------------------------------------------------------
	@RequestMapping("/adicionar/parto")
	public ModelAndView adicionarParto(Parto parto) {
		Calendar cal = Calendar.getInstance();
		parto.setDataParto(cal.getTime());
		ModelAndView mv = new ModelAndView("gestao/SalvarParto");

		return mv;
	}

	@RequestMapping(value = "/salvarParto", method = RequestMethod.POST)
	public String salvarParto(@Validated Parto p, RedirectAttributes attributes) {
		
		gestaoClient.salvarParto(p);
		attributes.addFlashAttribute("mensagem", "Parto salvo com sucesso!");
		return "redirect:adicionar/criarBovino";
	}

	// ------------------------------ CRIAR NOVO BOVINO
	// --------------------------------------------------------

	@RequestMapping("/adicionar/criarBovino")
	public ModelAndView adicionarBovino(Bovino bovino) {

		ModelAndView mv = new ModelAndView("gestao/CriarBovino");

		return mv;
	}

	@RequestMapping(value = "/novoBovino", method = RequestMethod.POST)
	public ModelAndView criarBovino(@Validated Bovino b, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return adicionarBovino(b);
		}

		gestaoClient.salvarBovino(b);
		attributes.addFlashAttribute("mensagem", "Bovino salvo com sucesso!");

		return new ModelAndView("redirect:adicionar/pesagem");
	}

	// ------------------------------ MORTE
	// --------------------------------------------------------
	@RequestMapping("/adicionar/morte")
	public ModelAndView adicionarMorte(Morte morte) {
		ModelAndView mv = new ModelAndView("gestao/Morte");
		return mv;
	}

	@RequestMapping(value = "/morte", method = RequestMethod.POST)
	public ModelAndView salvarMorte(@Validated Morte morte, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return adicionarMorte(morte);
		}

		gestaoClient.salvarMorte(morte);
		attributes.addFlashAttribute("mensagem", "Morte salva com sucesso!");

		return new ModelAndView("redirect:adicionar/morte");
	}

	// ------------------------------ VENDIDO
	// --------------------------------------------------------
	@RequestMapping("/adicionar/vendido")
	public ModelAndView adicionarVendido(Venda venda) {
		ModelAndView mv = new ModelAndView("gestao/Vendido");
		return mv;
	}

	@RequestMapping(value = "/vendido", method = RequestMethod.POST)
	public ModelAndView salvarVendido(@Validated Venda venda, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {

			return adicionarVendido(venda);
		}
		gestaoClient.salvarVendido(venda);
		attributes.addFlashAttribute("mensagem", "Venda salva com sucesso!");

		return new ModelAndView("redirect:adicionar/vendido");
	}

	// ------------------------------ ABATE
	// --------------------------------------------------------
	@RequestMapping("/adicionar/abatido")
	public ModelAndView adicionarAbatido(Abatido abatido) {
		ModelAndView mv = new ModelAndView("gestao/Abatido");
		return mv;
	}

	@RequestMapping(value = "/abatido", method = RequestMethod.POST)
	public ModelAndView salvarAbatido(@Validated Abatido abatido, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {

			return adicionarAbatido(abatido);
		}
		
		gestaoClient.salvarAbatido(abatido);
		attributes.addFlashAttribute("mensagem", "Bovino abatido salvo com sucesso!");

		return new ModelAndView("redirect:adicionar/abatido");
	}

	// ------------------------------ PESAGEM
	// --------------------------------------------------------

	@RequestMapping("/adicionar/pesagem")
	public ModelAndView adicionarPesagem(Peso peso) {
		ModelAndView mv = new ModelAndView("gestao/SalvarPesagem");
		return mv;
	}

	@RequestMapping(value = "/pesagem", method = RequestMethod.POST)
	public ModelAndView salvarPesagem(@Validated Peso peso, BindingResult result, Integer id,
			RedirectAttributes attributes) {

		if (result.hasErrors()) {

			return adicionarPesagem(peso);
		}

		gestaoClient.salvarPesagem(peso, id);
		attributes.addFlashAttribute("mensagem", "Peso salvo com sucesso!");

		return new ModelAndView("redirect:adicionar/ecc");
	}

	// ------------------------------ ECC
	// --------------------------------------------------------

	@RequestMapping("/adicionar/ecc")
	public ModelAndView adicionarEcc() {
		ModelAndView mv = new ModelAndView("gestao/SalvarEcc");
		return mv;
	}

	@RequestMapping(value = "/ecc", method = RequestMethod.POST)
	public String salvarEcc(Ecc ecc, Integer id, RedirectAttributes attributes) {
		

		gestaoClient.salvarEcc(ecc, id);
		attributes.addFlashAttribute("mensagem", "Ecc salvo com sucesso!");

		return "redirect:adicionar/criarBovino";
	}

	// ------------------------------ MATRIZ
	// --------------------------------------------------------

	@RequestMapping("/adicionar/matriz")
	public ModelAndView adicionarMatriz() {
		ModelAndView mv = new ModelAndView("gestao/matriz");
		return mv;
	}

	@RequestMapping(value = "/matriz", method = RequestMethod.POST)
	public String salvarMatriz(Integer id, RedirectAttributes attributes) {
		FichaMatriz fichaMatriz = new FichaMatriz();
		fichaMatriz.setDataInclusao(new Date());

		gestaoClient.salvarMatriz(fichaMatriz, id);
		attributes.addFlashAttribute("mensagem", "Matriz salva com sucesso!");
		return "redirect:adicionar/matriz";
	}

	// ------------------------------ TOURO
	// --------------------------------------------------------

	@RequestMapping("/adicionar/touro")
	public ModelAndView adicionartouro() {
		ModelAndView mv = new ModelAndView("gestao/touro");
		return mv;
	}

	@RequestMapping(value = "/touro", method = RequestMethod.POST)
	public String salvarTouro(Touro touro, RedirectAttributes attributes) {
		

		gestaoClient.salvarTouro(touro);
		attributes.addFlashAttribute("mensagem", "Touro salva com sucesso!");
		return "redirect:adicionar/touro";
	}

	// ------------------------------ INSEMINAÇÃO
	// --------------------------------------------------------

	@RequestMapping("/adicionar/inseminacao")
	public ModelAndView adicionarInseminacao(Inseminacao i) {
		Calendar cal = Calendar.getInstance();
		i.setDataDaInseminacao(cal.getTime());
		cal.add(Calendar.DAY_OF_MONTH, 285);
		i.setPrevisaoParto(cal.getTime());

		ModelAndView mv = new ModelAndView("gestao/Inseminacao");
		return mv;
	}

	@RequestMapping(value = "/inseminacao", method = RequestMethod.POST)
	public ModelAndView salvarInseminacao(@Validated Inseminacao i, BindingResult result,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return adicionarInseminacao(i);
		}

		gestaoClient.salvarInseminacao(i);
		attributes.addFlashAttribute("mensagem", "Inseminação salva com sucesso!");
		return new ModelAndView("redirect:adicionar/inseminacao");
	}
//	 @RequestMapping(value = "/grafico", method = RequestMethod.GET)
//	 public ModelAndView grafico() {
//	String a = "['40','80','140','200','250','330']";
//		 ModelAndView mv = new ModelAndView("gestao/testeGrafico");
//		 mv.addObject("peso",a);
//	 return mv;
//	 }

	// @RequestMapping(value = "/peso/inserir", method = RequestMethod.GET)
	// public void buscarBovinoPorMae() {
	// Peso peso;
	// for (int i = 1; i <= 25; i++) {
	// peso = new Peso();
	//
	// SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
	// Random gerador = new Random();
	//
	// Date data;
	// try {
	// data = formato.parse("01-06-2015");
	// peso.setDataPesagem(data);
	//
	// int peso1 = gerador.nextInt((460 - 440) + 1) + 440;
	//
	// peso.setPeso(Double.valueOf(String.valueOf(peso1)));
	// gestaoClient.salvarPesagem(peso, i);
	// } catch (ParseException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	//
	//
	//
	// }
	//
	// }

	// ------------------------------ MODELATRRIBUTE
	// --------------------------------------------------------
	@ModelAttribute("todasMatriz")
	public List<Bovino> todosMatriz() {
		List<Bovino> matriz = gestaoClient.listarMatriz();

		return matriz;
	}

	@ModelAttribute("todosTouro")
	public List<Bovino> todosTouro() {
		List<Bovino> touro = gestaoClient.listarTouro();
		return touro;
	}

	@ModelAttribute("todosFemea")
	public List<Bovino> todasFemea() {
		List<Bovino> femea = gestaoClient.listarFemea();
		return femea;
	}

	@ModelAttribute("bovinos")
	public List<Bovino> todosBovinos() {
		List<Bovino> bovinos = bovinoClient.listarPorNome("todos", "nome");

		return bovinos;
	}

	public List<Inseminacao> todasInseminacao(List<Inseminacao> inseminacoes) {

		Bovino bovino;
		for (int i = 0; i < inseminacoes.size(); i++) {
			bovino = gestaoClient.buscaNomeMatriz(Long.parseLong(inseminacoes.get(i).getMatriz()));
			inseminacoes.get(i).setMatriz(bovino.getNomeBovino());
		}

		return inseminacoes;
	}

	public List<DiagnosticoGestacao> todosDG(List<DiagnosticoGestacao> dg) {

		Bovino bovino;
		for (int i = 0; i < dg.size(); i++) {
			bovino = gestaoClient.buscaNomeMatriz(Long.parseLong(dg.get(i).getIdFichaMatriz()));
			dg.get(i).setIdFichaMatriz(bovino.getNomeBovino());
		}

		return dg;
	}

	public List<Parto> todosPartos(List<Parto> partos) {

		Bovino bovino;
		for (int i = 0; i < partos.size(); i++) {
			bovino = gestaoClient.buscaNomeMatriz(Long.parseLong(partos.get(i).getIdFichaMatriz()));
			partos.get(i).setIdFichaMatriz(bovino.getNomeBovino());
		}

		return partos;
	}

	public List<Morte> todosMortos(List<Morte> morte) {

		Bovino bovino;
		for (int i = 0; i < morte.size(); i++) {
			bovino = bovinoClient.listarUm(Long.parseLong(morte.get(i).getIdBovino()));
			morte.get(i).setIdBovino(bovino.getNomeBovino());
		}

		return morte;
	}

	public List<Desmama> todasDesmama(List<Desmama> desmama) {

		Bovino bovino;
		for (int i = 0; i < desmama.size(); i++) {
			bovino = bovinoClient.listarUm(Long.parseLong(desmama.get(i).getIdBovino()));
			desmama.get(i).setIdBovino(bovino.getNomeBovino());
		}
		for (int i = 0; i < desmama.size(); i++) {
			bovino = bovinoClient.listarUm(Long.parseLong(desmama.get(i).getIdFichaMatriz()));
			desmama.get(i).setIdFichaMatriz(bovino.getNomeBovino());
		}

		return desmama;
	}

	public List<Venda> todosVendidos(List<Venda> venda) {

		Bovino bovino;
		for (int i = 0; i < venda.size(); i++) {
			bovino = bovinoClient.listarUm(Long.parseLong(venda.get(i).getIdBovino()));
			venda.get(i).setIdBovino(bovino.getNomeBovino());

		}

		return venda;
	}

	public List<Abatido> todosAbatidos(List<Abatido> abatido) {

		Bovino bovino;
		for (int i = 0; i < abatido.size(); i++) {
			bovino = bovinoClient.listarUm(Long.parseLong(abatido.get(i).getIdBovino()));
			abatido.get(i).setIdBovino(bovino.getNomeBovino());

		}

		return abatido;
	}
	public Bovino organizaLista(Bovino bovino){
		Peso peso = new Peso();
		if(bovino!=null) {
			if(bovino.getPeso().size()>1) {
				for(int j=0;j<bovino.getPeso().size()-1;j++) {
				for(int i=0;i<bovino.getPeso().size()-1;i++) {
					
					if(bovino.getPeso().get(i).getDataPesagem().getTime() > bovino.getPeso().get(i+1).getDataPesagem().getTime()) {
						
						peso.setDataPesagem(bovino.getPeso().get(i).getDataPesagem());
						peso.setIdPeso(bovino.getPeso().get(i).getIdPeso());
						peso.setPeso(bovino.getPeso().get(i).getPeso());
						peso.setStatus(bovino.getPeso().get(i).getStatus());
						
						bovino.getPeso().get(i).setDataPesagem( bovino.getPeso().get(i+1).getDataPesagem());
						bovino.getPeso().get(i).setIdPeso(bovino.getPeso().get(i+1).getIdPeso());
						bovino.getPeso().get(i).setPeso(bovino.getPeso().get(i+1).getPeso());
						bovino.getPeso().get(i).setStatus(bovino.getPeso().get(i+1).getStatus());
						
						
						
						
						bovino.getPeso().get(i+1).setDataPesagem(peso.getDataPesagem());
						bovino.getPeso().get(i+1).setIdPeso(peso.getIdPeso());
						bovino.getPeso().get(i+1).setPeso((peso.getPeso()));
						bovino.getPeso().get(i+1).setStatus((peso.getStatus()));
						
					}
					
				}
				}
				
			}
		}

		return bovino;
	}

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

		for (int i = 0; i < 9; i++) {
			Ecc ecc = new Ecc();
			ecc.setIdECC(0L);
			ecc.setEscore(i + 1);
			eccs.add(ecc);
		}
		return eccs;
	}

	@ModelAttribute("todosFuncionarios")
	public List<Funcionario> todosFuncionarios() {
		List<Funcionario> funcionarios = funcionarioClient.listarFuncionarios();
		return funcionarios;
	}

	@ModelAttribute("trueAndFalse")
	public List<TrueAndFalse> trueAndFalse() {
		List<TrueAndFalse> tafs = gestaoClient.listarTrueAndFalse();
		return tafs;
	}

	@ModelAttribute("todosTiposTarefa")
	public List<TipoTarefaEnum> todosTiposTarefa() {
		return Arrays.asList(TipoTarefaEnum.values());
	}

}
