package io.jagoketik.pakeedi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dezlum.codelabs.getjson.GetJson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.jagoketik.model.songs;


public class browse extends Fragment {
RecyclerView recyclerView;
List<io.jagoketik.model.songs> songs;
result_BrowseAdapter adapter;
JsonObject jsonData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_browse, container, false);
        recyclerView = v.findViewById(R.id.recArtist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        songs = new ArrayList<>();

        try {
            jsonData = new GetJson().AsJSONObject("http://165.22.97.31/music/top/lagu/33");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JsonArray lagu = jsonData.getAsJsonObject("tracks").getAsJsonArray("items");

        for (int i = 0;i<lagu.size();i++){
            String title = lagu.get(i).getAsJsonObject().get("name").getAsString();
            String artist = lagu.get(i).getAsJsonObject().getAsJsonArray("artist_list").get(0).getAsJsonObject().get("name").getAsString();
            String url = "http://165.22.97.31/music/songinfo/" + lagu.get(i).getAsJsonObject().get("id").getAsString();
            String image = lagu.get(i).getAsJsonObject().getAsJsonArray("images").get(2).getAsJsonObject().get("url").getAsString();

            songs.add(
                    new songs(
                            title,
                            artist,
                            url,
                            image
                    )
            );
        }
        adapter = new result_BrowseAdapter(getContext(),songs);
        recyclerView.setAdapter(adapter);
        return v;
    }


}
