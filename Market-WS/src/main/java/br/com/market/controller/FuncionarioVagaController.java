package br.com.market.controller;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.market.infra.exception.ModelNaoEncontradoException;
import br.com.market.infra.service.EntityService;
import br.com.market.model.FuncionarioVaga;

@Controller
@RequestMapping("funcionarioVaga")
public class FuncionarioVagaController extends AbstractController<FuncionarioVaga> {
	
	@Autowired
	private EntityService<FuncionarioVaga> funcionarioVagaService;

	@Override
	public FuncionarioVaga consultar(Long cod) {
		return funcionarioVagaService.get(cod);
	}
	
	@Override
	public List<FuncionarioVaga> listar() {
		return funcionarioVagaService.listAll();
	}
	
	@Override
	public @ResponseBody FuncionarioVaga criar(@RequestBody FuncionarioVaga funcionarioVaga) throws ModelNaoEncontradoException {
		if (funcionarioVaga.getStatus() == null) {
			funcionarioVaga.setStatus(FuncionarioVaga.Status.EM_ANALISE);
		}
		
		try {
			funcionarioVagaService.save(funcionarioVaga);
		} catch (ConstraintViolationException e) {
			throw new ModelNaoEncontradoException(HttpStatus.BAD_REQUEST.value(), "Funcionário já aplicou para a vaga.");
		}
		return funcionarioVaga;
	}

	@Override
	public void atualizar(Long cod, @RequestBody FuncionarioVaga funcionarioVaga) throws ModelNaoEncontradoException {
		if (funcionarioVagaService.get(cod) == null) {
			throw new ModelNaoEncontradoException(HttpStatus.BAD_REQUEST.value(), "Registro não encontrado.");
		}
		
		funcionarioVagaService.update(funcionarioVaga);
	}

	@Override
	public void remover(Long cod) throws ModelNaoEncontradoException {
		if (funcionarioVagaService.get(cod) == null) {
			throw new ModelNaoEncontradoException(HttpStatus.BAD_REQUEST.value(), "Registro não encontrado.");
		}
		
		funcionarioVagaService.deleteByCod(cod);
	}

}
