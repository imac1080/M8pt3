package com.example.m8button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RankingActivity extends AppCompatActivity {
    public ListView lv;
    Uri photoURI;

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
        //if (MainActivity.nom!=null) {
        //MainActivity.ListRanking.add(new Persona(MainActivity.nom, MainActivity.intentos));
        //MainActivity.nom=null;
        //MainActivity.intentos=0;
        OrdenarArray();
        //GuardarArray(RankingActivity.this);
        //}

        ProducteAdapter adapter = new ProducteAdapter(this, ProducteAdapter.listaProductos);
        lv.setAdapter(adapter);
    }

    public static void OrdenarArray() {
        boolean ordenado = true;
        Persona persona1 = new Persona("1",1);
        while (ordenado) {
            ordenado = false;
            for (int i = 0; i < MainActivity.ListRanking.size() - 1; i++) {
                if (MainActivity.ListRanking.get(i).qualificacio > MainActivity.ListRanking.get(i + 1).qualificacio) {
                    persona1.qualificacio = MainActivity.ListRanking.get(i).qualificacio;
                    persona1.nombre = MainActivity.ListRanking.get(i).nombre;
                    MainActivity.ListRanking.remove(i);
                    MainActivity.ListRanking.add(i + 1, persona1);
                    ordenado = true;
                }
            }
        }
    }

//    private String imgPath;
//    static final int REQUEST_TAKE_PHOTO = 2;
//    public void tomarFoto(View view) {
//        final Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        // Ensure that there's a camera activity to handle the intent
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            // Create the File where the photo should go
//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//            } catch (IOException ex) {
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                photoURI = FileProvider.getUriForFile(this,
//                        "com.example.proyecto_bar_reinols_movil",
//                        photoFile);
//                imgPath = photoFile.getAbsolutePath();
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
//            }
//        }
//    }
//    String currentPhotoPath;
//    private File createImageFile() throws IOException {
//        // Create an image file name
//        String timeStamp = new SimpleDateFormat("yy-MM-dd").format(new Date());
//        //String imageFileName = user.getText().toString();
//        String imageFileName = timeStamp + "_" + Persona.class.getName() + "_";
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",         /* suffix */
//                storageDir      /* directory */
//        );
//
//        // Save a file: path for use with ACTION_VIEW intents
//        currentPhotoPath = image.getAbsolutePath();
//        return image;
//    }
}

class ProducteAdapter extends ArrayAdapter<Persona> {

    private Context mContext;
    static ArrayList<Persona> listaProductos = new ArrayList<Persona>();// guardar lista de productos

    /********CONSTRUCTOR********/
    public ProducteAdapter(@NonNull Context context, ArrayList<Persona> list) {
        super(context, 0, list);
        mContext = context;
        listaProductos = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.activity_productos_adapter, parent, false);
        }

        Persona productoSeleccionado = listaProductos.get(position);

        ImageView imagen = (ImageView) listItem.findViewById(R.id.fotoProducto);
        Bitmap bitmap = BitmapFactory.decodeByteArray(productoSeleccionado.getRutaImagen(), 0, productoSeleccionado.getRutaImagen().length);
        imagen.setImageBitmap(bitmap);

        TextView nombreProducto = (TextView) listItem.findViewById(R.id.nombreProducto);
        nombreProducto.setText(productoSeleccionado.getNombre());

        TextView precioProducto = (TextView) listItem.findViewById(R.id.precioProducto);
        precioProducto.setText(productoSeleccionado.getQualificacio() + "€");

        return listItem;
    }
}

class Persona {

    /********VARIABLES********/
    String nombre;
    float qualificacio;
    byte[] rutaImagen;

    /********SETTERS AND GETTERS********/

    public String getNombre() {
        return nombre;
    }

    public Persona(String nombre, float qualificacio) {
        this.nombre = nombre;
        this.qualificacio = qualificacio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getQualificacio() {
        return qualificacio;
    }

    public void setQualificacio(int qualificacio) {
        this.qualificacio = qualificacio;
    }

    public byte[] getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(byte[] rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

}

