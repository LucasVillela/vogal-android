package br.com.vogal.vogal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import jp.wasabeef.richeditor.RichEditor;

public class EditorActivity extends AppCompatActivity {


    private RichEditor mEditor;
    String noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Intent note = getIntent();
        noteId = note.getStringExtra("note");


    }


    private void mountEditor(){
        mEditor = (RichEditor) findViewById(R.id.editor);
        mEditor.setEditorFontSize(22);
        mEditor.setPlaceholder("Insert text here...");


        findViewById(R.id.action_undo).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.undo();
            }
        });

        findViewById(R.id.action_redo).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.redo();
            }
        });

        findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                mEditor.setBold();
            }
        });
    }
}
