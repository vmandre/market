package br.com.market.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.market.infra.exception.ModelNaoEncontradoException;
import br.com.market.model.Holerite;
import br.com.market.service.HoleriteService;

@Controller
@RequestMapping("holerite")
public class HoleriteController extends AbstractController<Holerite> {
	
	@Autowired
	private HoleriteService holeriteService;

	@Override
	public Holerite consultar(Long cod) {
		return holeriteService.get(cod);
	}
	
	@Override
	public List<Holerite> listar() {
		return holeriteService.listAll();
	}
	
	@Override
	public @ResponseBody Holerite criar(@RequestBody Holerite holerite) {
		holeriteService.save(holerite);
		return holerite;
	}

	@Override
	public void atualizar(Long cod, @RequestBody Holerite holerite) throws ModelNaoEncontradoException {
		if (holeriteService.get(cod) == null) {
			throw new ModelNaoEncontradoException(HttpStatus.BAD_REQUEST.value(), "Holerite não encontrado.");
		}
		
		holeriteService.update(holerite);
	}

	@Override
	public void remover(Long cod) throws ModelNaoEncontradoException {
		if (holeriteService.get(cod) == null) {
			throw new ModelNaoEncontradoException(HttpStatus.BAD_REQUEST.value(), "Holerite não encontrado.");
		}
		
		holeriteService.deleteByCod(cod);
	}
	
	@RequestMapping(value = "/consultarPorFuncionarioEDataEmissao", method = RequestMethod.POST)
	public @ResponseBody Holerite holeritePorFuncionarioEDataEmissao(Long codFuncionario, @DateTimeFormat(pattern = "yyyy-MM-dd")Date dataEmissao) throws ModelNaoEncontradoException, IOException {
		Holerite holerite = holeriteService.holeritePorFuncionarioEDataEmissao(codFuncionario, dataEmissao);
		if (holerite == null) {
			throw new ModelNaoEncontradoException(HttpStatus.BAD_REQUEST.value(), "Holerite indisponível para esta data.");
		}
		
	    return holerite;
	}

}
