package com.example.petrescue;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class PerroAdapter extends ArrayAdapter<Perro> {
    private Context context;
    private int resource;
    private List<Perro> perrosList;

    public PerroAdapter(Context context, int resource, List<Perro> perrosList) {
        super(context, resource, perrosList);
        this.context = context;
        this.resource = resource;
        this.perrosList = perrosList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        Perro perro = perrosList.get(position);

        ImageView imageViewPerro = convertView.findViewById(R.id.imageView_perro);
        TextView nombreTextView = convertView.findViewById(R.id.textView_nombre_perro);
        TextView razaTextView = convertView.findViewById(R.id.textView_raza_perro);

        // Cargar la imagen utilizando Picasso
        //Picasso.get().load(perro.getImagenPath()).placeholder(R.drawable.baseline_insert_photo_24).into(imageViewPerro);
        File imageFile = new File(perro.getImagenPath());
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        imageViewPerro.setImageBitmap(bitmap);
        nombreTextView.setText(perro.getNombre());
        razaTextView.setText(perro.getRaza());

        Button verMasButton = convertView.findViewById(R.id.button_ver_mas);
        verMasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InfoPerrosActivity.class);
                intent.putExtra("perro_id", perro.getId());
                intent.putExtra("perro_nombre", perro.getNombre());
                intent.putExtra("perro_raza", perro.getRaza());
                // Agrega otros extras según sea necesario
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}