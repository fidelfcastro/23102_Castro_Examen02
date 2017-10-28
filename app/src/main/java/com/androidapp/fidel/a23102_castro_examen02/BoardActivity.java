package com.androidapp.fidel.a23102_castro_examen02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by fidel on 10/27/2017.
 */

public class BoardActivity extends AppCompatActivity{
    BoardAdapter oBoardAdapter;
    ListView oListView;
    ArrayList<Board> boardArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_activity);

        oListView=(ListView) findViewById(R.id.lv_board);
        oBoardAdapter =new BoardAdapter(this);
        oListView.setAdapter(oBoardAdapter);

        boardArray = this.getIntent().getParcelableArrayListExtra("Array");
        parceBoard(boardArray);

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void parceBoard(ArrayList<Board> lBoard) {
        oBoardAdapter.clear();

        for(Board oBoard : lBoard) {
            oBoardAdapter.add(oBoard);
        }

        oBoardAdapter.notifyDataSetChanged();
    }
}
