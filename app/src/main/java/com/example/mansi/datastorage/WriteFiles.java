package com.example.mansi.datastorage;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by MANSI on 29-09-2016.
 */
public class WriteFiles extends AppCompatActivity {


    private TextView quote;
    private EditText todo;
    private Button button2, button3, button5, button6;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_files);
        int permission = ActivityCompat.checkSelfPermission(WriteFiles.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    WriteFiles.this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
        quote = (TextView) findViewById(R.id.quote);
        todo = (EditText) findViewById(R.id.todo);

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filename = "internal.txt";
                FileInputStream fin = null;
                try {
                    fin = openFileInput(filename);
                    int c;
                    String temp = "";
                    while ((c = fin.read()) != -1) {
                        temp = temp + Character.toString((char) c);
                    }
                    quote.setText(temp);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String filename = "internal.txt";
               FileOutputStream outputStream;
               String string = todo.getText().toString();
               try {
                   outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                   outputStream.write(string.getBytes());
                   outputStream.close();
                   Toast.makeText(WriteFiles.this, "Written to Internal Storage", Toast.LENGTH_LONG).show();

               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
        });


        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isExternalStorageReadable()) {
                    File file = new File(getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "external1.txt");
                    FileInputStream fin = null;
                    try {
                        fin = new FileInputStream(file);
                        int c;
                        String temp = "";
                        while ((c = fin.read()) != -1) {
                            temp = temp + Character.toString((char) c);
                        }
                        quote.setText(temp);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = todo.getText().toString();
                if (isExternalStorageWritable()) {
                    File file = new File(getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "external1.txt");
                    try {
                        FileOutputStream out = new FileOutputStream(file);
                        out.write(string.getBytes());
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (isExternalStorageWritable()) {
                    String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
                    File path = new File(baseDir, "ext2.txt");
                    try {
                        FileOutputStream out = new FileOutputStream(path);
                        out.write(string.getBytes());
                        out.close();
//                        Toast.makeText(WriteFiles.this, "Written to External Storage", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (isExternalStorageWritable()) {
                    File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "ext3.txt");

                    try {
                        FileOutputStream out = new FileOutputStream(file);
                        out.write(string.getBytes());
                        out.close();
                        Toast.makeText(WriteFiles.this, "Written to External Storage", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


}
