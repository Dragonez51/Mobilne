package com.example.zadanie3;

import androidx.lifecycle.ViewModel;

public class StateViewModel extends ViewModel{
    private int counter = 0;
    private String writeText;
    private boolean checked = false;
    private boolean darkMode = false;
    public int getCounter(){
        return counter;
    }
    public void setCounter(int counter){
        this.counter = counter;
    }
    public void incrementCounter(){
        this.counter++;
    }
    public String getWriteText(){
        return writeText;
    }
    public void setWriteText(String text){
        writeText = text;
    }
    public boolean getChecked(){
        return checked;
    }
    public void setChecked(boolean checked){
        this.checked = checked;
    }
    public boolean getDarkMode(){
        return darkMode;
    }
    public void setDarkMode(boolean darkMode){
        this.darkMode = darkMode;
    }
}
