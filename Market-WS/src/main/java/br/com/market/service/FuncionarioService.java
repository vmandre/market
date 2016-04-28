package br.com.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.market.infra.service.EntityService;
import br.com.market.model.Funcionario;
import br.com.market.repository.FuncionarioRepository;

@Service
public class FuncionarioService extends EntityService<Funcionario> {
	
	@Autowired
	private FuncionarioRepository repository;
	
	@Autowired
	public FuncionarioService(FuncionarioRepository repository) {
		super(repository);
		this.repository = repository;
	}
	
	public Funcionario login(Funcionario entity) {
		return repository.login(entity);
	}

}
