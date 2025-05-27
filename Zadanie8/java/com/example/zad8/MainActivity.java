package com.example.zad8;

import static android.view.View.VISIBLE;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = findViewById(R.id.edit);
        edit.setOnClickListener(view->{
            findViewById(R.id.err1).setVisibility(android.view.View.GONE);
            findViewById(R.id.err2).setVisibility(android.view.View.GONE);
            findViewById(R.id.correct).setVisibility(android.view.View.GONE);
            findViewById(R.id.cancel).setVisibility(android.view.View.GONE);

            emailDialog();
        });
    }

    private void emailDialog(){
        final Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.email_dialog);

        EditText et = dialog.findViewById(R.id.email);
        Button submit = dialog.findViewById(R.id.zapisz);
        Button cancel = dialog.findViewById(R.id.anuluj);

        submit.setOnClickListener(view -> {
            String inputText = et.getText().toString().trim();
            if(inputText.contains("@")){
                passwordDialog(inputText);
            }else{
                findViewById(R.id.err1).setVisibility(android.view.View.VISIBLE);
            }
            dialog.dismiss();
        });

        cancel.setOnClickListener(view -> {
            findViewById(R.id.cancel).setVisibility(android.view.View.VISIBLE);
            dialog.dismiss();
        });

        dialog.show();
    }

    private void passwordDialog(String email){
        final Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.password_dialog);

        EditText pass1 = dialog.findViewById(R.id.password);
        EditText pass2 = dialog.findViewById(R.id.rpt_pass);

        Button submit = dialog.findViewById(R.id.zapisz);
        Button cancel = dialog.findViewById(R.id.anuluj);

        submit.setOnClickListener(view -> {
            if(String.valueOf(pass1.getText()).equals(String.valueOf(pass2.getText()))){
                TextView emailView = findViewById(R.id.emailView);
                emailView.setText(email);
                TextView passView = findViewById(R.id.passwordView);
                passView.setText(pass1.getText());
                findViewById(R.id.correct).setVisibility(android.view.View.VISIBLE);
            }else{
                findViewById(R.id.err2).setVisibility(android.view.View.VISIBLE);
            }
            dialog.dismiss();
        });

        cancel.setOnClickListener(view -> {
            findViewById(R.id.cancel).setVisibility(android.view.View.VISIBLE);
            dialog.dismiss();
        });

        dialog.show();
    }
}