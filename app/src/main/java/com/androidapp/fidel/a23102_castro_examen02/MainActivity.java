package com.androidapp.fidel.a23102_castro_examen02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Response;
import com.androidapp.fidel.a23102_castro_examen02.Utils.BoardHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Board> boardArray = new ArrayList<>();
    ArrayList<Snakes> snakesArray = new ArrayList<>();
    ArrayList<Ladders> ladderArray = new ArrayList<>();
    String url = "http://107.170.247.123:2403/snakes-ladders";
    BoardHelper bHelper;
    BoardAdapter oBoardAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bHelper = new BoardHelper(getApplicationContext());
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_ladderBegin = (Button) findViewById(R.id.ladderBeginButton);
        Button btn_ladderDest = (Button) findViewById(R.id.ladderDestButton);
        Button btn_snakesBegin = (Button) findViewById(R.id.snakeBeginButton);
        Button btn_snakesDest = (Button) findViewById(R.id.snakeDestButton);
        Button btn_create = (Button) findViewById(R.id.createGameButton);

        final EditText txt_boardName = (EditText) findViewById(R.id.boardNameText);
        final EditText txt_boardAuth = (EditText) findViewById(R.id.authorText);
        final EditText txt_ladderBegin = (EditText) findViewById(R.id.ladderBeginText);
        final EditText txt_ladderDest = (EditText) findViewById(R.id.ladderDestinationText);
        final EditText txt_snakesBegin = (EditText) findViewById(R.id.snakesBeginText);
        final EditText txt_snakesDest = (EditText) findViewById(R.id.snakeDestinationText);

        ListView listview = (ListView) findViewById(R.id.lv_board);
        oBoardAdapter = new BoardAdapter(this);
        listview.setAdapter(oBoardAdapter);

        final BoardHelper bHelper = new BoardHelper(getApplicationContext());
        bHelper.open();
        boardArray = bHelper.getAllBoards();
        bHelper.close();
        oBoardAdapter.addAll(boardArray);


        final JsonArrayRequest boardArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String result = "";
                Board board = new Board();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.getString(i));
                        String name = jsonObject.getString("userId");
                        String author = jsonObject.getString("id");
                        bHelper.open();
                        board = bHelper.addBoard(name, author);
                        bHelper.close();
                        boardArray.add(board);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });


        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txt_boardName.getText().toString();
                String auth = txt_boardAuth.getText().toString();
                Board b = new Board(
                        name,auth
                );
                boardArray.add(b);
                oBoardAdapter.add(b);
                oBoardAdapter.notifyDataSetChanged();
                bHelper.open();
                bHelper.addBoard(name, auth);
                bHelper.close();
            }
        });

    }
}
