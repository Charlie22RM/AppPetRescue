package com.example.petrescue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
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
        Picasso.get().load(perro.getImagenPath()).placeholder(R.drawable.baseline_insert_photo_24).into(imageViewPerro);

        nombreTextView.setText(perro.getNombre());
        razaTextView.setText(perro.getRaza());

        return convertView;
    }
}