package com.example.todolist;

import android.app.Dialog;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout outerLayout;
    private static int currentCollumn = -1;
    private float SCALE;
    private int TITLE_SIZE;
    private int BOX_SIZE ;
    private int PADDING_SIDE ;
    private int PADDING_TOP ;
    private int CONTAINER_SIZE ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SCALE = getResources().getDisplayMetrics().density;
        TITLE_SIZE = (int)(15 * SCALE + 0.5f);
        BOX_SIZE = (int) (150 * SCALE + 0.5f);
        PADDING_SIDE = (int)(20 * SCALE + 0.5f);
        PADDING_TOP = (int)(10 * SCALE + 0.5f);
        CONTAINER_SIZE = (int) (200 * SCALE + 0.5f);

        SavedData.currentContext = new MutableContextWrapper(this);

        outerLayout = findViewById(R.id.outerListLayout);

        boolean isBack = true;

        try{
            getIntent().getExtras().get("extra");
        }catch(NullPointerException e){
            isBack = false;
        }

        if(!isBack){
            SavedData.LAYOUTLIST = new LinkedList<>();
            SavedData.TASKSLIST = new LinkedList<>();
            SavedData.SETTINGS = new LinkedList<>();
            SavedData.toDoListID = 0;
            addToDoListAddingButton();
        }else{
            int currentLayoutIndex = 0;
            for(LayoutList currentLayout : SavedData.LAYOUTLIST){
                try{
                    LinearLayout temp = (LinearLayout) currentLayout.getLayout().getParent();
                    temp.removeAllViews();
                }catch(NullPointerException e){

                }
                for(int i=0; i<currentLayout.getLayout().getChildCount(); i+=2){
                    if(currentLayout.getLayout().getChildAt(i) instanceof ImageView){
                        continue;
                    }
                    LinearLayout tempLayout = (LinearLayout) currentLayout.getLayout().getChildAt(i);
                    TextView Title = (TextView) tempLayout.getChildAt(0);
                    Settings tempSettings = SavedData.SETTINGS.get(i==0?currentLayoutIndex*2:(currentLayoutIndex*2)+1);
                    Title.setTextColor(Color.rgb(tempSettings.getRed(), tempSettings.getGreen(), tempSettings.getBlue()));
                }
                if(currentLayout.getLayout().getChildAt(0) instanceof ImageView){
                    currentLayout.getLayout().getChildAt(0).setOnClickListener(view -> { addToDoListDialog(); });
                }
                if(currentLayout.getLayout().getChildAt(2) instanceof ImageView){
                    currentLayout.getLayout().getChildAt(2).setOnClickListener(view -> { addToDoListDialog(); });
                }
                outerLayout.addView(currentLayout.getLayout());

                currentLayoutIndex++;
            }
        }
    }

    private void addToDoList(String ListName){
        LinearLayout addLayout = SavedData.LAYOUTLIST.getLast().getLayout();
        boolean isRightNull = currentCollumn%2!=0;

        addLayout.removeView(addLayout.getChildAt(isRightNull? 2:0));

        LinearLayout toDoListView = new LinearLayout(SavedData.currentContext);
        toDoListView.setLayoutParams(new LinearLayout.LayoutParams(isRightNull? 0 : BOX_SIZE, BOX_SIZE, isRightNull? 2 : 0));
        toDoListView.setOrientation(LinearLayout.VERTICAL);
        toDoListView.setGravity(Gravity.CENTER);
        toDoListView.setBackgroundColor(getColor(R.color.TaskBackground));
        toDoListView.setId(SavedData.toDoListID++);

        TextView title = new TextView(SavedData.currentContext);
        title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        title.setText(ListName);
        title.setAllCaps(true);
        title.setTextSize(TITLE_SIZE);
        title.setGravity(Gravity.CENTER);
        title.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
        title.setTextColor(getColor(R.color.TaskForeground));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            title.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        }

        toDoListView.addView(title);
        toDoListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toDoList = new Intent(getApplicationContext(), ToDoListActivity.class);
                toDoList.putExtra("Title", ListName);
                toDoList.putExtra("ID", view.getId());
                startActivity(toDoList);
            }
        });

        addLayout.addView(toDoListView);

        SavedData.LAYOUTLIST.getLast().setLayout(addLayout);
        if(isRightNull){
            SavedData.TASKSLIST.add(new LinkedList<>());
            SavedData.SETTINGS.add(new Settings());
        }

        if(SavedData.TASKSLIST.size() == 1){
            Toast.makeText(this, "Naciśnij na dodane pole aby wyświetlić listę!", Toast.LENGTH_LONG).show();
        }

        addToDoListAddingButton();
    }
    private void addToDoListAddingButton(){

        LinearLayout addLayout;

        if(currentCollumn % 2 != 0){
            addLayout = new LinearLayout(SavedData.currentContext);
            addLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CONTAINER_SIZE));
            addLayout.setPadding(0, PADDING_TOP, 0, PADDING_TOP);
            addLayout.setGravity(Gravity.CENTER);

            ImageView toDoListView = new ImageView(SavedData.currentContext);
            toDoListView.setLayoutParams(new LinearLayout.LayoutParams(BOX_SIZE, BOX_SIZE));
            toDoListView.setImageResource(R.drawable.new_to_do_list);
            toDoListView.setBackgroundColor(getColor(R.color.transparent));
            toDoListView.setId(SavedData.LAYOUTLIST.size());
            toDoListView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addToDoListDialog();
                }
            });

            addLayout.addView(toDoListView);

            SavedData.LAYOUTLIST.add(new LayoutList(addLayout));
            SavedData.TASKSLIST.add(new LinkedList<>());
            SavedData.SETTINGS.add(new Settings());

            outerLayout.addView(addLayout);
        }else{
            addLayout = SavedData.LAYOUTLIST.getLast().getLayout();
            addLayout.setPadding(PADDING_SIDE, PADDING_TOP, PADDING_SIDE, PADDING_TOP);

            View toDoListView = SavedData.LAYOUTLIST.getLast().getLayout().getChildAt(0);
            toDoListView.setLayoutParams(new LinearLayout.LayoutParams(0, BOX_SIZE, 2));

            View space = new View(SavedData.currentContext);
            space.setLayoutParams(new LinearLayout.LayoutParams(0,0, 1));

            ImageView toDoListView2 = new ImageView(SavedData.currentContext);
            toDoListView2.setLayoutParams(new LinearLayout.LayoutParams(0, BOX_SIZE, 2));
            toDoListView2.setImageResource(R.drawable.new_to_do_list);
            toDoListView2.setBackgroundColor(getColor(R.color.transparent));
            toDoListView2.setId(SavedData.LAYOUTLIST.size());
            toDoListView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addToDoListDialog();
                }
            });

            addLayout.addView(space);
            addLayout.addView(toDoListView2);

            SavedData.LAYOUTLIST.getLast().setLayout(addLayout);
//            SavedData.LAYOUTLIST.getLast().setRightToDoList(new LinkedList<>());
            SavedData.TASKSLIST.add(new LinkedList<>());
            SavedData.SETTINGS.add(new Settings());
        }
        currentCollumn++;
    }

    private void addToDoListDialog(){
        final Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.add_task_layout);

        EditText taskName = dialog.findViewById(R.id.TaskName);
        taskName.setHint("To Do List name:");
        Button submit = dialog.findViewById(R.id.submit);
        Button cancel = dialog.findViewById(R.id.cancel);

        submit.setOnClickListener(view -> {
            String inputText = taskName.getText().toString().trim();

            if(!inputText.isEmpty()){
                addToDoList(inputText);
                dialog.dismiss();
            }else{
                Toast.makeText(this, "Cannot add To Do List without a name!", Toast.LENGTH_SHORT).show();
            }

        });

        cancel.setOnClickListener(view -> {
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        dialog.show();
    }
}