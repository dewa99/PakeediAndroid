package io.jagoketik.pakeedi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dezlum.codelabs.getjson.GetJson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.jagoketik.model.results;
import io.jagoketik.model.result_album;


public class result extends Fragment {

    RecyclerView recyclerView;
    resultAdapter adapter;
    List<results> resultList;
    List<result_album> resultAlbum;
    JsonObject jsonData;
    String json;
    ImageView profpic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_result, container, false);

        EditText value = v.findViewById(R.id.search);
        profpic = (ImageView) v.findViewById(R.id.imageResult);
        resultList = new ArrayList<>();
        resultAlbum = new ArrayList<>();

        recyclerView = v.findViewById(R.id.resultsArtist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        Bundle bundle = getArguments();
        if(bundle !=null){
            json = bundle.getString("json");
            try {
                jsonData = new GetJson().AsJSONObject(json);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            String result = jsonData.getAsJsonArray("album_list").get(0).getAsJsonObject().get("artist_name").toString();
        }

        if(jsonData.getAsJsonArray("singer_list") != null){
            for (int i = 0;i < jsonData.getAsJsonArray("singer_list").size();i++){
                String result = jsonData.getAsJsonArray("singer_list").get(i).getAsJsonObject().get("name").toString();
                String resultFix = result.replace("\"","");
                String urlImage = jsonData.getAsJsonArray("singer_list").get(i).getAsJsonObject().get("pic_url_tpl").getAsString();
                String id = jsonData.getAsJsonArray("singer_list").get(i).getAsJsonObject().get("doc_id").toString();

                resultList.add(
                   new results(
                           resultFix,
                           urlImage,
                           id)
           );
            }
        }
        else {
            Toast.makeText(getContext(), "Tidak Ada Data Artist", Toast.LENGTH_SHORT).show();
        }

        adapter = new resultAdapter(getContext(),resultList);
        recyclerView.setAdapter(adapter);
        return v;
    }
}
