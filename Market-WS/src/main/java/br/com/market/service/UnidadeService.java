package br.com.market.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import br.com.market.model.Unidade;
=======
import br.com.market.bean.Unidade;
>>>>>>> branch 'master' of https://github.com/filipedosreis/market.git
import br.com.market.repository.UnidadeRepository;

@Service
public class UnidadeService {
	
	@Autowired
	private UnidadeRepository repository;
	
	public List<Unidade> findAllUnidades() {
		return repository.findAllUnidades();
	}

}
