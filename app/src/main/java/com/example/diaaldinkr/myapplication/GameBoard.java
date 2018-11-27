package com.example.diaaldinkr.myapplication;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class GameBoard {
    String[][] board;
    int oScores;
    int xScores;
    int drawScores;
    public int getDrawScores() {
        return drawScores;
    }

    public void setDrawScores(int drawScores) {
        this.drawScores = drawScores;
    }

    public int getxScores() {
        return xScores;
    }

    public void setxScores(int xScores) {
        this.xScores = xScores;
    }


    public int getoScores() {
        return oScores;
    }

    public void setoScores(int oScores) {
        this.oScores = oScores;
    }


    public GameBoard() {

        board = new String[3][3];
        for (int i = 0 ;i<board.length;i++){
            for (int j = 0 ;j<board[0].length;j++){
                board[i][j]=" ";
            }
        }
    }

    public void setMark(int x, int y, String mark) {
        board[x][y] = mark;
    }

    public Map checkWin(String[][] board) {
        Map dictionary = new HashMap();
        boolean win;
        String winner="";
        if (board[0][0].equals(board[0][1]) && board[0][1].equals(board[0][2])  && !board[0][0].equals(" ")&& !board[0][1].equals(" ") && !board[0][2].equals(" ")) { //
            win = true;
            winner="firstrow";
        } else if (board[1][0].equals(board[1][1]) && board[1][1].equals(board[1][2]) && !board[1][0].equals(" ")&& !board[1][1].equals(" ")&& !board[1][2].equals(" ")){
            win = true;
            winner="secondrow";
        }else if(board[2][0].equals(board[2][1]) && board[2][1].equals(board[2][2]) && !board[2][0].equals(" ")&& !board[2][1].equals(" ")&& !board[2][2].equals(" ")){
            win=true;
            winner="thirdrow";
        }else if(board[0][0].equals(board[1][0]) && board[1][0].equals(board[2][0]) && !board[0][0].equals(" ")&& !board[1][0].equals(" ")&& !board[2][0].equals(" ")){
            win=true;
            winner="firstcol";
        }else if(board[0][1].equals(board[1][1])&&board[1][1].equals(board[2][1])&& !board[0][1].equals(" ")&& !board[1][1].equals(" ")&& !board[2][1].equals(" ")){
            win=true;
            winner="secondcol";
        }else if(board[0][2].equals(board[1][2])&&board[1][2].equals(board[2][2])&& !board[0][2].equals(" ")&& !board[1][2].equals(" ")&& !board[2][2].equals(" ")){
            win=true;
            winner="thirdcol";
        }else if(board[0][0].equals(board[1][1])&&board[1][1].equals(board[2][2])&& !board[0][0].equals(" ")&& !board[1][1].equals(" ")&& !board[2][2].equals(" ")){
            win=true;
            winner="firstalk";
        }else if(board[2][0].equals(board[1][1])&&board[1][1].equals(board[0][2])&& !board[2][0].equals(" ")&& !board[1][1].equals(" ")&& !board[0][2].equals(" ")){
            win=true;
            winner="secondalk";
        }else{
            win=false;
            winner="draw";
        }
        dictionary.put(win,winner);
        return dictionary;
    }

}
