package com.example.huythanh.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.huythanh.Model.Music;
import com.example.huythanh.projectkaraoke.R;

import java.util.List;

public class MusicAdapter extends ArrayAdapter<Music> {
    Activity context; int resource;
    List<Music> objects;


    public MusicAdapter( Activity context, int resource, List<Music> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater= this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);
        TextView txtMa= row.findViewById(R.id.txtMa);
        TextView txtTenbaihat= row.findViewById(R.id.txtTenbh);
        TextView txtCasy= row.findViewById(R.id.txtTenCasy);
        ImageButton btnlike= row.findViewById(R.id.btnlike);
        ImageButton btndislike= row.findViewById(R.id.btndislike);



        final Music music= this.objects.get(position);
        txtMa.setText(music.getMa());
        txtTenbaihat.setText(music.getTenbaihat());
        txtCasy.setText(music.getCasy());
        if(music.isThich()){
            btnlike.setVisibility(View.INVISIBLE);
            btndislike.setVisibility(View.VISIBLE);
        }
        else {
            btnlike.setVisibility(View.VISIBLE);
            btndislike.setVisibility(View.INVISIBLE);
        }
        btnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyThich(music);
            }
        });
        btndislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLykothich(music);
            }
        });


        return row;

    }

    private void xuLykothich(Music music) {
        music.setThich(false);
    }

    private void xuLyThich(Music music) {
       music.setThich(true);
    }


}
