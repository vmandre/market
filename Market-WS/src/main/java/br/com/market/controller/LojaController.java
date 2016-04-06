package br.com.market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.market.model.Loja;
import br.com.market.service.LojaService;

@Controller
@RequestMapping("loja")
public class LojaController extends AbstractController{

	@Autowired
	private LojaService lojaService;

	@Autowired
	public LojaController(LojaService svc) {
		lojaService = svc;
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public @ResponseBody List<Loja> listar() {
		return lojaService.listAll();
	}

	@RequestMapping(value = "/consultarByCod", method = RequestMethod.GET)
	public @ResponseBody Loja consultarLoja(Long cod) {
		return lojaService.get(cod);
	}

}
