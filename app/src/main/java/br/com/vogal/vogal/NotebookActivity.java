package br.com.vogal.vogal;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.vogal.adapter.NotebookAdapter;
import br.com.vogal.model.Notebook;
import br.com.vogal.model.Notebooks;
import br.com.vogal.service.NotebookService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotebookActivity extends AppCompatActivity {

    ArrayList<Notebook> notebookArrayList = new ArrayList<>();

    NotebookAdapter arrayAdapter;

    NotebookService notebookService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook);

        ListView listView = (ListView) findViewById(R.id.notebookList);
        arrayAdapter = new NotebookAdapter(this, notebookArrayList);

        listView.setAdapter(arrayAdapter);

        notebookService = new NotebookService(getApplicationContext());

        Call<Notebooks> call = notebookService.getNotebooks();

        call.enqueue(handleNotebook());

    }


    private Callback<Notebooks> handleNotebook(){
        return new Callback<Notebooks>() {
            @Override
            public void onResponse(Call<Notebooks> call, Response<Notebooks> response) {
                Notebooks notebooks = response.body();
                if (notebooks != null){
                    List<Notebook> notebookList = notebooks.getNotebooks();
                    if( notebookList.size() > 0){
                        notebookArrayList.clear();
                        notebookArrayList.addAll(notebookList);

                        arrayAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<Notebooks> call, Throwable t) {
                Snackbar.make(findViewById(R.id.activity_notebook),"Failed to load notebooks",Snackbar.LENGTH_SHORT).show();
            }
        };
    }
}
