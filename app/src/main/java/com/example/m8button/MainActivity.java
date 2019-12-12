package com.example.m8button;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int random = (int)(Math.random() * 50 + 1);
    static int intentos= 0;
    static String nom=null;
    static TextView textView2;
    static ArrayList<Persona> ListRanking = new ArrayList<Persona>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = findViewById(R.id.button);
        final Button btnRanking = findViewById(R.id.btnranking);
        final EditText number = findViewById(R.id.textUserRanking);
        textView2 = findViewById(R.id.textView);
        //LeerArray(MainActivity.this);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                //Log.d("-------","click");
                String textoProva= number.getText().toString();
                if(TextUtils.isEmpty(textoProva)) {
                    number.setError("No pots deixar-ho buit");
                    return;
                }
                    if (button.getText().equals("TORNAR A COMENÇAR")) {
                        int random = (int) (Math.random() * 50 + 1);
                        button.setText("BUTTON");
                        number.setText("");
                    } else {
                        intentos++;
                        if (Integer.parseInt(number.getText().toString()) == random) {
                            textView2.setText("HAS ENCERTAT EL NUMERO " + number.getText());
                            button.setText("TORNAR A COMENÇAR");
                            random = (int) (Math.random() * 50 + 1);
                            textView2.setText("");
                            final Dialog dialog = new Dialog(MainActivity.this);
                            dialog.setContentView(R.layout.dialog_signin);
                            dialog.setTitle("Title");
                            Button button = (Button) dialog.findViewById(R.id.button);
                            button.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    EditText edit=(EditText)dialog.findViewById(R.id.textUserRanking);
                                    nom=edit.getText().toString();
                                    if (IsOnArray(nom)==true){
                                        if (TextUtils.isEmpty(nom)){
                                            edit.setError("No pots deixar-ho buit");
                                            return;
                                        }else {
                                            ListRanking.add(new Persona(nom, intentos));
                                            nom = null;
                                            intentos = 0;
                                            GuardarArray(MainActivity.this);
                                            dialog.dismiss();
                                        }
                                    }else{
                                        Toast.makeText(MainActivity.this, "el nom "+nom+" ja esta utilitzat!", Toast.LENGTH_LONG).show();
                                    }

                                }
                            });
                            Button buttonCancel = (Button) dialog.findViewById(R.id.btnranking);
                            buttonCancel.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });
                            dialog.show();
                        } else {
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
    public static boolean IsOnArray(String nombreTest){
        for(int i = 0; i < ListRanking.size(); i++)   {
            if (nombreTest.equals(ListRanking.get(i).getNombre())){
                return false;
            }
        }
        return true;
    }

    public void LeerArray(Context context){
        try {
            File f = new File(context.getFilesDir(),"data.dat");
            if (f.exists()){
                FileInputStream fout = new FileInputStream(f);
                ObjectInputStream oos = new ObjectInputStream(fout);
                ListRanking.clear();
                int i=0;
                while (oos.available()>0)  {
                    String s = oos.readUTF();
                    int edat2 = oos.readInt();
                    ListRanking.add(new Persona(s,edat2));
                    Log.d("-------","persona "+i+" puesta");
                    i++;
                }
                fout.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void GuardarArray(Context context) {
        try {
            File f = new File(context.getFilesDir(), "data.dat");
            FileOutputStream fout = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            for(int i = 0; i < ListRanking.size(); i++)   {
               oos.writeUTF(ListRanking.get(i).nom);
                oos.writeInt(ListRanking.get(i).qualificacio);
                Log.d("-------","persona "+i+" puesta long de array: "+ListRanking.size());
            }
            oos.flush();
            fout.getFD().sync();
            fout.close();
            startActivity(new Intent(MainActivity.this, RankingActivity.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
