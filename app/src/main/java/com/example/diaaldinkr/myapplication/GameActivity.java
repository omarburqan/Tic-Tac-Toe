package com.example.diaaldinkr.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    GameBoard board =new GameBoard();
    String player = "X";
    String playerTurn="X Turn";
    Map win;
    int clicked=0;
    int draws=0;
    int xScores=0;
    int oScores=0;
    Button startOver;
    MediaPlayer handClapSound;
    SharedPreferences sp;
    //int haveScores=0;
    TextView drawScore;
    TextView xScore;
    TextView oScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // initiate the buttons
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        startOver=findViewById(R.id.startOver);
        handClapSound = MediaPlayer.create(this,R.raw.handclap);
        drawScore =findViewById(R.id.draws);
        xScore =findViewById(R.id.xScore);
        oScore =findViewById(R.id.oScore);
        final Button c00 =findViewById(R.id.c00);
        final Button c01 =findViewById(R.id.c01);
        final Button c02 =findViewById(R.id.c02);
        final Button c10 =findViewById(R.id.c10);
        final Button c11 =findViewById(R.id.c11);
        final Button c12 =findViewById(R.id.c12);
        final Button c20 =findViewById(R.id.c20);
        final Button c21 =findViewById(R.id.c21);
        final Button c22 =findViewById(R.id.c22);
        c00.setOnClickListener(this);
        c01.setOnClickListener(this);
        c02.setOnClickListener(this);
        c10.setOnClickListener(this);
        c11.setOnClickListener(this);
        c12.setOnClickListener(this);
        c20.setOnClickListener(this);
        c21.setOnClickListener(this);
        c22.setOnClickListener(this);

        startOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Need method and mor prepare
                handClapSound.stop();
                handClapSound = MediaPlayer.create(GameActivity.this,R.raw.handclap);
                board = new GameBoard();
                c00.setText(" ");
                c01.setText(" ");
                c02.setText(" ");
                c10.setText(" ");
                c11.setText(" ");
                c12.setText(" ");
                c20.setText(" ");
                c21.setText(" ");
                c22.setText(" ");
                disableEnable(true,c00,c01,c02,c10,c11,c12,c20,c21,c22);
                playerTurn="X Turn";
                player = "X";
                changeLable();
                clicked=0;
            }
        });
       sp = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {
        clicked++;
        Button temp = (Button) v;
        startOver.setEnabled(false);
        if(R.id.c00==v.getId()){ // when the player click it will:
            board.setMark(0,0,player);// save the mark in gameboard class after that

        }else if(R.id.c01==v.getId()){
            board.setMark(0,1,player);

        }else if(R.id.c02==v.getId()){
            board.setMark(0,2,player);

        }else if(R.id.c10==v.getId()){
            board.setMark(1,0,player);

        }else if(R.id.c11==v.getId()){
            board.setMark(1,1,player);

        }else if(R.id.c12==v.getId()){
            board.setMark(1,2,player);

        }else if(R.id.c20==v.getId()){
            board.setMark(2,0,player);

        }else if(R.id.c21==v.getId()){
            board.setMark(2,1,player);

        }else if(R.id.c22==v.getId()){
            board.setMark(2,2,player);
        }
        if(player=="X"){
            temp.setTextColor(Color.BLACK);
        }
        if(player=="O"){
            temp.setTextColor(Color.BLACK);
        }
        temp.setText(player);// write to the textview each player sign

        temp.setEnabled(false); // disable the text on click
        check(); // at the bottom
    }
    private void PaintText(String winner){
        Button c00 =findViewById(R.id.c00);
        Button c01 =findViewById(R.id.c01);
        Button c02 =findViewById(R.id.c02);
        Button c10 =findViewById(R.id.c10);
        Button c11 =findViewById(R.id.c11);
        Button c12 =findViewById(R.id.c12);
        Button c20 =findViewById(R.id.c20);
        Button c21 =findViewById(R.id.c21);
        Button c22 =findViewById(R.id.c22);
        if(winner.equals("firstrow")){
            c00.setTextColor(Color.BLUE);
            c01.setTextColor(Color.BLUE);
            c02.setTextColor(Color.BLUE);
        }else if (winner.equals("secondrow")){
            c10.setTextColor(Color.BLUE);
            c11.setTextColor(Color.BLUE);
            c12.setTextColor(Color.BLUE);
        }else if (winner.equals("thirdrow")){
            c20.setTextColor(Color.BLUE);
            c21.setTextColor(Color.BLUE);
            c22.setTextColor(Color.BLUE);
        }else if(winner.equals("firstcol")){
            c00.setTextColor(Color.BLUE);
            c10.setTextColor(Color.BLUE);
            c20.setTextColor(Color.BLUE);
        }
        else if(winner.equals("secondcol")){
            c01.setTextColor(Color.BLUE);
            c11.setTextColor(Color.BLUE);
            c21.setTextColor(Color.BLUE);
        }else if(winner.equals("thirdcol")){
            c02.setTextColor(Color.BLUE);
            c12.setTextColor(Color.BLUE);
            c22.setTextColor(Color.BLUE);
        }else if(winner.equals("firstalk")){
            c00.setTextColor(Color.BLUE);
            c11.setTextColor(Color.BLUE);
            c22.setTextColor(Color.BLUE);
        }else if(winner.equals("secondalk")){
            c20.setTextColor(Color.BLUE);
            c11.setTextColor(Color.BLUE);
            c02.setTextColor(Color.BLUE);
        }
    }
    private void check() { //  get which player won the game from the gameboard class
         Button c00 =findViewById(R.id.c00);
         Button c01 =findViewById(R.id.c01);
         Button c02 =findViewById(R.id.c02);
         Button c10 =findViewById(R.id.c10);
         Button c11 =findViewById(R.id.c11);
         Button c12 =findViewById(R.id.c12);
         Button c20 =findViewById(R.id.c20);
         Button c21 =findViewById(R.id.c21);
         Button c22 =findViewById(R.id.c22);
        changePlayer();
        win =board.checkWin(board.board); // gameboard.java (class) Script
        if(win.containsKey(true) && player=="X" ) { // checking who was the last turn for .
            playerTurn = "O Won!";
            oScores++;
            PaintText((String) win.get(true));
            disableEnable(false,c00,c01,c02,c10,c11,c12,c20,c21,c22); // disable the click on the texts
            changeOScore();
            handClapSound.start();
            Toast.makeText(getApplicationContext(),  "Game Over!", Toast.LENGTH_LONG).show();
        }else if(win.containsKey(true) && player=="O") {
            playerTurn = "X Won!";
            xScores++;
            PaintText((String) win.get(true));
            disableEnable(false,c00,c01,c02,c10,c11,c12,c20,c21,c22);
            changeXScore();
            handClapSound.start();
            Toast.makeText(getApplicationContext(),  "Game Over!", Toast.LENGTH_LONG).show();
        }else if(win.containsKey(false) && clicked==9){
            playerTurn = "Draw - No Winner!";
            draws++;
            disableEnable(false,c00,c01,c02,c10,c11,c12,c20,c21,c22);
            changeDrawScore();
            Toast.makeText(getApplicationContext(),  "Game Over!", Toast.LENGTH_LONG).show();
        }
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("xScores",xScores);
        editor.putInt("oScores", oScores);
        editor.putInt("draws", draws);
        editor.commit();

        changeLable();
    }

    @Override
    protected void onStart() {
        super.onStart();
        draws=sp.getInt("draws",0);
        xScores=sp.getInt("xScores",0);
        oScores=sp.getInt("oScores",0);
        drawScore.setText(draws+"");
        xScore.setText(xScores+"");
        oScore.setText(oScores+"");
    }

    private void disableEnable(boolean flag, Button btn1, Button btn2, Button btn3, Button btn4, Button btn5, Button btn6, Button btn7, Button btn8, Button btn9){
        btn1.setEnabled(flag);
        btn2.setEnabled(flag);
        btn3.setEnabled(flag);
        btn4.setEnabled(flag);
        btn5.setEnabled(flag);
        btn6.setEnabled(flag);
        btn7.setEnabled(flag);
        btn8.setEnabled(flag);
        btn9.setEnabled(flag);
    }
    private void changeDrawScore() { // writing scores

        drawScore.setText(draws+"");
        startOver.setEnabled(true);
    }

    private void changeXScore() { // writings scores

        xScore.setText(xScores+"");
        startOver.setEnabled(true);

    }

    private void changeOScore() { // writing scores

        oScore.setText(oScores+"");
        startOver.setEnabled(true);
    }

    private void changeLable() { // change the turn
        TextView turn =findViewById(R.id.turn);
        turn.setText(playerTurn);
    }

    private void changePlayer(){ // after each click it will change the player turn in order this class to know which player turn it is when needed
        if(player.equals("X")){
            player="O";
            playerTurn="O Turn";
        }else if (player.equals("O")){
            player="X";
            playerTurn="X Turn";

        }
    }
}
