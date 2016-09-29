package com.example.mansi.datastorage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by MANSI on 29-09-2016.
 */
public class SharedPref extends AppCompatActivity {

    private Button button2,button3;
    private EditText todo;
    private TextView quote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_pref);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String name = sharedPref.getString("ToDo", "NO ToDo");
        quote=(TextView) findViewById(R.id.quote);
        todo=(EditText) findViewById(R.id.todo);
        System.out.println(name);
        quote.setText(name);


        button2=(Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quote.setText(todo.getText().toString());
                SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
                editor.putString("ToDo", quote.getText().toString());
                editor.commit();
                todo.setText(null,TextView.BufferType.EDITABLE);
                Toast.makeText(SharedPref.this,
                        "All the Best!", Toast.LENGTH_LONG).show();
            }
        });

        button3=(Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quote.setText("No ToDos for Today. ENJOY!!");
                todo.setText(null, TextView.BufferType.EDITABLE);
                SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
                editor.putString("ToDo", quote.getText().toString());
                editor.commit();
                Toast.makeText(SharedPref.this,
                        "Yayy!! Task is done", Toast.LENGTH_LONG).show();
            }
        });
    }

}
