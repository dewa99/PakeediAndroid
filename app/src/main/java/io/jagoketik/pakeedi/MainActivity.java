package io.jagoketik.pakeedi;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dezlum.codelabs.getjson.GetJson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.jagoketik.model.songs;

public class MainActivity extends AppCompatActivity {
    public TextView titles,artistPan,songTitle;
    Button play,prev,next;
    MediaPlayer player;
    String jsonData;
    String url,url1;
    String artist,title;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_artist:
                    titles.setText(R.string.title_artist);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containermain,new artist())
                            .commit();

                    return true;
                case R.id.nav_fav:
                    titles.setText(R.string.title_favorite);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containermain, new favorite())
                            .commit();

                    return true;
                case R.id.nav_browse:
                    titles.setText(R.string.title_browse);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containermain, new browse())
                            .commit();

                    return true;
                case R.id.nav_search:
                    titles.setText(R.string.title_search);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.containermain, new search())
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titles = findViewById(R.id.title);
        play = findViewById(R.id.play);
        prev = findViewById(R.id.prev);
        next = findViewById(R.id.next);
        artistPan = findViewById(R.id.artistPanel);
        songTitle = findViewById(R.id.songTitle);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containermain,new artist())
                .commit();

        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);

    }

    void play(String url){
        this.url = url;
        Controller();
    }
    void parseJson(){

        try {
            jsonData = new GetJson().AsString(url);
            JsonObject reader = new JsonParser().parse(jsonData).getAsJsonObject();
            artist = reader.get("msinger").getAsString();
            title = reader.get("msong").getAsString();
            if(reader.get("r192Url").getAsString()==null){
                url1 = reader.get("r320Url").getAsString();
            }
            else if(reader.get("r192Url").getAsString()!=null){
                url1 = reader.get("r192Url").getAsString();
            }
            else if(reader.get("r320Url").getAsString()==null){
                url1 = reader.get("mp3Url").getAsString();
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    void Controller(){
        parseJson();
        if(!player.isPlaying()){
            audioPlay();
        }
        else {
            player.stop();
            player.reset();
            audioPlay();
        }

    }
    void audioPlay(){
        try {
            player.setDataSource(url1);
            player.prepare();
            artistPan.setText(artist);
            songTitle.setText(title);
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    void pause(){

    }

}
