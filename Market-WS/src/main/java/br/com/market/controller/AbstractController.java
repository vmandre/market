package br.com.market.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.market.infra.exception.MarketErroBean;
import br.com.market.infra.exception.ModelNaoEncontradoException;
import br.com.market.infra.model.Entity;

@RequestMapping("/")
public abstract class AbstractController <T extends Entity<?>> {
	
	@RequestMapping(value = "/listarTodos", method = RequestMethod.POST)
	public abstract @ResponseBody List<T> listar();

	@RequestMapping(value = "/consultarPorCod", method = RequestMethod.POST)
	public abstract @ResponseBody T consultar(Long cod);
	
	@RequestMapping(value = "/criar", method = RequestMethod.POST)
	public abstract @ResponseBody T criar(@RequestBody T entity);
	
	@RequestMapping(value="/atualizar",  method = RequestMethod.PUT)
	@ResponseBody
	public abstract void atualizar(Long cod, @RequestBody T entity) throws ModelNaoEncontradoException;

	@RequestMapping(value="/remover",  method = RequestMethod.DELETE)
	@ResponseBody
	public abstract void remover(Long cod) throws ModelNaoEncontradoException;
	
	@ExceptionHandler(ModelNaoEncontradoException.class)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public MarketErroBean handleException(ModelNaoEncontradoException e) {
		MarketErroBean erro = new MarketErroBean(e.getCodigo(), e.getMensagem());
	    return erro;
	}

}
