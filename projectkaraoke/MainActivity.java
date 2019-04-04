package com.example.huythanh.projectkaraoke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TabHost;

import com.example.huythanh.Model.Music;
import com.example.huythanh.adapter.MusicAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvBaiHatgoc;
    ArrayList<Music> dsbaihat;
    MusicAdapter musicAdapter;
    ListView lvBaihatyeuthich;
    ArrayList<Music> dsbaihatyeuthich;
    MusicAdapter musicyeuthichAdapter;
    TabHost tabHost;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
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

    }

    private void xuLybaihatyeuthich() {
        dsbaihatyeuthich.clear();
        for(Music music : dsbaihat)
        {
            if(music.isThich()==true){
                dsbaihatyeuthich.add(music);

            }
        }
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

        dsbaihat.add(new Music("5555", "Yeu lai tu dau", "Khac Viet", true));
        dsbaihat.add(new Music("6555", "Em cua ngay hom qua", "Son tung", true));
        dsbaihat.add(new Music("7555", "Tien quan ca", "Nam cao", false));
        dsbaihat.add(new Music("8555", "Hello", "Adele", false));
        musicAdapter.notifyDataSetChanged();
    }


}
