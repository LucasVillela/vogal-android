package br.com.vogal.service;

import br.com.vogal.client.VogalClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lbvil on 12/10/2017.
 */

public class BaseService {

    protected Retrofit retrofit;

    protected VogalClient vogalClient;

    public BaseService(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://vogal-homolog.appspot.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.vogalClient = this.retrofit.create(VogalClient.class);
    }
}
