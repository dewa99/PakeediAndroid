package io.jagoketik.pakeedi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dezlum.codelabs.getjson.GetJson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Iterator;
import java.util.concurrent.ExecutionException;

public class songslist extends AppCompatActivity {
    JsonObject jsonObject;
    Intent in;
    String value;
    JsonElement data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songslist);
        in = getIntent();
        Bundle b = in.getExtras();
        value = b.getString("json");
        try {
            jsonObject = new GetJson().AsJSONObject(value);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JsonArray singerList = jsonObject.getAsJsonArray("singer_list");

        if(singerList.size()>0){

            for (int i = 0; i<singerList.size();i++){
                Log.d("json",singerList.get(i).getAsJsonObject().get("doc_id").getAsString());
            }
        }

    }
}
