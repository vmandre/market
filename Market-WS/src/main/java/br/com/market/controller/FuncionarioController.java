package br.com.market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.market.infra.exception.ModelNaoEncontradoException;
import br.com.market.model.Funcionario;
import br.com.market.service.FuncionarioService;

@Controller
@RequestMapping("funcionario")
public class FuncionarioController extends AbstractController<Funcionario> {
	
	@Autowired
	private FuncionarioService funcionarioService;

	@Override
	public List<Funcionario> listar() {
		return funcionarioService.listAll();
	}

	@Override
	public Funcionario consultar(Long cod) {
		return funcionarioService.get(cod);
	}
	
	@Override
	public Funcionario criar(Funcionario entity) {
		funcionarioService.save(entity);
		return entity;
	}

	@Override
	public void atualizar(Long cod, Funcionario entity) throws ModelNaoEncontradoException {
		if (funcionarioService.get(cod) == null) {
			throw new ModelNaoEncontradoException(HttpStatus.BAD_REQUEST.value(), "Funcionário não encontrado.");
		}
		
		funcionarioService.update(entity);
	}

	@Override
	public void remover(Long cod) throws ModelNaoEncontradoException {
		if (funcionarioService.get(cod) == null) {
			throw new ModelNaoEncontradoException(HttpStatus.BAD_REQUEST.value(), "Funcionário não encontrado.");
		}
		
		funcionarioService.deleteByCod(cod);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Funcionario> login(@RequestBody Funcionario entity) throws ModelNaoEncontradoException {
		Funcionario funcionario = funcionarioService.login(entity);
		
		if (funcionario == null) {
			throw new ModelNaoEncontradoException(HttpStatus.BAD_REQUEST.value(), "Usuário/Senha não encontrado.");
		}
		
		return ResponseEntity.ok(funcionario);
	}
	
}
