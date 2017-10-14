package br.com.vogal.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.vogal.model.Note;
import br.com.vogal.vogal.EditorActivity;
import br.com.vogal.vogal.R;

/**
 * Created by lbvil on 13/10/2017.
 */

public class NoteAdapter extends BaseAdapter {

    List<Note> noteList;
    Context context;


    public NoteAdapter(Context context, List<Note> noteList){
        this.context = context;
        this.noteList = noteList;
    }

    @Override
    public int getCount() {
        return noteList.size();
    }

    @Override
    public Object getItem(int position) {
        return noteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View grid = inflater.inflate(R.layout.grid_note, parent, false);
        TextView title = (TextView) grid.findViewById(R.id.titleTextView);
        TextView noteText = (TextView) grid.findViewById(R.id.noteTextView);

        Note note = noteList.get(position);
        String html = note.getTextHTML();
        if(html == null){
            html = "";
        }

        title.setText(note.getTitle());
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.N){
            noteText.setText(Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT));
        }else{
            noteText.setText(Html.fromHtml(html));
        }

        grid.setOnClickListener(getClickListener(position));

        return grid;
    }

    private View.OnClickListener getClickListener(final int position){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note = noteList.get(position);
                Intent intent = new Intent(context, EditorActivity.class);
                intent.putExtra("note",note.getId());

                context.startActivity(intent);
            }
        };
    }

    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
