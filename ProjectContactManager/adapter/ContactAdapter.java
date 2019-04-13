package com.example.huythanh.adapter;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huythanh.model.Contact;
import com.example.huythanh.projectcontactmanager.ManHinhTInNhan;
import com.example.huythanh.projectcontactmanager.R;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {
    Activity context;
    int resource;
    List<Contact> objects;
    public  static  final int REQUEST_CALL=1;
    public ContactAdapter(Activity context, int resource,  List<Contact> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }


    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row= inflater.inflate(this.resource, null);

        TextView txtTen= row.findViewById(R.id.txtTeni);
        TextView txtPhone=row.findViewById(R.id.txtPhonei);
        ImageButton btnCall=row.findViewById(R.id.btnCall);
        ImageButton btnSMS=row.findViewById(R.id.btnSMS);
        ImageButton btnDelete=row.findViewById(R.id.btnDelete);

        final  Contact contact= this.objects.get(position);
        txtTen.setText(contact.getTen());
        txtPhone.setText(contact.getPhone());
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xyLygoi(contact);
            }
        });
        btnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xyLySMS(contact);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyxoa(contact);
            }
        });
        return row;
    }

    private void xuLyxoa(Contact contact) {
        this.remove(contact);
    }

    private void xyLySMS(Contact contact) {
        Intent intent= new Intent(this.context,ManHinhTInNhan.class);
        intent.putExtra("CONTACT", contact);
        this.context.startActivity(intent);
    }

    private void xyLygoi(Contact contact) {
        Intent intent= new Intent(Intent.ACTION_CALL);
        Uri uri=Uri.parse("tel:"+contact.getPhone());
        intent.setData(uri);
        if(ContextCompat.checkSelfPermission(this.context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this.context, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);

        } else {
            this.context.startActivity(intent);
        }



    }
}
