package br.com.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.market.infra.service.EntityService;
import br.com.market.model.FuncionarioVaga;
import br.com.market.repository.FuncionarioVagaRepository;

@Service
public class FuncionarioVagaService extends EntityService<FuncionarioVaga> {
	
	@SuppressWarnings("unused")
	@Autowired
	private FuncionarioVagaRepository repository;
	
	@Autowired
	public FuncionarioVagaService(FuncionarioVagaRepository repository) {
		super(repository);
		this.repository = repository;
	}

}
