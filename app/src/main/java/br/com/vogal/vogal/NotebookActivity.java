package br.com.vogal.vogal;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.vogal.model.Notebook;
import br.com.vogal.service.NotebookService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotebookActivity extends AppCompatActivity {

    ArrayList<Notebook> notebookArrayList = new ArrayList<>();

    ArrayAdapter arrayAdapter;

    NotebookService notebookService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook);

        ListView listView = (ListView) findViewById(R.id.notebookList);
        arrayAdapter = new ArrayAdapter(this, R.layout.row_notebook, notebookArrayList);

        notebookService = new NotebookService(getApplicationContext());

        Call<List<Notebook>> call = notebookService.getNotebooks();

        call.enqueue(handleNotebook());

    }


    private Callback<List<Notebook>> handleNotebook(){
        return new Callback<List<Notebook>>() {
            @Override
            public void onResponse(Call<List<Notebook>> call, Response<List<Notebook>> response) {
                List<Notebook> notebookList = response.body();
                if( notebookList.size() > 0){
                    notebookArrayList.clear();
                    notebookArrayList.addAll(notebookList);

                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Notebook>> call, Throwable t) {
                Snackbar.make(findViewById(R.id.activity_notebook),"Failed to load notebooks",Snackbar.LENGTH_SHORT).show();
            }
        };
    }
}
