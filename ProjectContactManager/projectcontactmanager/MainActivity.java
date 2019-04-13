package com.example.huythanh.projectcontactmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.huythanh.adapter.ContactAdapter;
import com.example.huythanh.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText txtTen,txtPhone;
    Button btnLuu;
    ListView lvLuu;
    ArrayList<Contact> dscontact;
    ContactAdapter adapterContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xyLyLuu();
            }
        });
    }

    private void xyLyLuu() {
        Contact contact = new Contact(txtTen.getText().toString(), txtPhone.getText().toString());
        dscontact.add(contact);
        adapterContact.notifyDataSetChanged();
    }

    private void addControls() {
        txtTen=findViewById(R.id.txtTen);
        txtPhone=findViewById(R.id.txtPhone);
        lvLuu=findViewById(R.id.lvDanhba);
        btnLuu=findViewById(R.id.btnLuu);
        dscontact= new ArrayList<>();
        adapterContact= new ContactAdapter(MainActivity.this, R.layout.item, dscontact);
        lvLuu.setAdapter(adapterContact);
    }
}
