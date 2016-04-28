package br.com.market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.market.model.Funcionario;
import br.com.market.service.FuncionarioService;

@Controller
@RequestMapping("funcionario")
public class FuncionarioController extends AbstractController<Funcionario> {
	
	@Autowired
	private FuncionarioService funcionarioService;

	@Override
	public List<Funcionario> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Funcionario consultar(Long cod) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void atualizar(Long cod, Funcionario entity) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Funcionario criar(Funcionario entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remover(Long cod) {
		// TODO Auto-generated method stub
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Funcionario login(@RequestBody Funcionario entity) {
		return funcionarioService.login(entity);
	}
	

}
