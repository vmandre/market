package br.com.market.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.market.infra.service.EntityService;
import br.com.market.model.Holerite;
import br.com.market.repository.HoleriteRepository;

@Service
public class HoleriteService extends EntityService<Holerite> {
	
	@Autowired
	private HoleriteRepository repository;
	
	@Autowired
	public HoleriteService(HoleriteRepository repository) {
		super(repository);
		this.repository = repository;
	}
	
	/**
	 * Servi�o para consulta de holeria a partir do cod funcion�rio e data de emiss�o
	 * 
	 * @param codFuncionario
	 * @param dataEmissao
	 * @return
	 */
	public Holerite holeritePorFuncionarioEDataEmissao(Long codFuncionario, Date dataEmissao) {
		return repository.holeritePorFuncionarioEDataEmissao(codFuncionario, dataEmissao);
	}

}
