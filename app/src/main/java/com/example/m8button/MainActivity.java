package com.example.m8button;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int random = (int)(Math.random() * 50 + 1);
    int intentos= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = findViewById(R.id.button);
        final Button btnRanking = findViewById(R.id.btnranking);
        final EditText number = findViewById(R.id.number);
        final TextView textView2 = findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(this,"HELLO DANIEL WORLD",Toast.LENGTH_LONG).show();
                    if (textView2.getText().equals("HAS ENCERTAT EL NUMERO " + number.getText())) {
                        int random = (int) (Math.random() * 50 + 1);
                        button.setText("BUTTON");
                        number.setText("");
                    } else {
                        if (Integer.parseInt(number.getText().toString()) == random) {
                            textView2.setText("HAS ENCERTAT EL NUMERO " + number.getText());
                            button.setText("TORNAR A COMENÇAR");
                            random = (int) (Math.random() * 50 + 1);
                            textView2.setText("");
                        } else {
                            intentos++;
                            String texto = "HAS FALLAT EL NUMERO(" + random + ") INTENT: " + intentos;
                            if (random < Integer.parseInt(String.valueOf(number.getText()))) {
                                texto = texto + "\n El numero es menor que " + number.getText();
                            } else {
                                texto = texto + "\n El numero es mayor que " + number.getText();
                            }
                            textView2.setText(texto);
                        }
                    }

            }
    });
        btnRanking.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RankingActivity.class));
            }
        });
    }

}
