package br.com.market.services;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

import br.com.market.models.Funcionario;
import br.com.market.models.Noticia;
import br.com.market.models.Vaga;


//@Rest(rootUrl = "http://192.168.0.7:8080/market", converters = { MappingJackson2HttpMessageConverter.class })
@Rest(rootUrl = "http://market-env.sa-east-1.elasticbeanstalk.com", converters = { MappingJackson2HttpMessageConverter.class })
public interface MarketRestService {

    @Post("/funcionario/login")
    public Funcionario login(@Body Funcionario funcionario);

    @Post("/vaga/listarPorLoja?codLoja={codLoja}")
    public List<Vaga> consultarVagasPorLoja(@Path Long codLoja);

    @Post("/vaga/listaRapidaPorLoja?codLoja={codLoja}&limite={limite}")
    public List<Vaga> consultaRapidaVagasPorLoja(@Path Long codLoja, @Path Integer limite);

    @Post("/vaga/listaRapidaDiferenteLoja?codLoja={codLoja}&limite={limite}")
    public List<Vaga> consultaRapidaVagasDiferenteLoja(@Path Long codLoja, @Path Integer limite);

    @Post("/vaga/listarDiferenteLoja?codLoja={codLoja}")
    public List<Vaga> consultarVagasDiferenteLoja(@Path Long codLoja);

    @Post("/vaga/consultarPorCod?cod={codVaga}")
    public Vaga consultarVaga(@Path Long codVaga);

    @Post("/noticia/listarPorLoja?codLoja={codLoja}")
    public Object[] consultarNoticiasPorLoja(@Path Long codLoja);

    @Post("/noticia/listaRapidaPorLoja?codLoja={codLoja}&limite={limite}")
    public Object[] consultaRapidaNoticiasPorLoja(@Path Long codLoja, @Path Integer limite);

    @Post("/noticia/listarRapidaDiferenteLoja?codLoja={codLoja}&limite={limite}")
    public Object[] consultaRapidaNoticiasDiferenteLoja(@Path Long codLoja, @Path Integer limite);

    @Post("/noticia/listarDiferenteLoja?codLoja={codLoja}")
    public Object[] consultarNoticiasDiferenteLoja(@Path Long codLoja);

    @Post("/noticia/consultarPorCod?cod={codNoticia}")
    public Noticia consultarNoticia(@Path Long codNoticia);

}
