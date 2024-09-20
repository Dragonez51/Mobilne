package com.example.mobilne;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextPassword2;
    Button Submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.name);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextPassword2 = findViewById(R.id.password2);

        Submit = findViewById(R.id.submit);
        Submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String password2 = editTextPassword2.getText().toString().trim();

                if(name.isEmpty()){
                    Toast.makeText(MainActivity.this,"Proszę wprowadzić imię", Toast.LENGTH_SHORT).show();
                }else if(email.isEmpty()){
                    Toast.makeText(MainActivity.this,"Proszę wprowadzić adres email", Toast.LENGTH_SHORT).show();
                }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(MainActivity.this,"Niepoprawny adres email", Toast.LENGTH_SHORT).show();
                }else if(password.length()<6){
                    Toast.makeText(MainActivity.this,"Podane hasło jest za krótkie", Toast.LENGTH_SHORT).show();
                }else if(!password.equals(password2)){
                    Toast.makeText(MainActivity.this,"Podane hasła są różne", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Formularz przesłany poprawnie", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}