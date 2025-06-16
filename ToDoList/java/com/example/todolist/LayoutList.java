package com.example.todolist;

import android.widget.LinearLayout;

public class LayoutList {

    private LinearLayout layout;

    public LayoutList(LinearLayout layout){
        this.layout = layout;
    }
    public LinearLayout getLayout() {
        return layout;
    }
    public void setLayout(LinearLayout layout){
        this.layout = layout;
    }
}