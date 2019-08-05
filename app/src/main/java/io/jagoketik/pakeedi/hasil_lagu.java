package io.jagoketik.pakeedi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dezlum.codelabs.getjson.GetJson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.jagoketik.model.songs;


public class hasil_lagu extends Fragment {
result_SongsAdapter adapter;
List<io.jagoketik.model.songs> songs;
String key;
RecyclerView recyclerView;
JsonObject jsonData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hasil_lagu, container, false);

        recyclerView = v.findViewById(R.id.resultSongs);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        songs = new ArrayList<>();

        Bundle bundle = getArguments();
        if(bundle !=null){
            key = bundle.getString("key");
            try {
                jsonData = new GetJson().AsJSONObject("http://165.22.97.31/music/search/" + key);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

            for (int i = 0;i<jsonData.getAsJsonArray("itemlist").size();i++){
                String title = jsonData.getAsJsonArray("itemlist").get(i).getAsJsonObject().get("info1").getAsString();
                String artist = jsonData.getAsJsonArray("itemlist").get(i).getAsJsonObject().get("info2").getAsString();
                String url = jsonData.getAsJsonArray("itemlist").get(i).getAsJsonObject().get("m_url").getAsString();

                songs.add(
                        new songs(
                                title,
                                artist,
                                url,
                                url
                        )
                );
            }





        adapter = new result_SongsAdapter(getContext(),songs);
        recyclerView.setAdapter(adapter);
        return v;
    }


}
