package br.com.vogal.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import br.com.vogal.client.VogalClient;
import br.com.vogal.utils.DateTypeAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lbvil on 12/10/2017.
 */

public class BaseService {

    protected Retrofit retrofit;

    protected VogalClient vogalClient;

    public BaseService(){

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();
        this.retrofit = new Retrofit.Builder()
                //.baseUrl("https://vogal-homolog.appspot.com/api/")
                .baseUrl("http://10.0.2.2:8080/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        this.vogalClient = this.retrofit.create(VogalClient.class);
    }
}
