package br.com.market.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

<<<<<<< HEAD
import br.com.market.model.Unidade;
import br.com.market.service.UnidadeService;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({ "file:src/test/resources/context-test.xml" })
@ContextConfiguration({ "file:src/main/resources/spring/config/beanLocations.xml" })
=======
import br.com.market.bean.Unidade;
import br.com.market.service.UnidadeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/test/resources/context-test.xml" })
>>>>>>> branch 'master' of https://github.com/filipedosreis/market.git
public class ModelTest {
	@Autowired
    private ApplicationContext applicationContext;
	@Autowired
	private UnidadeService service;
	
	
	@Before
	public void setUp() {
//		context = new AnnotationConfigApplicationContext();
//		context.scan("br.com.market"); 
//		context.refresh();
	}
	
	@Test
	public void findAllUnidades() {
//		UnidadeService service = (UnidadeService) context.getBean("unidadeService");
		List<Unidade> unidades = service.findAllUnidades();
		
		if (unidades != null) {
			assert(true);
		} else {
			assert(false);
		}
	}

}
