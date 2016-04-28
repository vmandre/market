package br.com.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.market.infra.service.EntityService;
import br.com.market.model.Loja;
import br.com.market.repository.LojaRepository;

@Service
public class LojaService extends EntityService<Loja> {
	
	@SuppressWarnings("unused")
	@Autowired
	private LojaRepository repository;
	
	@Autowired
	public LojaService(LojaRepository repository) {
		super(repository);
		this.repository = repository;
	}

}
