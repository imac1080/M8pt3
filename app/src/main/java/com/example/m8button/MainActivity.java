package com.example.m8button;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void boton(View view){
        Toast.makeText(this,"HELLO DANIEL WORLD",Toast.LENGTH_LONG).show();
    }

}
