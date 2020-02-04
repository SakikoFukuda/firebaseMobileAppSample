package com.example.myapplication.marubatu;

import android.widget.Button;

public class Board {

    public enum BOARD_SQUARE {
        // value値はボタンの「タグ」と合わせる
        TOP_LEFT("TopLeft"),
        TOP_CENTER("TopCenter"),
        TOP_RIGHT("TopRight"),
        MIDDLE_LEFT("MiddleLeft"),
        MIDDLE_CENTER("MiddleCenter"),
        MIDDLE_RIGHT("MiddleRight"),
        BOTTOM_LEFT("BottomLeft"),
        BOTTOM_CENTER("BottomCenter"),
        BOTTOM_RIGHT("BottomRight");

        String value;

        BOARD_SQUARE(String s) {
            this.value = s;
        }

        public static BOARD_SQUARE of(String s) {
            for (BOARD_SQUARE bs : BOARD_SQUARE.values()) {
                if (bs.value.equals(s)) {
                    return bs;
                }
            }
            throw new IllegalArgumentException(s);
        }

        public boolean equals(String s) {
            return this.value.equals(s);
        }
    }

    public enum TOKEN {
        MARU,
        BATU,
        NONE
    }

    private TOKEN[][] board = { {TOKEN.NONE, TOKEN.NONE, TOKEN.NONE}, {TOKEN.NONE, TOKEN.NONE, TOKEN.NONE}, {TOKEN.NONE, TOKEN.NONE, TOKEN.NONE} };

    public void init() {
        board = new TOKEN[][]{ {TOKEN.NONE, TOKEN.NONE, TOKEN.NONE}, {TOKEN.NONE, TOKEN.NONE, TOKEN.NONE}, {TOKEN.NONE, TOKEN.NONE, TOKEN.NONE} };
    }

    public void add(Button button, TOKEN token) {
        BOARD_SQUARE square = BOARD_SQUARE.of((String) button.getTag());

        switch (square) {
            case TOP_LEFT:
                board[0][0] = token;
                break;
            case TOP_CENTER:
                board[0][1] = token;
                break;
            case TOP_RIGHT:
                board[0][2] = token;
                break;
            case MIDDLE_LEFT:
                board[1][0] = token;
                break;
            case MIDDLE_CENTER:
                board[1][1] = token;
                break;
            case MIDDLE_RIGHT:
                board[1][2] = token;
                break;
            case BOTTOM_LEFT:
                board[2][0] = token;
                break;
            case BOTTOM_CENTER:
                board[2][1] = token;
                break;
            case BOTTOM_RIGHT:
                board[2][2] = token;
                break;
        }
    }

    // TODO 引数は不要だとおもう。「今」どこに何を置いても勝敗の条件は変わらないので。
    // TODO 判定処理を賢くしたい。盤面を回転させれば、パターンは4つだけ？
    public boolean judge(Button button, TOKEN token) {
        BOARD_SQUARE square = BOARD_SQUARE.of((String) button.getTag());

        switch (square) {
            case TOP_LEFT:
                return (board[0][1].equals(token) && board[0][2].equals(token)) // 横
                        || (board[1][0].equals(token) && board[2][0].equals(token)) // 縦
                        || (board[1][1].equals(token) && board[2][2].equals(token)) // 斜め
                        ;
            case TOP_CENTER:
                return (board[0][0].equals(token) && board[0][2].equals(token)) // 横
                        || (board[1][1].equals(token) && board[2][1].equals(token)) // 縦
                        ;
            case TOP_RIGHT:
                return (board[0][0].equals(token) && board[0][1].equals(token)) // 横
                        || (board[1][2].equals(token) && board[2][2].equals(token)) // 縦
                        || (board[1][1].equals(token) && board[2][0].equals(token)) // 斜め
                        ;
            case MIDDLE_LEFT:
                return (board[1][1].equals(token) && board[1][2].equals(token)) // 横
                        || (board[0][0].equals(token) && board[2][0].equals(token)) // 縦
                        ;
            case MIDDLE_CENTER:
                return (board[1][0].equals(token) && board[1][2].equals(token)) // 横
                        || (board[0][1].equals(token) && board[2][1].equals(token)) // 縦
                        || (board[0][0].equals(token) && board[2][2].equals(token)) // 斜め
                        || (board[0][2].equals(token) && board[2][0].equals(token)) // 斜め
                        ;
            case MIDDLE_RIGHT:
                return (board[1][0].equals(token) && board[1][2].equals(token)) // 横
                        || (board[0][2].equals(token) && board[2][2].equals(token)) // 縦
                        ;
            case BOTTOM_LEFT:
                return (board[2][1].equals(token) && board[2][2].equals(token)) // 横
                        || (board[0][0].equals(token) && board[1][0].equals(token)) // 縦
                        || (board[0][2].equals(token) && board[1][1].equals(token)) // 斜め
                        ;
            case BOTTOM_CENTER:
                return (board[2][0].equals(token) && board[2][2].equals(token)) // 横
                        || (board[0][1].equals(token) && board[1][1].equals(token)) // 縦
                        ;
            case BOTTOM_RIGHT:
                return (board[2][0].equals(token) && board[2][1].equals(token)) // 横
                        || (board[0][2].equals(token) && board[1][2].equals(token)) // 縦
                        || (board[0][0].equals(token) && board[1][1].equals(token)) // 斜め
                        ;
        }

        return false;
    }

    public boolean isFullfill() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == TOKEN.NONE) return false;
            }
        }
        return true;
    }

    public boolean isEmpty() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!(board[i][j] == TOKEN.NONE)) return false;
            }
        }
        return true;
    }
}
