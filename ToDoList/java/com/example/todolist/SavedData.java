package com.example.todolist;

import android.content.MutableContextWrapper;

import java.util.LinkedList;

public abstract class SavedData {
    public static LinkedList<LayoutList> LAYOUTLIST;
    public static LinkedList<LinkedList<Task>> TASKSLIST;
    public static LinkedList<Settings> SETTINGS;
    public static MutableContextWrapper currentContext;
    public static int toDoListID;
}