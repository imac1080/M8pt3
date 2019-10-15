package com.example.m8button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
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
        if (MainActivity.nom!=null) {
            MainActivity.ListRanking.add(new Persona(MainActivity.nom, MainActivity.intentos));
            MainActivity.nom=null;
            MainActivity.intentos=0;
            OrdenarArray();
            //GuardarArray(RankingActivity.this);
        }
        ArrayAdapter<Persona> arrayAdapter = new ArrayAdapter<Persona>(
                this,
                android.R.layout.simple_list_item_1,
                MainActivity.ListRanking );

        lv.setAdapter(arrayAdapter);
    }
    public static void OrdenarArray(){
        boolean ordenado=true;
        Persona persona1 = new Persona("1",1);
        while (ordenado){
            ordenado=false;
            for(int i = 0; i < MainActivity.ListRanking.size()-1; i++)   {
                if (MainActivity.ListRanking.get(i).qualificacio > MainActivity.ListRanking.get(i+1).qualificacio){
                    persona1.qualificacio=MainActivity.ListRanking.get(i).qualificacio;
                    persona1.nom=MainActivity.ListRanking.get(i).nom;
                    MainActivity.ListRanking.remove(i);
                    MainActivity.ListRanking.add(i+1,persona1);
                    ordenado=true;
                }
            }
        }
    }
    public void GuardarArray(Context context){
        try {
            File f = new File(context.getFilesDir(),"data.dat");
            FileOutputStream fout = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            //oos.writeObject(MainActivity.ListRanking);
            oos.writeInt(9);
            oos.flush();
            fout.getFD().sync();
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*FileOutputStream outStream = null;
        try {
            File f = new File(context.getFilesDir(),"data.dat");
            outStream = new FileOutputStream(f);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);


            objectOutStream.writeObject(MainActivity.ListRanking);
            objectOutStream.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }*/
        /*try {
            File myFile = new File(getFilesDir(),"data.dat");
            myFile.createNewFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.write("replace this with your string");
            myOutWriter.close();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}



