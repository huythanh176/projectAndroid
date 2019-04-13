package com.example.huythanh.projectcontactmanager;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huythanh.model.Contact;

public class ManHinhTInNhan extends AppCompatActivity {
    TextView txtHienthi;
    Button btnLuu;
    EditText txtNoidung;
    Contact selectedContact=null;
    public  static  final int REQUEST_SMS=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_tin_nhan);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xyLygui();
            }
        });
    }

    private void xyLygui() {
        final SmsManager sms=  SmsManager.getDefault();
        Intent msgsent=new Intent("ACTION_MSG_SENT");
        final PendingIntent pendingIntent= PendingIntent.getBroadcast(this, 0, msgsent, 0);
        registerReceiver(new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent) {
                int result= getResultCode();
                String msg="SEND OK";
                if (result!=Activity.RESULT_OK){
                    msg="SEND fail";
                }
                Toast.makeText(ManHinhTInNhan.this, msg, Toast.LENGTH_LONG).show();
            }
        } , new IntentFilter("ACTION_MSG_SENT"));
        if(ContextCompat.checkSelfPermission(ManHinhTInNhan.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ManHinhTInNhan.this, new String[]{Manifest.permission.SEND_SMS}, REQUEST_SMS);

        } else {
            sms.sendTextMessage(selectedContact.getPhone(),null , txtNoidung.getText()+"", pendingIntent, null);
        }
      //  sms.sendTextMessage(selectedContact.getPhone(),null , txtNoidung.getText()+"", pendingIntent, null);
    }

    private void addControls() {
        txtHienthi=findViewById(R.id.txtNguoinhan);
        txtNoidung=findViewById(R.id.txtNoiDung);
        btnLuu=findViewById(R.id.btnGui);

        Intent intent=getIntent();
        selectedContact=(Contact)intent.getSerializableExtra("CONTACT");
        txtHienthi.setText(selectedContact.getTen()+"-"+selectedContact.getPhone());
    }
}
