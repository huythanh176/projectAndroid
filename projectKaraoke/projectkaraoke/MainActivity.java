package com.example.huythanh.projectkaraoke;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.huythanh.Model.Music;
import com.example.huythanh.adapter.MusicAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvBaiHatgoc;
    ArrayList<Music> dsbaihat;
    MusicAdapter musicAdapter;
    ListView lvBaihatyeuthich;
    ArrayList<Music> dsbaihatyeuthich;
    MusicAdapter musicyeuthichAdapter;
    TabHost tabHost;

    public static String DATABASE_NAME="Arirang.sqlite";
    String DB_PATH_SUFFIX="/databases/";
    public  static SQLiteDatabase database=null;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xyLySaoChepCSDLTuAssestVaoHeThongMobile();
        addControls();
        addEvents();
        xuLybaihatgoc();

    }
    private void xyLySaoChepCSDLTuAssestVaoHeThongMobile() {
        File dbFile= getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()){
            try {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Sao chep thanh cong", Toast.LENGTH_LONG).show();
            }
            catch (Exception e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }

    private void CopyDataBaseFromAsset() {
        try{
            InputStream Myinput=getAssets().open(DATABASE_NAME);
            String outfilename= getDatabasePath();
            File f = new File(getApplicationInfo().dataDir+DB_PATH_SUFFIX);
            if (!f.exists()){
                f.mkdir();
            }
            OutputStream myoutput= new FileOutputStream(outfilename);
            byte[] buffer= new byte[1024];
            int length;
            while ((length=Myinput.read(buffer))>0){
                myoutput.write(buffer, 0, length);
            }
            myoutput.flush();
            myoutput.close();
            Myinput.close();


        }
        catch(Exception e){
            Log.e( "LOi sao chao",e.toString());
        }
    }

    private String getDatabasePath(){
        return  getApplicationInfo().dataDir+DB_PATH_SUFFIX+DATABASE_NAME;
    }

    private void addEvents() {
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equalsIgnoreCase("t1"))
                {
                    xuLybaihatgoc();
                }
                else if(tabId.equalsIgnoreCase("t2")){
                    xuLybaihatyeuthich();
                }
            }
        });
    }

    private void xuLybaihatgoc() {
        database= openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor= database.query("ArirangSongList", null, null, null, null, null, null);
        dsbaihat.clear();
        while (cursor.moveToNext()){
            String mabh=cursor.getString(0);
            String tenbh=cursor.getString(1);
            String casi=cursor.getString(3);
            Integer yeuthich=cursor.getInt(5);
            Music music= new Music();
            music.setMa(mabh);
            music.setTenbaihat(tenbh);
            music.setCasy(casi);
            music.setThich(yeuthich==1);
            dsbaihat.add(music);

        }
        cursor.close();
        musicAdapter.notifyDataSetChanged();

    }

    private void xuLybaihatyeuthich() {
        database= openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor= database.query("ArirangSongList", null, "YEUTHICH=?", new String[]{"1"}, null, null, null);
        dsbaihatyeuthich.clear();
        while (cursor.moveToNext()){
            String mabh=cursor.getString(0);
            String tenbh=cursor.getString(1);
            String casi=cursor.getString(3);
            Integer yeuthich=cursor.getInt(5);
            Music music= new Music();
            music.setMa(mabh);
            music.setTenbaihat(tenbh);
            music.setCasy(casi);
            music.setThich(yeuthich==1);
            dsbaihatyeuthich.add(music);

        }
        cursor.close();
        musicyeuthichAdapter.notifyDataSetChanged();


    }

    private void addControls() {
         tabHost= findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec tab1= tabHost.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("",getResources().getDrawable(R.drawable.baihat));
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2= tabHost.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("",getResources().getDrawable(R.drawable.heart));
        tabHost.addTab(tab2);


        lvBaiHatgoc= findViewById(R.id.lvBaiHatGoc);
        dsbaihat= new ArrayList<>();
        musicAdapter= new MusicAdapter(MainActivity.this, R.layout.item, dsbaihat);
        lvBaiHatgoc.setAdapter(musicAdapter);

        lvBaihatyeuthich= findViewById(R.id.lvBaiHatYeuThich);
        dsbaihatyeuthich= new ArrayList<>();
        musicyeuthichAdapter= new MusicAdapter(MainActivity.this, R.layout.item, dsbaihatyeuthich);
        lvBaihatyeuthich.setAdapter(musicyeuthichAdapter);


    }


}
