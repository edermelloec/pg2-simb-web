
package com.pg2.simbweb.client;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.pg2.simbweb.domain.bovino.Bovino;
import com.pg2.simbweb.domain.bovino.Ecc;
import com.pg2.simbweb.domain.bovino.Gestao;
import com.pg2.simbweb.domain.bovino.Peso;
import com.pg2.simbweb.domain.matriz.DiagnosticoGestacao;
import com.pg2.simbweb.domain.matriz.FichaMatriz;
import com.pg2.simbweb.domain.matriz.Inseminacao;
import com.pg2.simbweb.domain.matriz.Parto;
import com.pg2.simbweb.domain.matriz.TrueAndFalse;
import com.pg2.simbweb.gestao.Abatido;
import com.pg2.simbweb.gestao.Desmama;
import com.pg2.simbweb.gestao.Morte;
import com.pg2.simbweb.gestao.Touro;
import com.pg2.simbweb.gestao.Venda;

@Component
public class GestaoClient {

	private RestTemplate restTemplate = new RestTemplate();

	private String URI_ = "http://127.0.0.1:8080";
	private String URI_BASE = URI_ + "/gestao";
	private String URI_FERTILIDADE = URI_BASE + "/prenhez";
	private String URI_NATALIDADE = URI_BASE + "/natalidade";
	private String URI_MORTALIDADE = URI_BASE + "/mortalidade";
	private String URI_PARTO = URI_ + "/parto";
	private String URI_INSEMINACAO = URI_ + "/inseminacao";
	private String URI_RESULTADO = URI_ + "/resultado";

	public GestaoClient() {
	}

	// ------------------------------ GETs
	// --------------------------------------------------------
	public List<Gestao> natalidadeTotal() {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_NATALIDADE + "/todos")).build();

		ResponseEntity<Gestao[]> response = restTemplate.exchange(request, Gestao[].class);

		return Arrays.asList(response.getBody());

	}

	public List<Gestao> natalidadeMult() {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_NATALIDADE + "/mult")).build();

		ResponseEntity<Gestao[]> response = restTemplate.exchange(request, Gestao[].class);

		return Arrays.asList(response.getBody());

	}

	public List<Gestao> natalidadeNovi() {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_NATALIDADE + "/novilha")).build();

		ResponseEntity<Gestao[]> response = restTemplate.exchange(request, Gestao[].class);

		return Arrays.asList(response.getBody());

	}

	public List<Gestao> natalidadePrimi() {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_NATALIDADE + "/primi")).build();

		ResponseEntity<Gestao[]> response = restTemplate.exchange(request, Gestao[].class);

		return Arrays.asList(response.getBody());

	}

	public List<Gestao> fertilidadeTotal() {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_FERTILIDADE + "/todos")).build();

		ResponseEntity<Gestao[]> response = restTemplate.exchange(request, Gestao[].class);

		return Arrays.asList(response.getBody());

	}

	public List<Gestao> fertilidadeNovi() {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_FERTILIDADE + "/novilha")).build();

		ResponseEntity<Gestao[]> response = restTemplate.exchange(request, Gestao[].class);

		return Arrays.asList(response.getBody());

	}

	public List<Gestao> fertilidadePrimi() {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_FERTILIDADE + "/primi")).build();

		ResponseEntity<Gestao[]> response = restTemplate.exchange(request, Gestao[].class);

		return Arrays.asList(response.getBody());

	}

	public List<Gestao> fertilidadeMult() {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_FERTILIDADE + "/mult")).build();

		ResponseEntity<Gestao[]> response = restTemplate.exchange(request, Gestao[].class);

		return Arrays.asList(response.getBody());

	}

	public List<Gestao> mortalidadeTodos() {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_MORTALIDADE + "/todos")).build();

		ResponseEntity<Gestao[]> response = restTemplate.exchange(request, Gestao[].class);

		return Arrays.asList(response.getBody());

	}

	public List<Gestao> mortalidadePreParto() {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_MORTALIDADE + "/preparto")).build();

		ResponseEntity<Gestao[]> response = restTemplate.exchange(request, Gestao[].class);

		return Arrays.asList(response.getBody());

	}

	public List<Gestao> mortalidadeAdulto() {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_MORTALIDADE + "/adulto")).build();

		ResponseEntity<Gestao[]> response = restTemplate.exchange(request, Gestao[].class);

		return Arrays.asList(response.getBody());

	}

	public List<Gestao> mortalidadeJovens() {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_MORTALIDADE + "/jovens")).build();

		ResponseEntity<Gestao[]> response = restTemplate.exchange(request, Gestao[].class);

		return Arrays.asList(response.getBody());

	}

	public List<Gestao> mortalidadeDesmama() {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_MORTALIDADE + "/desmama")).build();

		ResponseEntity<Gestao[]> response = restTemplate.exchange(request, Gestao[].class);

		return Arrays.asList(response.getBody());

	}

	public List<Gestao> desmama() {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_BASE + "/desmama")).build();

		ResponseEntity<Gestao[]> response = restTemplate.exchange(request, Gestao[].class);

		return Arrays.asList(response.getBody());

	}

	public List<Gestao> abate() {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_BASE + "/abate")).build();

		ResponseEntity<Gestao[]> response = restTemplate.exchange(request, Gestao[].class);

		return Arrays.asList(response.getBody());

	}

	public List<Gestao> desfrute(String dataInicial,String dataFinal) {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_BASE + "/desfrute/" + dataInicial+"/"+dataFinal)).build();

		ResponseEntity<Gestao[]> response = restTemplate.exchange(request, Gestao[].class);

		return Arrays.asList(response.getBody());

	}

	public List<Gestao> touroVaca() {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_BASE + "/touroVaca")).build();

		ResponseEntity<Gestao[]> response = restTemplate.exchange(request, Gestao[].class);

		return Arrays.asList(response.getBody());

	}

	public List<Gestao> idadePrimeiraCria(String nome) {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_BASE + "/idade/" + nome)).build();
		ResponseEntity<Gestao[]> response = restTemplate.exchange(request, Gestao[].class);
		return Arrays.asList(response.getBody());

	}

	public List<Gestao> idadeDesmame(String nome) {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_BASE + "/desmama/" + nome)).build();
		ResponseEntity<Gestao[]> response = restTemplate.exchange(request, Gestao[].class);
		return Arrays.asList(response.getBody());

	}

	public List<Bovino> listarMatriz() {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_BASE + "/listar/matriz")).build();
		ResponseEntity<Bovino[]> response = restTemplate.exchange(request, Bovino[].class);
		return Arrays.asList(response.getBody());

	}

	public List<Bovino> listarTouro() {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_BASE + "/listar/touro")).build();
		ResponseEntity<Bovino[]> response = restTemplate.exchange(request, Bovino[].class);
		return Arrays.asList(response.getBody());

	}

	public List<Bovino> listarFemea() {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_BASE + "/listar/femea")).build();
		ResponseEntity<Bovino[]> response = restTemplate.exchange(request, Bovino[].class);
		return Arrays.asList(response.getBody());

	}

	public Bovino buscaNomeMatriz(Long codigo) {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_BASE + "/nome/matriz/" + codigo)).build();
		ResponseEntity<Bovino> response = restTemplate.exchange(request, Bovino.class);
		return response.getBody();

	}

	public List<Inseminacao> listarInseminacao(String busca,String tipoBusca) {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_INSEMINACAO+"/"+busca+"/"+tipoBusca)).build();

		ResponseEntity<Inseminacao[]> response = restTemplate.exchange(request, Inseminacao[].class);

		return Arrays.asList(response.getBody());

	}
	public List<DiagnosticoGestacao> listarDG(String busca,String tipoBusca) {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_RESULTADO+"/"+busca+"/"+tipoBusca)).build();

		ResponseEntity<DiagnosticoGestacao[]> response = restTemplate.exchange(request, DiagnosticoGestacao[].class);

		return Arrays.asList(response.getBody());

	}
	public List<Parto> listarParto(String busca,String tipoBusca) {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_PARTO+"/"+busca+"/"+tipoBusca)).build();

		ResponseEntity<Parto[]> response = restTemplate.exchange(request, Parto[].class);

		return Arrays.asList(response.getBody());

	}
	
	public List<Morte> listarMorte(String busca,String tipoBusca) {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_ + "/morto"+"/"+busca+"/"+tipoBusca)).build();

		ResponseEntity<Morte[]> response = restTemplate.exchange(request, Morte[].class);

		return Arrays.asList(response.getBody());

	}
	public List<Desmama> listarDesmam(String busca,String tipoBusca) {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_ + "/desmama"+"/"+busca+"/"+tipoBusca)).build();

		ResponseEntity<Desmama[]> response = restTemplate.exchange(request, Desmama[].class);

		return Arrays.asList(response.getBody());

	}
	
	public List<Venda> listarVenda(String busca,String tipoBusca) {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_+"/venda/listar/vendidos"+"/"+busca+"/"+tipoBusca)).build();

		ResponseEntity<Venda[]> response = restTemplate.exchange(request, Venda[].class);

		return Arrays.asList(response.getBody());

	}

	public List<Abatido> listarAbatido(String busca,String tipoBusca) {

		RequestEntity<Void> request = RequestEntity.get(URI.create(URI_+"/abatido"+"/"+busca+"/"+tipoBusca)).build();

		ResponseEntity<Abatido[]> response = restTemplate.exchange(request, Abatido[].class);

		return Arrays.asList(response.getBody());

	}
	// ------------------------------ POSTs
	// --------------------------------------------------------
	public String salvarDiagnosticoGestacao(DiagnosticoGestacao dg) {

		RequestEntity<DiagnosticoGestacao> request = RequestEntity.post(URI.create(URI_RESULTADO)).body(dg);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}

	public String salvarParto(Parto p) {

		RequestEntity<Parto> request = RequestEntity.post(URI.create(URI_PARTO + "/salvar/parto")).body(p);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}

	public String salvarBovino(Bovino b) {

		RequestEntity<Bovino> request = RequestEntity.post(URI.create(URI_BASE + "/salvar/bovino")).body(b);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}

	public String salvarInseminacao(Inseminacao i) {

		RequestEntity<Inseminacao> request = RequestEntity.post(URI.create(URI_INSEMINACAO)).body(i);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}

	public String salvarMorte(Morte morte) {

		RequestEntity<Morte> request = RequestEntity.post(URI.create(URI_BASE + "/salvar/morte")).body(morte);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}

	public String salvarDesmama(Desmama desmama) {

		RequestEntity<Desmama> request = RequestEntity.post(URI.create(URI_BASE + "/salvar/desmama")).body(desmama);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}

	public String salvarPesagem(Peso peso, Integer id) {

		RequestEntity<Peso> request = RequestEntity.post(URI.create("http://127.0.0.1:8080/bovino/" + id + "/peso"))
				.body(peso);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}

	public String salvarVendido(Venda venda) {

		RequestEntity<Venda> request = RequestEntity.post(URI.create(URI_BASE + "/salvar/vendido")).body(venda);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}

	public String salvarAbatido(Abatido abatido) {

		RequestEntity<Abatido> request = RequestEntity.post(URI.create(URI_BASE + "/salvar/abatido")).body(abatido);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}

	public String salvarEcc(Ecc ecc, Integer id) {

		RequestEntity<Ecc> request = RequestEntity.post(URI.create("http://127.0.0.1:8080/bovino/" + id + "/ecc"))
				.body(ecc);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}

	public String salvarMatriz(FichaMatriz fichaMatriz, Integer id) {

		RequestEntity<FichaMatriz> request = RequestEntity
				.post(URI.create("http://127.0.0.1:8080/bovino/" + id + "/fichamatriz")).body(fichaMatriz);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}

	public String salvarTouro(Touro touro) {

		RequestEntity<Touro> request = RequestEntity.post(URI.create(URI_BASE + "/salvar/touro")).body(touro);

		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);

		return response.getHeaders().getLocation().toString();
	}

	public List<TrueAndFalse> listarTrueAndFalse() {

		List<TrueAndFalse> tafs = new ArrayList<TrueAndFalse>();
		TrueAndFalse tafa = new TrueAndFalse();
		TrueAndFalse tafb = new TrueAndFalse();

		tafa.setMonta(true);
		tafa.setTipo("Monta");

		tafb.setMonta(false);
		tafb.setTipo("Inseminaçao");

		tafs.add(tafa);
		tafs.add(tafb);
		return tafs;

	}

}
