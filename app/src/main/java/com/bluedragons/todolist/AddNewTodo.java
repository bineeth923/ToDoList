package com.bluedragons.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class AddNewTodo extends AppCompatActivity {
    private static EditText text_editText;

    CheckBox checkBox;
    public static String content;
    public static boolean done;
    public static final String TODO = "todo";
    public static Button cross;
    public static String test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_todo);
        text_editText = (EditText) findViewById(R.id.editText);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        Button button = (Button) findViewById(R.id.button2);
        cross = (Button) findViewById(R.id.cross);
        if (cross != null  || !((test = String.valueOf(text_editText.getText())).equals(""))) {
            cross.setVisibility(View.INVISIBLE);
        }


       text_editText.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

           }

           @Override
           public void afterTextChanged(Editable s) {
                if(s.length()!=0){
                    cross.setVisibility(View.VISIBLE);
                    cross.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            text_editText.setText("");
                            cross.setVisibility(View.INVISIBLE);
                        }
                    });
                }
           }
       });

        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Todo todo  = new Todo();
                    content = text_editText.getText().toString();
                    done = checkBox.isChecked();
                    todo.content = content;
                    todo.done = done;
                    if(content.isEmpty()){
                        text_editText.setError(getString(R.string.This_field_is_required));
                    }
                    else {
                        Intent intent = new Intent();
                        intent.putExtra(TODO, todo);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
            });
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.add_new_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id== R.id.Discard){
            setResult(RESULT_CANCELED);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
