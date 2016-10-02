package com.example.mansi.datastorage;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Created by MANSI on 29-09-2016.
 */
public class Database extends AppCompatActivity {

    private EditText todo, editText, editText2, editText3;
    private Button button2, button3, button4, button7;
    String s1,s2,s3,s4;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database);

        DBHandler db = new DBHandler(this);

        todo = (EditText) findViewById(R.id.todo);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler db = new DBHandler(Database.this);
                s1 = todo.getText().toString();
                s2 = editText.getText().toString();
                s3 = editText2.getText().toString();
                s4 = editText3.getText().toString();

                Records record = new Records(s1,s2,s3,s4);
                db.addRecord(record);
                todo.setText("");
                editText.setText("");
                editText2.setText("");
                editText3.setText("");
                Toast.makeText(Database.this, "Record Added!", Toast.LENGTH_LONG).show();
            }
        });

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler db = new DBHandler(Database.this);
                s1 = todo.getText().toString();
                s2 = editText.getText().toString();
                s3 = editText2.getText().toString();
                s4 = editText3.getText().toString();

                Records record = new Records(s1,s2,s3,s4);
                db.updateRecord(record);
                todo.setText("");
                editText.setText("");
                editText2.setText("");
                editText3.setText("");
                Toast.makeText(Database.this, "Record Updated!", Toast.LENGTH_LONG).show();
            }
        });

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler db = new DBHandler(Database.this);
                s1 = todo.getText().toString();
                Records record = db.getRecord(s1);
                todo.setText(record.getRoll());
                editText.setText(record.getName());
                editText2.setText(record.getStream());
                editText3.setText(record.getCgpa());
            }
        });

        button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandler db = new DBHandler(Database.this);
                s1 = todo.getText().toString();
                s2 = editText.getText().toString();
                s3 = editText2.getText().toString();
                s4 = editText3.getText().toString();
                id = Integer.parseInt(s1);

                Records record = new Records(s1,s2,s3,s4);
                db.deleteRecord(record);
                todo.setText("");
                editText.setText("");
                editText2.setText("");
                editText3.setText("");
                Toast.makeText(Database.this, "Record Deleted!", Toast.LENGTH_LONG).show();
            }
        });

    }

}
