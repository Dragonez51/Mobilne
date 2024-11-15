package com.example.zad6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private EditText email, pass, rptPass;
    private Button submit;

    private String emailVal, passVal, rptPassVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);

        email = findViewById(R.id.ET_mail);
        pass = findViewById(R.id.ET_password);
        rptPass = findViewById(R.id.ET_rptPassword);

        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                emailVal = String.valueOf(email.getText());
                passVal = String.valueOf(pass.getText());
                rptPassVal = String.valueOf(rptPass.getText());
                if(!emailVal.contains("@")){
                    result.setText("Nieprawidłowy adres e-mail!");
                }else if(!passVal.equals(rptPassVal)){
                    result.setText("Hasła się róźnią");
                }else{
                    result.setText("Witaj "+emailVal);
                }
            }
        });

    }
}