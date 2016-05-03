package br.com.market.infra;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import br.com.market.models.AbstractModel;

public class Utils {

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

}
