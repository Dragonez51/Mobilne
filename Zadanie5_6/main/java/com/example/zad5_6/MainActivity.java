package com.example.zad5_6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView polubieniaTV, komunikaty;
    private Button polub, usun, zapisz, zobacz;
    private EditText emailET, passwordET, rptPasswordET;

    private int polubienia;
    private String zalogowanyUzytkownik = "Nikt nie był zalogowany poprzednio";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        polubienia = 0;

        polubieniaTV = findViewById(R.id.polubienia);
        komunikaty = findViewById(R.id.komunikaty);

        polub = findViewById(R.id.polub);
        usun = findViewById(R.id.usun);
        zapisz = findViewById(R.id.zapisz);
        zobacz = findViewById(R.id.zobacz);

        emailET = findViewById(R.id.email);
        passwordET = findViewById(R.id.password);
        rptPasswordET = findViewById(R.id.rptPassword);

        polub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                polubienia++;
                polubieniaTV.setText(polubienia + " polubień");
            }
        });

        usun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (polubienia > 0) {
                    polubienia--;
                    polubieniaTV.setText(polubienia + " polubień");
                }
            }
        });

        zapisz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = String.valueOf(emailET.getText()).trim();
                String pass = String.valueOf(passwordET.getText()).trim();
                String rptPass = String.valueOf(rptPasswordET.getText()).trim();
                if (!email.contains("@")) {
                    komunikaty.setText("Nieprawidłowy adres e-mail");
                } else if (!pass.equals(rptPass)) {
                    komunikaty.setText("Hasła się różnią");
                } else {
                    zalogowanyUzytkownik = email;
                    komunikaty.setText("Zarejestrowano: " + zalogowanyUzytkownik);
                }
            }
        });

        zobacz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                komunikaty.setText(zalogowanyUzytkownik);
            }
        });
    }
}