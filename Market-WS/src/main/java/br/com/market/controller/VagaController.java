package br.com.market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.market.infra.exception.ModelNaoEncontradoException;
import br.com.market.model.Vaga;
import br.com.market.service.VagaService;

@Controller
@RequestMapping("vaga")
public class VagaController extends AbstractController<Vaga> {
	
	@Autowired
	private VagaService vagaService;

	@Override
	public List<Vaga> listar() {
		return vagaService.listAll();
	}

	@Override
	public Vaga consultar(Long cod) {
		return vagaService.get(cod);
	}

	@Override
	public Vaga criar(@RequestBody Vaga entity) {
		vagaService.save(entity);
		return entity;
	}

	@Override
	public void atualizar(Long cod, @RequestBody Vaga entity) throws ModelNaoEncontradoException {
		if (vagaService.get(cod) == null) {
			throw new ModelNaoEncontradoException(HttpStatus.BAD_REQUEST.value(), "Vaga não encontrada.");
		}
		
		vagaService.update(entity);		
	}

	@Override
	public void remover(Long cod) throws ModelNaoEncontradoException {
		if (vagaService.get(cod) == null) {
			throw new ModelNaoEncontradoException(HttpStatus.BAD_REQUEST.value(), "Vaga não encontrada.");
		}
		
		vagaService.deleteByCod(cod);
	}
	
	@RequestMapping(value = "/listarPorLoja", method = RequestMethod.POST)
	public @ResponseBody List<Vaga> vagasPorLoja(Long codLoja) {
		return vagaService.vagasPorLoja(codLoja);
	}
	
	@RequestMapping(value = "/listarDiferenteLoja", method = RequestMethod.POST)
	public @ResponseBody List<Vaga> vagasDifennteLoja(Long codLoja) {
		return vagaService.vagasDiferenteLoja(codLoja);
	}
	
	@RequestMapping(value = "/listaRapidaPorLoja", method = RequestMethod.POST)
	public @ResponseBody List<Vaga> vagasListaRapida(Long codLoja, Integer limite) {
		return vagaService.vagasListaRapida(codLoja, limite);
	}
	
	@RequestMapping(value = "/listaRapidaDiferenteLoja", method = RequestMethod.POST)
	public @ResponseBody List<Vaga> vagasListaRapidaDiferenteLoja(Long codLoja, Integer limite) {
		return vagaService.vagasListaRapidaDiferenteLoja(codLoja, limite);
	}
	
}
