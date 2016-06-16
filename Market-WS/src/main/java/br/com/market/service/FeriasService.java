package br.com.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.market.infra.service.EntityService;
import br.com.market.model.HistoricoFerias;
import br.com.market.repository.FeriasRepository;

@Service
public class FeriasService extends EntityService<HistoricoFerias> {
	
	@SuppressWarnings("unused")
	@Autowired
	private FeriasRepository repository;
	
	@Autowired
	public FeriasService(FeriasRepository repository) {
		super(repository);
		this.repository = repository;
	}

}
