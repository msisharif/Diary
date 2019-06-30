package com.example.imrankhan.mydiaryusingfile;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.note_id);
        save = (Button) findViewById(R.id.save_id);

        readFromFile();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String note_text = editText.getText().toString();

                if(note_text != null)
                {
                    try {
                        writeToFile(note_text);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please give some note", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void writeToFile(String note_text) throws IOException {

        FileOutputStream fileOutputStream = openFileOutput("Diary.txt", Context.MODE_PRIVATE);
        fileOutputStream.write(note_text.getBytes());
        fileOutputStream.close();
    }

    private void readFromFile()
    {
        try {
            FileInputStream fileInputStream = openFileInput("Diary.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            StringBuffer stringBuffer = new StringBuffer();

            while ((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line+"\n");
            }

            editText.setText(stringBuffer.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
