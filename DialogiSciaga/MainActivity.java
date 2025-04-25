package com.example.dialogi;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.dialog1).setOnClickListener(view -> showAlertDialog());
        findViewById(R.id.dialog2).setOnClickListener(view -> showListDialog());
        findViewById(R.id.dialog3).setOnClickListener(view -> showDatePickerDialog());
        findViewById(R.id.dialog4).setOnClickListener(view -> showTimePickerDialog());
        findViewById(R.id.dialog5).setOnClickListener(view -> showCustomDialog());
    }

    private void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Dialog1 - showAlertDialog");
        builder.setMessage("przykładowa wiadomość");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Kliknięto OK", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Anulowano", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();
    }

    private void showListDialog(){
        final String[] items = {"Item 1", "Item 2", "Item 3", "Item 4"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Wybierz opcję");

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, items[i], Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();
    }

    private void showDatePickerDialog(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Toast.makeText(MainActivity.this, "Wybrana data: " + i + "/" + i1 + "/" + i2, Toast.LENGTH_SHORT).show();
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    private void showTimePickerDialog(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                String formattedMinute = (i1 < 10) ? "0" + i1 : String.valueOf(i1);
                Toast.makeText(MainActivity.this, "Wybrano godzine: "+ i + ":"+formattedMinute, Toast.LENGTH_SHORT).show();
            }
        }, hour, minute, true);

        timePickerDialog.show();
    }

    private void showCustomDialog(){
        final Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.custom_dialog);

        EditText et = dialog.findViewById(R.id.et_custom_input);
        Button submit = dialog.findViewById(R.id.btnSubmit);
        Button cancel = dialog.findViewById(R.id.btnCancel);

        submit.setOnClickListener(view -> {
            String inputText = et.getText().toString().trim();

            if(!inputText.isEmpty()){
                Toast.makeText(MainActivity.this, "Wprowadzono: "+inputText, Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this, "Nie wpowadzono żadnych danych!", Toast.LENGTH_SHORT).show();
            }

            dialog.dismiss();
        });

        cancel.setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "Anulowano", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        dialog.show();
    }
}