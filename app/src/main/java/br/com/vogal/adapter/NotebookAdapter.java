package br.com.vogal.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.ocpsoft.prettytime.PrettyTime;
import org.w3c.dom.Text;

import java.util.List;

import br.com.vogal.model.Notebook;
import br.com.vogal.vogal.R;

/**
 * Created by lbvil on 12/10/2017.
 */

public class NotebookAdapter extends ArrayAdapter<Notebook>{

    private Context context;
    private List<Notebook> notebookList;


    public NotebookAdapter(Context context, List<Notebook> notebookList){
        super(context, R.layout.row_notebook, notebookList);
        this.context = context;
        this.notebookList = notebookList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_notebook, parent, false);

        TextView notebookTitle  = (TextView) rowView.findViewById(R.id.notebookTitleTextView);
        TextView lastModified  = (TextView) rowView.findViewById(R.id.lastModifiedTextView);
        TextView totalNotes  = (TextView) rowView.findViewById(R.id.totalNotesTextView);

        Notebook notebook = notebookList.get(position);


        notebookTitle.setText(notebook.getTitle());
        lastModified.setText(new PrettyTime().format(notebook.getUpdatedAt()));
        totalNotes.setText(new Integer(new Double(Math.random() * 10).intValue()).toString() + " notes");


        return rowView;
    }

}
