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

}
