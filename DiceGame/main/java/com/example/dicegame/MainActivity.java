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
                updateDice();
                rollNumber++;
                rollCount.setText("Liczba rzutów: "+rollNumber);
            }
        });

        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                reset();
            }
        });

    }

    private void reset(){
        dice1.setText("?");
        dice2.setText("?");
        dice3.setText("?");
        dice4.setText("?");
        dice5.setText("?");

        rollNumber = 0;

        overallScore = 0;

        rollResult.setText("Wynik tego losowania: ");

        score.setText("Wynik gry: ");

        rollCount.setText("Liczba rzutów: ");
    }

    private void updateDice(){
        Random rand = new Random();

        int diceValue1 = rand.nextInt(6)+ 1;
        int diceValue2 = rand.nextInt(6)+ 1;
        int diceValue3 = rand.nextInt(6)+ 1;
        int diceValue4 = rand.nextInt(6)+ 1;
        int diceValue5 = rand.nextInt(6)+ 1;

        dice1.setText(String.valueOf(diceValue1));
        dice2.setText(String.valueOf(diceValue2));
        dice3.setText(String.valueOf(diceValue3));
        dice4.setText(String.valueOf(diceValue4));
        dice5.setText(String.valueOf(diceValue5));

        int[] tab = new int[5];
        tab[0] = diceValue1;
        tab[1] = diceValue2;
        tab[2] = diceValue3;
        tab[3] = diceValue4;
        tab[4] = diceValue5;

        int wynik = 0;

        for(int i=0; i<tab.length; i++){
            int ileRazy = 1;
            for(int j=0;j<tab.length;j++){
                if(i==j){
                    continue;
                }
                if(tab[i] == tab[j]){
                    ileRazy++;
                    break;
                }
            }
            if(ileRazy>1){
                wynik+=tab[i];
            }
        }

        rollResult.setText("Wynik tego losowania: "+wynik);

        overallScore+=wynik;

        score.setText("Wynik gry: "+overallScore);
    }
}