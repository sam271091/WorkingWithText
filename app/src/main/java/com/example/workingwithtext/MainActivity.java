package com.example.workingwithtext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText edit;
    private TextView txtShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit  = (EditText)findViewById(R.id.editText);
        txtShow = (TextView)findViewById(R.id.textView);

    }


    public void read(View view){

        try {
            FileInputStream  fileInput = openFileInput("example.txt");
            InputStreamReader reader = new InputStreamReader(fileInput);
            BufferedReader buffer = new BufferedReader(reader);
            StringBuffer strBuffer = new StringBuffer();
            String lines;

            while ((lines = buffer.readLine())!= null){
                strBuffer.append(lines + "\n");
            }
            txtShow.setText(strBuffer.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void write(View view){

        String myTxt = edit.getText().toString();
        try {
            FileOutputStream fileOutput = openFileOutput("example.txt",MODE_PRIVATE);
            try {
                fileOutput.write(myTxt.getBytes());
                fileOutput.close();
                edit.setText("");
                Toast.makeText(MainActivity.this,"Текст сохранен",Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
