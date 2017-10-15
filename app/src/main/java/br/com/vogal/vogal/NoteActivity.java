package br.com.vogal.vogal;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.internal.LinkedTreeMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.vogal.adapter.NoteAdapter;
import br.com.vogal.model.Note;
import br.com.vogal.model.Notes;
import br.com.vogal.service.NoteService;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoteActivity extends AppCompatActivity {

    GridView gridView;
    NoteAdapter noteAdapter;
    NoteService noteService;
    List<Note> arrayList = new ArrayList<>();
    String notebook;
    SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton floatingActionButton;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Intent notebookIntent = getIntent();
        this.notebook = notebookIntent.getStringExtra("notebook");

        gridView = (GridView) findViewById(R.id.noteGridView);

        context = this;
        noteService = new NoteService(getApplicationContext());
        noteAdapter = new NoteAdapter(this, arrayList);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_add_new_note);
        floatingActionButton.setOnClickListener(getFABAction());
        gridView.setAdapter(noteAdapter);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.gridSwipeRefresh);

        handleRefresh();
        fillGrid();

    }


    @Override
    protected void onRestart() {
        super.onRestart();

        fillGrid();
    }

    private void fillGrid(){
        Call<Notes> call = noteService.getNotes();
        call.enqueue(handleNotes());
    }


    private void handleRefresh(){

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fillGrid();
            }
        });
    }


    private Callback<Notes> handleNotes(){
        return new Callback<Notes>() {
            @Override
            public void onResponse(Call<Notes> call, Response<Notes> response) {
                Notes notes = response.body();
                if (notes != null){
                    List<Note> noteList = notes.getNotes();
                    if (noteList.size() > 0){
                        arrayList.clear();
                        for(Note note:noteList){
                            if(note.getNotebook().equals(notebook)){
                                arrayList.add(note);
                            }
                        }

                        noteAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
            }

            @Override
            public void onFailure(Call<Notes> call, Throwable t) {
                Snackbar.make(findViewById(R.id.activity_note),"Failed to load notes",Snackbar.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        };
    }

    private View.OnClickListener getFABAction(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<ResponseBody> call = noteService.createNote(Note.basicNote(notebook));

                call.enqueue(handleNewNote());
            }
        };
    }

    private Callback<ResponseBody> handleNewNote(){
        return new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody body = response.body();
                Gson gson = new Gson();

                LinkedTreeMap result = null;
                try {
                    result = gson.fromJson(body.string(), LinkedTreeMap.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(result.containsKey("note")){
                    String idNote = (String) result.get("note");

                    Intent intent = new Intent(context, EditorActivity.class);
                    intent.putExtra("note",idNote);

                    context.startActivity(intent);

                }else{
                    Snackbar.make(findViewById(R.id.activity_note),"Failed to create new note",Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Snackbar.make(findViewById(R.id.activity_note),"Failed to create new note",Snackbar.LENGTH_SHORT).show();
            }
        };
    }

}
