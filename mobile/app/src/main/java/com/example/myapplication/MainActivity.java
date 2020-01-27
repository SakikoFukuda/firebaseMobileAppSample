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

    // TODO 状態を保持する変数が3つもある。減らしたい。
    private Turn currentTurn;
    private boolean winOrLose = false;
    private final MaruBatuBoard board = new MaruBatuBoard();

    // TODO 盤面のリセット機能がほしい

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
        if (board.isFullfill() || winOrLose) return;

        Button button = (Button) v;
        switch (currentTurn){
            case FIRST:
                button.setText("○");
                board.add(button, MaruBatuBoard.TOKEN.MARU);
                winOrLose = judge(button, MaruBatuBoard.TOKEN.MARU);
                break;
            case SECOND:
                button.setText("×");
                board.add(button, MaruBatuBoard.TOKEN.BATU);
                winOrLose = judge(button, MaruBatuBoard.TOKEN.BATU);
                break;
        }
    }

    private boolean judge(Button button, MaruBatuBoard.TOKEN token) {
        if (board.judge(button, token)) {
            TextView textView = (TextView) findViewById(R.id.maru_batu_result);
            textView.setText("勝負あり！！");
            return true;
        } else {
            toggleTurn();
            return false;
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
