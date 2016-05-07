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
import br.com.market.model.Noticia;
import br.com.market.service.NoticiaService;

@Controller
@RequestMapping("noticia")
public class NoticiaController extends AbstractController<Noticia> {
	
	@Autowired
	private NoticiaService noticiaService;

	@Override
	public List<Noticia> listar() {
		return noticiaService.listAll();
	}

	@Override
	public Noticia consultar(Long cod) {
		return noticiaService.get(cod);
	}

	@Override
	public Noticia criar(@RequestBody Noticia entity) {
		noticiaService.save(entity);
		return entity;
	}

	@Override
	public void atualizar(Long cod, @RequestBody Noticia entity) throws ModelNaoEncontradoException {
		if (noticiaService.get(cod) == null) {
			throw new ModelNaoEncontradoException(HttpStatus.BAD_REQUEST.value(), "Noticia não encontrada.");
		}
		
		noticiaService.update(entity);		
	}

	@Override
	public void remover(Long cod) throws ModelNaoEncontradoException {
		if (noticiaService.get(cod) == null) {
			throw new ModelNaoEncontradoException(HttpStatus.BAD_REQUEST.value(), "Noticia não encontrada.");
		}
		
		noticiaService.deleteByCod(cod);
	}
	
	@RequestMapping(value = "/listarPorLoja", method = RequestMethod.POST)
	public @ResponseBody List<Noticia> noticiasPorLoja(Long codLoja) {
		return noticiaService.noticiasPorLoja(codLoja);
	}
	
	@RequestMapping(value = "/listarDiferenteLoja", method = RequestMethod.POST)
	public @ResponseBody List<Noticia> noticiasDifennteLoja(Long codLoja) {
		return noticiaService.noticiasDiferenteLoja(codLoja);
	}
	
}
