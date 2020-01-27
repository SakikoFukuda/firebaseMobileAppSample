package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.marubatu.MaruBatuBoard;
import com.example.myapplication.marubatu.Turn;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TURN_FIRST  = "○の番です";
    private final String TURN_SECOND = "×の番です";

    private Turn currentTurn;
    private final MaruBatuBoard board = new MaruBatuBoard();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.turn_comment);
        textView.setText(TURN_FIRST);
        currentTurn = Turn.FIRST;
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;

        switch (currentTurn){
            case FIRST:
                button.setText("○");
                board.add(button, MaruBatuBoard.TOKEN.MARU);
                judge(button, MaruBatuBoard.TOKEN.MARU);
                break;
            case SECOND:
                button.setText("×");
                board.add(button, MaruBatuBoard.TOKEN.BATU);
                judge(button, MaruBatuBoard.TOKEN.BATU);
                break;
        }
    }

    private void judge(Button button, MaruBatuBoard.TOKEN token) {
        if (board.judge(button, token)) {
            TextView textView = (TextView) findViewById(R.id.maru_batu_result);
            textView.setText("勝負あり！！");
        } else {
            toggleTurn();
        }
    }

    private void toggleTurn() {
        TextView textView = (TextView) findViewById(R.id.turn_comment);

        if (currentTurn.equals(Turn.FIRST)) {
            textView.setText(TURN_SECOND);
            currentTurn = Turn.SECOND;
        } else if (currentTurn.equals(Turn.SECOND)) {
            textView.setText(TURN_FIRST);
            currentTurn = Turn.FIRST;
        }
    }
}
