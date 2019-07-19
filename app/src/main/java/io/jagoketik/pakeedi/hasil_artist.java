package io.jagoketik.pakeedi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dezlum.codelabs.getjson.GetJson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.jagoketik.model.results_artist;

public class hasil_artist extends Fragment {
    RecyclerView recyclerView;
    result_artistAdapter adapter;
    List<results_artist> list;
    String json;
    JsonObject jsonData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_hasil_artist, container, false);
        recyclerView = v.findViewById(R.id.results);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();

        Bundle bundle = getArguments();
        if(bundle !=null){
            json = bundle.getString("artist");
            try {
                jsonData = new GetJson().AsJSONObject(json);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
////            String result = jsonData.getAsJsonArray("album_list").get(0).getAsJsonObject().get("artist_name").toString();
        }
        if(jsonData!=null){
            for (int i=0;i<jsonData.getAsJsonObject("tracks").get("list_count").getAsInt();i++){
                String name = jsonData.getAsJsonObject("tracks").getAsJsonArray("items").get(i).getAsJsonObject().get("name").toString();
                String id = jsonData.getAsJsonObject("tracks").getAsJsonArray("items").get(i).getAsJsonObject().get("id").toString();

                list.add(
                        new results_artist(
                                name,
                                id
                        )
                );

            }


        }

        adapter = new result_artistAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);

        return v;

    }

}
