package br.com.market.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.market.infra.service.EntityService;
import br.com.market.model.Vaga;
import br.com.market.repository.VagaRepository;

@Service
public class VagaService extends EntityService<Vaga> {
	
	@Autowired
	private VagaRepository repository;
	
	private Integer LIMITE = new Integer(3);
	
	@Autowired
	public VagaService(VagaRepository repository) {
		super(repository);
		this.repository = repository;
	}
	
	/**
	 * Consulta vagas para loja do codigo da loja informado.
	 * 
	 * @param codLoja
	 * @return
	 */
	public List<Vaga> vagasPorLoja(Long codLoja) {
		return repository.vagasPorLoja(codLoja);
	}
	
	/**
	 * Consulta vagas para lojas diferentes do codigo informado.
	 * 
	 * @param codLoja
	 * @return
	 */
	public List<Vaga> vagasDiferenteLoja(Long codLoja) {
		return repository.vagasDiferenteLoja(codLoja);
	}

	/**
	 * Consulta vagas para loja do codigo informado e com limite de resultado.
	 * 
	 * @param codLoja
	 * @param limite
	 * @return
	 */
	public List<Vaga> vagasListaRapida(Long codLoja, Integer limite) {
		if (limite == null) {
			limite = LIMITE;
		}
		return repository.vagasListaRapidaPorLoja(codLoja, limite);
	}

	/**
	 * Consulta vagas para lojas diferentes do codigo informado e com limite de resultado.
	 * 
	 * @param codLoja
	 * @param limite
	 * @return
	 */
	public List<Vaga> vagasListaRapidaDiferenteLoja(Long codLoja, Integer limite) {
		if (limite == null) {
			limite = LIMITE;
		}
		return repository.vagasListaRapidaDiferenteLoja(codLoja, limite);
	}
	
	

}
