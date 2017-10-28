package com.androidapp.fidel.a23102_castro_examen02;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by fidel on 10/27/2017.
 */

public class BoardAdapter extends ArrayAdapter<Board> {
    public BoardAdapter(Context context) {
        super(context, R.layout.game_layout, R.id.text2);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View objectView=super.getView(position,convertView,parent);
        TextView txt2=(TextView) objectView.findViewById(R.id.text2);
        TextView txt3 = (TextView) objectView.findViewById(R.id.text3);


        Board oBoard = this.getItem(position);

        txt2.setText("Name:   " + oBoard.getName());
        txt3.setText("Author:   " + oBoard.getAuthor());

        return objectView;
    }
}
