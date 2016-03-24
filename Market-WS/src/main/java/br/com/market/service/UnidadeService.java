package br.com.market.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.market.infra.service.EntityService;
import br.com.market.model.Unidade;
import br.com.market.repository.UnidadeRepository;

@Service
public class UnidadeService extends EntityService<Unidade> {
	
	@Autowired
	private UnidadeRepository repository;
	
	@Autowired
	public UnidadeService(UnidadeRepository repository) {
		super(repository);
		this.repository = repository;
	}

	
	public List<Unidade> findAllUnidades() {
//		return repository.findAllUnidades();
		return repository.listAll();
	}

	public Unidade findAUnidade(Long id) {
//		return repository.findAUnidade(id);
		return repository.get(id);
	}

}
