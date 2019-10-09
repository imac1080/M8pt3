package com.example.m8button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RankingActivity extends AppCompatActivity {
    public ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        final Button btnTornar = findViewById(R.id.btnTornar);
        lv = (ListView) findViewById(R.id.lvRanking);
        btnTornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        MainActivity.ListRanking.add(new Persona("daniel",5));
        MainActivity.ListRanking.add(new Persona("josu",3));
        ArrayAdapter<Persona> arrayAdapter = new ArrayAdapter<Persona>(
                this,
                android.R.layout.simple_list_item_1,
                MainActivity.ListRanking );

        lv.setAdapter(arrayAdapter);
    }

}

