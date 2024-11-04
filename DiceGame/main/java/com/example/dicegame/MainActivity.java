package com.example.dicegame;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button roll, reset;

    private TextView dice1, dice2, dice3, dice4, dice5, rollResult, score, rollCount;

    int rollNumber = 0;

    int overallScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roll = findViewById(R.id.roll);

        dice1 = findViewById(R.id.dice1);
        dice2 = findViewById(R.id.dice2);
        dice3 = findViewById(R.id.dice3);
        dice4 = findViewById(R.id.dice4);
        dice5 = findViewById(R.id.dice5);

        rollResult = findViewById(R.id.rollResult);
        score = findViewById(R.id.score);

        rollCount = findViewById(R.id.rollCount);

        reset = findViewById(R.id.reset);


        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice();
                updateRollCount();
            }
        });

        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                resetGame();
            }
        });

    }

    private void rollDice(){
        Random rand = new Random();

        int[] diceResults = new int[5];

        for(int i=0;i<diceResults.length;i++){
            diceResults[i] = rand.nextInt(6)+1;
        }

        displayDiceResults(diceResults);

        int wynik = 0;

        for(int i=0; i<diceResults.length; i++) {
            int ileRazy = 1;
            for (int j = 0; j < diceResults.length; j++) {
                if (i == j) {
                    continue;
                }
                if (diceResults[i] == diceResults[j]) {
                    ileRazy++;
                    break;
                }
            }
            if (ileRazy > 1) {
                wynik += diceResults[i];
            }
        }

        updateScore(wynik);

    }

    private void resetGame(){
        dice1.setText("?");
        dice2.setText("?");
        dice3.setText("?");
        dice4.setText("?");
        dice5.setText("?");

        rollNumber = 0;

        overallScore = 0;

        rollResult.setText("Wynik tego losowania: 0");

        score.setText("Wynik gry: 0");

        rollCount.setText("Liczba rzutów: 0");
    }

    private void updateScore(int newScore){
        rollResult.setText("Wynik tego losowania: "+newScore);
        overallScore += newScore;
        score.setText("Wynik gry: "+overallScore);
    }

    private void updateRollCount(){
        rollNumber++;
        rollCount.setText("Liczba rzutów: "+rollNumber);
    }

    private void displayDiceResults(int[] diceResults){
        dice1.setText(String.valueOf(diceResults[0]));
        dice2.setText(String.valueOf(diceResults[1]));
        dice3.setText(String.valueOf(diceResults[2]));
        dice4.setText(String.valueOf(diceResults[3]));
        dice5.setText(String.valueOf(diceResults[4]));
    }

}