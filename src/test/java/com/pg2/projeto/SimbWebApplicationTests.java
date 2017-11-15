package com.pg2.projeto;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.pg2.simbweb.SimbWebApplication;
import com.pg2.simbweb.client.GestaoClient;
import com.pg2.simbweb.domain.bovino.Peso;

import junit.framework.Assert;


@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SimbWebApplication.class)
@WebAppConfiguration
public class SimbWebApplicationTests {

	@Autowired
	GestaoClient gestaoClient;
	
	
	@Test
	public void testSalvarPesagem() {
		Peso peso = new Peso();
		peso.setDataPesagem(new Date());
		peso.setIdPeso(500l);
		peso.setStatus(true);
		
		String pesagemSalva = gestaoClient.salvarPesagem(peso, 1);
		System.out.println(pesagemSalva);
		
		Assert.assertNotNull(pesagemSalva);
	}

}


