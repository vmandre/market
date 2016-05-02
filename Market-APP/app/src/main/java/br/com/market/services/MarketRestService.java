package br.com.market.services;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import br.com.market.models.Funcionario;


//@Rest(rootUrl = "http://192.168.0.7:8080/market", converters = { MappingJackson2HttpMessageConverter.class })
@Rest(rootUrl = "http://market-env.sa-east-1.elasticbeanstalk.com", converters = { MappingJackson2HttpMessageConverter.class })
public interface MarketRestService {

    @Post("/funcionario/login")
    public Funcionario login(@Body Funcionario funcionario);
}
