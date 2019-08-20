package io.jagoketik.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.jagoketik.model.songs;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "ediplaylist.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table playlist(no integer primary key, singer text null, title text null, url text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
//        sql = "INSERT INTO playlist (no, singer, title, url) VALUES ('1', 'Nella Kharisma', 'Ada Gajah Dibalik Batu (New Original) - Single', 'http://165.22.97.31/music/songinfo/RfX7h31wR99acK5chP8Ncw==');";
//        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<songs> getPlaylist() {
        List<songs> playlist = new ArrayList<>();
        String selectQuery = "SELECT  * FROM playlist";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(0);
                String artist = cursor.getString(1);
                String title = cursor.getString(2);
                String url = cursor.getString(3);

                songs song = new songs(artist,title,url,null);
                playlist.add(song);
            } while (cursor.moveToNext());
        }

        db.close();
        return playlist;
    }
}
