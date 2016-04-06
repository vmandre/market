package br.com.market.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.market.model.Loja;
import br.com.market.service.LojaService;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({ "file:src/test/resources/context-test.xml" })
@ContextConfiguration({ "file:src/test/resources/spring/application-context-test.xml" })
public class ModelTest {
	@Autowired
    private ApplicationContext applicationContext;
	@Autowired
	private LojaService service;
	
	
	@Before
	public void setUp() {
//		context = new AnnotationConfigApplicationContext();
//		context.scan("br.com.market"); 
//		context.refresh();
	}
	
	@Test
	public void listarLojas() {
//		UnidadeService service = (UnidadeService) context.getBean("unidadeService");
		List<Loja> lojas = service.listAll();
		
		if (lojas != null) {
			assert(true);
		} else {
			assert(false);
		}
	}

}
