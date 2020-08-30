package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int player = 0; //0:yellow 1:red

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean gameActive = true;

    MediaPlayer mediaPlayer;
    public void dropIn(View view)
    {
        ImageView counter = (ImageView)view;

       int tag = Integer.parseInt(counter.getTag().toString());

       if(gameState[tag] == 2 && gameActive) {
           gameState[tag] = player;
           mediaPlayer.start();

           if (player == 0) {
               counter.setImageResource(R.drawable.yellow);
               counter.animate().alpha(1).setDuration(500);

               player = 1;
           } else {
               counter.setImageResource(R.drawable.red);
               counter.animate().alpha(1).setDuration(500);
               player = 0;
           }

           Button playAgainButton = (Button)findViewById(R.id.playAgainButton);
           TextView winnerTextView = (TextView)findViewById(R.id.winnerTextView);



           for (int[] winningPosition : winningPositions) {
               if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                   gameActive = false;
                   String winner = "";

                   if (player == 1) {
                       winner = "Yellow";
                       winnerTextView.setTextColor(Color.parseColor("#FBD943"));

                   } else {
                       winner = "Red";
                       winnerTextView.setTextColor(Color.parseColor("#FC2A1D"));
                   }

                   winnerTextView.setText(winner + " wins!");
                   playAgainButton.setVisibility(View.VISIBLE);
                   winnerTextView.setVisibility(View.VISIBLE);
                   winnerTextView.animate().scaleX(2f).setDuration(1000);
               }

//               else
//               {
//                  int count = 0;
//
//                  for(int i =0 ; i<gameState.length ; i++)
//                  {
//                      if(gameState[i]==1 || gameState[i]==0)
//                      {
//                          count++;
//                      }
//                  }
//
//                  if(count == 9)
//                  {
//                      gameActive = false;
//                      winnerTextView.setText("None wins!");
//                      playAgainButton.setVisibility(View.VISIBLE);
//                      winnerTextView.setVisibility(View.VISIBLE);
//                      winnerTextView.animate().scaleX(2f).setDuration(1000);
//
//                  }
//               }

               else
               {
                   boolean gameover = true;
                   for(int track : gameState)
                   {
                       if(track == 2)
                       {
                           gameover = false;

                       }
                   }

                   if(gameover)
                   {
                       winnerTextView.setText("None wins!");
                      playAgainButton.setVisibility(View.VISIBLE);
                      winnerTextView.setVisibility(View.VISIBLE);
                      winnerTextView.animate().scaleX(2f).setDuration(1000);
                   }
               }


           }




       }


    }



    public void hello(View view)
    {
        Button playAgainButton = (Button)findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView)findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        winnerTextView.setScaleX(0f);
        winnerTextView.setTextColor(Color.parseColor("#100F0F"));

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i =0 ; i < gridLayout.getChildCount() ; i++)
        {
            ImageView counter = (ImageView)gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        player = 0; //0:yellow 1:red

        for(int i=0 ; i<gameState.length ; i++)
        {
            gameState[i]=2;
        }

        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.sound);

    }
}