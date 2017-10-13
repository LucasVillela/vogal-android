package br.com.vogal.service;

import android.content.Context;

import java.util.List;

import br.com.vogal.client.VogalClient;
import br.com.vogal.model.Notebook;
import br.com.vogal.model.Notebooks;
import retrofit2.Call;

/**
 * Created by lbvil on 12/10/2017.
 */

public class NotebookService extends BaseService {

    private String token;

    public NotebookService(Context context){
        super();

        SessionService session = new SessionService(context);
        this.token = session.getToken();
    }

    public Call<Notebooks> getNotebooks(){
        return this.vogalClient.getNotebooks(this.token);
    }
}
