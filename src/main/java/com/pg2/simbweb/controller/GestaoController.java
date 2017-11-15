package com.pg2.simbweb.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
	public ModelAndView gestaoDesfrute(@RequestParam(defaultValue = "01-01-01") String data) {
		
		GestaoClient gc = new GestaoClient();

		ModelAndView mv = new ModelAndView("gestao/desfrute");
		mv.addObject("desfrute", gc.desfrute(data));

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
	
	
//	@ModelAttribute("inseminacoes")
//	public List<Inseminacao> todasInseminacao() {
//		List<Inseminacao> inseminacoes = gestaoClient.listarInseminacao();
//		Bovino bovino;
//		for (int i = 0; i < inseminacoes.size(); i++) {
//			bovino = gestaoClient.buscaNomeMatriz(Long.parseLong(inseminacoes.get(i).getMatriz()));
//			inseminacoes.get(i).setMatriz(bovino.getNomeBovino());
//		}
//
//		return inseminacoes;
//	}
	
	
	
	@RequestMapping(value ="/listar/inseminacao", method = RequestMethod.GET)
	public ModelAndView listaInseminacao(@RequestParam(defaultValue="todos") String descricao, String tipoBusca) {
		List<Inseminacao> inseminacoes = gestaoClient.listarInseminacao(descricao,tipoBusca);
		
		ModelAndView mv = new ModelAndView("gestao/listarInseminacao");
		mv.addObject("inseminacoes", todasInseminacao(inseminacoes));

		return mv;
	}

	@RequestMapping("/listar/gestacao")
	public ModelAndView listaDiagnosticoGestacao() {

		ModelAndView mv = new ModelAndView("gestao/listarGestacao");

		return mv;
	}

	@RequestMapping("/listar/parto")
	public ModelAndView listaParto() {

		ModelAndView mv = new ModelAndView("gestao/listarParto");

		return mv;
	}

	@RequestMapping("/listar/morte")
	public ModelAndView listaMorte() {

		ModelAndView mv = new ModelAndView("gestao/listarMorte");

		return mv;
	}

	@RequestMapping("/listar/desmama")
	public ModelAndView listaDesmama() {

		ModelAndView mv = new ModelAndView("gestao/listarDesmama");

		return mv;
	}
	@RequestMapping("/listar/vendido")
	public ModelAndView listaVendido() {
		
		ModelAndView mv = new ModelAndView("gestao/listarVendidos");
		
		return mv;
	}
	@RequestMapping("/listar/abatido")
	public ModelAndView listaAbatido() {

		ModelAndView mv = new ModelAndView("gestao/listarAbatido");

		return mv;
	}
	
	@RequestMapping(value ="/listar/pesagem", method = RequestMethod.GET)
	public ModelAndView listaPesagem(@RequestParam(defaultValue="todos") String descricao) {
		Double ganho=0d;
		List<Bovino> bovinos = bovinoClient.listarPorNome(descricao,"nome");
		if(bovinos!=null) {
			
			if(bovinos.get(0).getPeso().size()>1) {
				ganho = bovinos.get(0).getPeso().get(bovinos.get(0).getPeso().size()-1).getPeso()-bovinos.get(0).getPeso().get(0).getPeso();
			}
		}
		  
		
		ModelAndView mv = new ModelAndView("gestao/listarPesagem");
		mv.addObject("bovinopeso",bovinos.get(0));
		mv.addObject("ganhoPeso",ganho);

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
		System.out.println(p.getDataParto());
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

		return new ModelAndView ("redirect:adicionar/vendido");
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
		System.out.println(abatido.toString());
		gestaoClient.salvarAbatido(abatido);
		attributes.addFlashAttribute("mensagem", "Bovino abatido salvo com sucesso!");

		return new ModelAndView ("redirect:adicionar/abatido");
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
		System.out.println("escore: " + ecc.getEscore() + " , " + id);

		gestaoClient.salvarEcc(ecc, id);
		attributes.addFlashAttribute("mensagem", "Ecc salvo com sucesso!");

		return "redirect:adicionar/ecc";
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
		System.out
				.println("TEST :" + touro.getIdBovino() + " , " + touro.getStatus() + " , " + touro.getIdFichaTouro());

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
		List<Bovino> bovinos = bovinoClient.listarPorNome("todos","nome");

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

	@ModelAttribute("diagGest")
	public List<DiagnosticoGestacao> todosDG() {
		List<DiagnosticoGestacao> dg = gestaoClient.listarDG();
		Bovino bovino;
		for (int i = 0; i < dg.size(); i++) {
			bovino = gestaoClient.buscaNomeMatriz(Long.parseLong(dg.get(i).getIdFichaMatriz()));
			dg.get(i).setIdFichaMatriz(bovino.getNomeBovino());
		}

		return dg;
	}

	@ModelAttribute("partos")
	public List<Parto> todosPartos() {
		List<Parto> partos = gestaoClient.listarParto();
		Bovino bovino;
		for (int i = 0; i < partos.size(); i++) {
			bovino = gestaoClient.buscaNomeMatriz(Long.parseLong(partos.get(i).getIdFichaMatriz()));
			partos.get(i).setIdFichaMatriz(bovino.getNomeBovino());
		}

		return partos;
	}

	@ModelAttribute("mortos")
	public List<Morte> todosMortos() {
		List<Morte> morte = gestaoClient.listarMorte();
		Bovino bovino;
		for (int i = 0; i < morte.size(); i++) {
			bovino = bovinoClient.listarUm(Long.parseLong(morte.get(i).getIdBovino()));
			morte.get(i).setIdBovino(bovino.getNomeBovino());
		}

		return morte;
	}

	@ModelAttribute("desmamas")
	public List<Desmama> todasDesmama() {
		List<Desmama> morte = gestaoClient.listarDesmama();
		Bovino bovino;
		for (int i = 0; i < morte.size(); i++) {
			bovino = bovinoClient.listarUm(Long.parseLong(morte.get(i).getIdBovino()));
			morte.get(i).setIdBovino(bovino.getNomeBovino());
			morte.get(i).setIdFichaMatriz(bovino.getMae());
		}

		return morte;
	}
	@ModelAttribute("vendidos")
	public List<Venda> todosVendidos() {
		List<Venda> venda = gestaoClient.listarVenda();
		Bovino bovino;
		for (int i = 0; i < venda.size(); i++) {
			bovino = bovinoClient.listarUm(Long.parseLong(venda.get(i).getIdBovino()));
			venda.get(i).setIdBovino(bovino.getNomeBovino());
			
		}

		return venda;
	}
	
	@ModelAttribute("abatidos")
	public List<Abatido> todosAbatidos() {
		List<Abatido> abatido = gestaoClient.listarAbatido();
		Bovino bovino;
		for (int i = 0; i < abatido.size(); i++) {
			bovino = bovinoClient.listarUm(Long.parseLong(abatido.get(i).getIdBovino()));
			abatido.get(i).setIdBovino(bovino.getNomeBovino());
			
		}

		return abatido;
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
