package br.com.market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.market.model.Unidade;
import br.com.market.service.UnidadeService;

@Controller
public class UnidadeController extends AbstractController{

	@Autowired
	private UnidadeService unidadeService;

	@Autowired
	public UnidadeController(UnidadeService svc) {
		unidadeService = svc;
	}

	@RequestMapping(value = "/consultarUnidades", method = RequestMethod.GET)
	public @ResponseBody List<Unidade> consultarUnidades() {
		return unidadeService.findAllUnidades();
	}

	@RequestMapping(value = "/consultarUnidade", method = RequestMethod.GET)
	public @ResponseBody Unidade consultarUnidade(Long id) {
		return unidadeService.findAUnidade(id);
	}

}
