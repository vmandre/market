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
import br.com.market.model.HistoricoFerias;

@Controller
@RequestMapping("ferias")
public class FeriasController extends AbstractController<HistoricoFerias> {
	
	@Autowired
	private EntityService<HistoricoFerias> feriasService;

	@Override
	public HistoricoFerias consultar(Long cod) {
		return feriasService.get(cod);
	}
	
	@Override
	public List<HistoricoFerias> listar() {
		return feriasService.listAll();
	}
	
	@Override
	public @ResponseBody HistoricoFerias criar(@RequestBody HistoricoFerias ferias) {
		feriasService.save(ferias);
		return ferias;
	}

	@Override
	public void atualizar(Long cod, @RequestBody HistoricoFerias ferias) throws ModelNaoEncontradoException {
		if (feriasService.get(cod) == null) {
			throw new ModelNaoEncontradoException(HttpStatus.BAD_REQUEST.value(), "Registro não encontrado.");
		}
		
		feriasService.update(ferias);
	}

	@Override
	public void remover(Long cod) throws ModelNaoEncontradoException {
		if (feriasService.get(cod) == null) {
			throw new ModelNaoEncontradoException(HttpStatus.BAD_REQUEST.value(), "Registro não encontrado.");
		}
		
		feriasService.deleteByCod(cod);
	}

}
