package br.com.market.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.market.infra.model.Entity;
import br.com.market.model.Loja;

@RequestMapping("/")
public abstract class AbstractController <T extends Entity<?>> {
	
	@RequestMapping(value = "/listarTodos", method = RequestMethod.POST)
	public abstract @ResponseBody List<T> listar();

	@RequestMapping(value = "/consultarByCod", method = RequestMethod.POST)
	public abstract @ResponseBody T consultar(Long cod);
	
	@RequestMapping(value = "/criar", method = RequestMethod.POST)
	public abstract @ResponseBody Loja criar(@RequestBody T entity);
	
	@RequestMapping(value="/atualizar",  method = RequestMethod.PUT)
	@ResponseBody
	public abstract void atualizar(Long cod, @RequestBody T entity);

	@RequestMapping(value="/remover",  method = RequestMethod.DELETE)
	@ResponseBody
	public abstract void remover(Long cod);
	

}
