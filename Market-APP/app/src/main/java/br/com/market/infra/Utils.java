package br.com.market.infra;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

import br.com.market.models.AbstractModel;
import br.com.market.models.ErroMarket;

public class Utils {

    public static String DATA_FORMATO = "dd/MM/yyyy";

    /**
     * Metodo resposável por verificar se o device possui conexão com a internet.
     * 
     * @param cm
     * @return
     */
    public static boolean isOnline(ConnectivityManager cm) {
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static String objectToJson(AbstractModel model) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(model);
        } catch (JsonProcessingException e) {
            //TODO TRATAR ERRO
            e.printStackTrace();
        }

        return jsonString;
    }

    public static AbstractModel jsonToObject(String jsonString, Class classe) {
        ObjectMapper mapper = new ObjectMapper();
        AbstractModel model = null;
        try {
            model = (AbstractModel) mapper.readValue(jsonString, classe);
         } catch (IOException e) {
            //TODO TRATAR ERRO
            e.printStackTrace();
        }

        return model;
    }

    public static String validaErroServicoHttpClient(HttpClientErrorException e, String mensagemPadrao) {
        if (HttpStatus.BAD_REQUEST.equals(e.getStatusCode())) {
            ErroMarket erroMarket = null;
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = e.getResponseBodyAsString();
            try {
                erroMarket = mapper.readValue(jsonString, ErroMarket.class);
            } catch (IOException e1) {
                e1.printStackTrace();
                return mensagemPadrao;
            }

            if (erroMarket != null) {
                return erroMarket.getMensagem();
            }
        }

        return mensagemPadrao;
    }

}
