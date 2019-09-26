package com.example.m8button;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int intentos= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int random = (int)(Math.random() * 50 + 1);
        final Button button = findViewById(R.id.button);
        final EditText number = findViewById(R.id.number);
        final TextView textView2 = findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(this,"HELLO DANIEL WORLD",Toast.LENGTH_LONG).show();
                if (Integer.parseInt(number.getText().toString()) == random){
                    textView2.setText("HAS ENCERTAT EL NUMERO "+number.getText());
                }else {
                    intentos++;
                    textView2.setText("HAS FALLAT EL NUMERO("+random+") INTENT: "+intentos);
                }
            }
    });


    }

}
