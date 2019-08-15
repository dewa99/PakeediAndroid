package io.jagoketik.pakeedi;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dezlum.codelabs.getjson.GetJson;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

import fr.bmartel.speedtest.SpeedTestReport;
import fr.bmartel.speedtest.SpeedTestSocket;
import fr.bmartel.speedtest.inter.ISpeedTestListener;
import fr.bmartel.speedtest.model.SpeedTestError;
import io.jagoketik.model.songs;

public class MainActivity extends AppCompatActivity {
    public TextView titles,artistPan,songTitle;
    Button play,prev,next;
    MediaPlayer player;
    JsonObject jsonData;
    String url,url1,quality;
    String artist,title,img_url;
    ImageView image_list;
    SeekBar seek_bar;
    private Handler mHandler = new Handler();
    SpeedTestSocket speedTestSocket;
    BigDecimal Kecepatan;

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
        seek_bar = findViewById(R.id.seekBar);

        speedTestSocket = new SpeedTestSocket();

        speedTestSocket.addSpeedTestListener(new ISpeedTestListener() {

            @Override
            public void onCompletion(SpeedTestReport report) {
                Kecepatan = report.getTransferRateBit();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Double a = Kecepatan.divide(BigDecimal.valueOf(1000000.0),BigDecimal.ROUND_UP).doubleValue();
                        String x = "";
                        if(a > 1.5){
                            x = "320";
                        }else if(a > 0.6){
                            x = "192";
                        }else{
                            x = "mp3";
                        }
                        final Toast toast = Toast.makeText(getBaseContext(),"report : " + x + " mbps" , Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
//                System.out.println("[COMPLETED] rate in octet/s : " + report.getTransferRateOctet());
//                System.out.println("[COMPLETED] rate in bit/s   : " + report.getTransferRateBit());
            }

            @Override
            public void onError(SpeedTestError speedTestError, String errorMessage) {
                // called when a download/upload error occur
            }

            @Override
            public void onProgress(float percent, SpeedTestReport report) {
                Kecepatan = report.getTransferRateBit();
            }
        });

        speedTestSocket.startDownload("ftp://speedtest.tele2.net/1MB.zip");

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
                    play.setBackground(getResources().getDrawable(R.drawable.ic_pause));
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
        player.reset();
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

//            if(jsonData.get("r320Url").getAsString()!= null){
            if(Kecepatan.divide(BigDecimal.valueOf(1000000.0),BigDecimal.ROUND_UP).doubleValue() > 1.5){
                url1 = jsonData.get("r320Url").getAsString();
                quality = "320";
            }
//            else if(jsonData.get("r192Url").getAsString()!=null){
            else if(Kecepatan.divide(BigDecimal.valueOf(1000000.0),BigDecimal.ROUND_UP).doubleValue() > 0.6){
                url1 = jsonData.get("r192Url").getAsString();
                quality = "192";
            }
//            else if(jsonData.get("mp3Url").getAsString()!=null){
            else {
                url1 = jsonData.get("mp3Url").getAsString();
                quality = "mp3";
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
            songTitle.setText(title.length() > 25 ? title.substring(0,25) : title);
            if(!img_url.isEmpty())
                Picasso.get().load(img_url).into(image_list);
            player.start();
            seek_bar.setMax(player.getDuration() / 1000);

            //Make sure you update Seekbar on UI thread
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(player != null){
                        int mCurrentPosition = player.getCurrentPosition() / 1000;
                        seek_bar.setProgress(mCurrentPosition);
                    }
                    mHandler.postDelayed(this, 1000);
                }
            });

            seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if(player != null && fromUser){
                        player.seekTo(progress * 1000);
                    }
                }
            });

            Toast.makeText(getBaseContext(), "quality : " + quality , Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void pause(){

    }

}
