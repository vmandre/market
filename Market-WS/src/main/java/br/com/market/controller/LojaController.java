package br.com.market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.market.infra.exception.ModelNaoEncontradoException;
import br.com.market.infra.service.EntityService;
import br.com.market.model.Loja;

@Controller
@RequestMapping("loja")
public class LojaController extends AbstractController<Loja> {
	
	@Autowired
	private EntityService<Loja> lojaService;

	@Override
	public Loja consultar(Long cod) {
		return lojaService.get(cod);
	}
	
	@Override
	public List<Loja> listar() {
		return lojaService.listAll();
	}
	
	@Override
	public @ResponseBody Loja criar(@RequestBody Loja loja) {
		lojaService.save(loja);
		return loja;
	}

	@Override
	public void atualizar(Long cod, @RequestBody Loja loja) throws ModelNaoEncontradoException {
		if (lojaService.get(cod) == null) {
			throw new ModelNaoEncontradoException(HttpStatus.BAD_REQUEST.value(), "Loja n�o encontrada.");
		}
		
		lojaService.update(loja);
	}

	@Override
	public void remover(Long cod) throws ModelNaoEncontradoException {
		if (lojaService.get(cod) == null) {
			throw new ModelNaoEncontradoException(HttpStatus.BAD_REQUEST.value(), "Loja n�o encontrada.");
		}
		
		lojaService.deleteByCod(cod);
	}

}
