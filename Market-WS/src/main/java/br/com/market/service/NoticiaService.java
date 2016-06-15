package br.com.market.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.market.infra.service.EntityService;
import br.com.market.model.Noticia;
import br.com.market.repository.NoticiaRepository;

@Service
public class NoticiaService extends EntityService<Noticia> {
	
	@Autowired
	private NoticiaRepository repository;
	
	private Integer LIMITE = new Integer(3);
	
	@Autowired
	public NoticiaService(NoticiaRepository repository) {
		super(repository);
		this.repository = repository;
	}
	
	/**
	 * Consulta noticias para loja do codigo da loja informado.
	 * 
	 * @param codLoja
	 * @return
	 */
	public List<Noticia> noticiasPorLoja(Long codLoja) {
		return repository.noticiasPorLoja(codLoja);
	}
	
	/**
	 * Consulta noticias para lojas diferentes do codigo informado.
	 * 
	 * @param codLoja
	 * @return
	 */
	public List<Noticia> noticiasDiferenteLoja(Long codLoja) {
		return repository.noticiasDiferenteLoja(codLoja);
	}
	
	/**
	 * Consulta vagas para loja do codigo informado e com limite de resultado.
	 * 
	 * @param codLoja
	 * @param limite
	 * @return
	 */
	public List<Noticia> noticiasListaRapida(Long codLoja, Integer limite) {
		if (limite == null) {
			limite = LIMITE;
		}
		return repository.noticiasListaRapidaPorLoja(codLoja, limite);
	}
	
	/**
	 * Consulta vagas para lojas diferentes do codigo informado e com limite de resultado.
	 * 
	 * @param codLoja
	 * @param limite
	 * @return
	 */
	public List<Noticia> noticiasListaRapidaDiferenteLoja(Long codLoja, Integer limite) {
		if (limite == null) {
			limite = LIMITE;
		}
		return repository.noticiasListaRapidaDiferenteLoja(codLoja, limite);
	}

}
