package com.bluedragons.todolist;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener {
    private EditText username_editText ;
    private EditText password_editText ;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = (Button) findViewById(R.id.button);
        assert button != null;
        button.setOnClickListener(this);
        username_editText = (EditText) findViewById(R.id.Username);
        password_editText = (EditText) findViewById(R.id.Passowrd);


    }


    @Override
    public void onClick(View v) {
        String username_string = username_editText.getText().toString();
        String password_string = password_editText.getText().toString();
        boolean error = false;
        if(username_string.isEmpty()){
            username_editText.setError(getString(R.string.This_field_is_required));
            error = true;
        }

        if(TextUtils.isEmpty(password_string)){
            password_editText.setError(getString(R.string.This_field_is_required));
            error = true;
        }
        if(!error){
            login(username_string,password_string);
        }

    }

    private void login( String username_string,  String password_string)  {
        AsyncTask<String, Integer, Boolean> asyntask = new AsyncTask<String, Integer, Boolean>() {
            @Override
            protected Boolean doInBackground(String... params) {
              String  username_string = params[0];
              String  password_string = params[1];
                for (int i=0;i<100;i++) {
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    publishProgress(i);
                }
                return (username_string.equals("test") && password_string.equals("test"));

            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                button.setText("Authorising.. " + String.valueOf(values[0]));



            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                button.setEnabled(false);


            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                button.setEnabled(true);
                button.setText("LOGIN");
                if(aBoolean) {
                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), TodoList.class);
                    startActivity(intent);
                    finish();
                }

                else{
                    Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                }
            }
        };
        asyntask.execute(username_string,password_string);



    }
}
