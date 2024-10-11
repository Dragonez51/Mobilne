package com.example.zad3;

import androidx.lifecycle.ViewModel;

public class CountViewModel extends ViewModel{
    private int counter;

    private String writeText;

    public String getWriteText(){
        return writeText;
    }

    public void setWriteText(String text){
        writeText = text;
    }

    public int getCounter(){
        return counter;
    }

    public void incrementCounter(){
        counter++;
    }
}
