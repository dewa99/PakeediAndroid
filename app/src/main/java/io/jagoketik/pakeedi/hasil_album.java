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
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.jagoketik.model.result_album;


public class hasil_album extends Fragment {
    RecyclerView recyclerView;
    result_AlbumAdapter adapter;
    List<result_album> list;
    String json;
    JsonObject jsonData;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hasil_album, container, false);
        recyclerView = view.findViewById(R.id.resultAlbum);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new org.solovyev.android.views.llm.LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        list = new ArrayList<>();

        Bundle bundle = getArguments();
        if(bundle !=null){
            json = bundle.getString("url");
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
            for (int i=0;i<jsonData.getAsJsonObject("albums").get("list_count").getAsInt();i++){
                String name = jsonData.getAsJsonObject("albums").get("items").getAsJsonArray().get(i).getAsJsonObject().get("name").getAsString();
                String id = jsonData.getAsJsonObject("albums").get("items").getAsJsonArray().get(i).getAsJsonObject().get("id").getAsString();
                String picUrl = jsonData.getAsJsonObject("albums").get("items").getAsJsonArray().get(i).getAsJsonObject().get("images").getAsJsonArray().get(0).getAsJsonObject().get("url").getAsString();

                list.add(
                        new result_album(
                                name,
                                id,
                                picUrl
                        )
                );
            }
        }
        adapter = new result_AlbumAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        return view;
    }


}
