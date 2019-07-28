package io.jagoketik.pakeedi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dezlum.codelabs.getjson.GetJson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.jagoketik.model.songs;

public class MainActivity extends AppCompatActivity {
    public TextView titles,artistPan,songTitle;
    Button play,prev,next;
    MediaPlayer player;
    JsonObject jsonData;
    String url,url1;
    String artist,title,img_url;
    ImageView image_list;

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
        image_list = findViewById(R.id.img_list);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.isPlaying()){
                    player.pause();
                    play.setBackground(getResources().getDrawable(R.drawable.ic_play));
                }
                else{
                    player.seekTo(player.getCurrentPosition());
                    player.start();
                }
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

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                player.stop();
                player.reset();
                play.setBackground(getResources().getDrawable(R.drawable.ic_play));
            }
        });

    }

    void play(String url){
        this.url = url;
        play.setBackground(getResources().getDrawable(R.drawable.ic_pause));
        if(player!=null)
        Controller();
    }
    void parseJson(){

        try {
            jsonData = new GetJson().AsJSONObject(url);

            artist = jsonData.get("msinger").getAsString();
            title = jsonData.get("msong").getAsString();
            if (jsonData.get("imgSrc").getAsString() != null){
                img_url = jsonData.get("imgSrc").getAsString();
            }else{
                img_url = jsonData.get("album_url").getAsString();
            }
            if(jsonData.get("r320Url").getAsString()!=null){
                url1 = jsonData.get("r320Url").getAsString();
            }
            else if(jsonData.get("r192Url").getAsString()!=null){
                url1 = jsonData.get("r192Url").getAsString();
            }
            else if(jsonData.get("mp3Url").getAsString()!=null){
                url1 = jsonData.get("mp3Url").getAsString();
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
            Picasso.get().load(img_url).into(image_list);
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    void pause(){

    }

}
