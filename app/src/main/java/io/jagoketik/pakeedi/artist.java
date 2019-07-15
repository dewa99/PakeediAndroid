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

import java.util.ArrayList;
import java.util.List;

import io.jagoketik.model.songs;


public class artist extends Fragment {

    RecyclerView recyclerView;
    songAdapter adapter;
    List<songs> songList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_artist, container, false);

        songList = new ArrayList<>();

        recyclerView = v.findViewById(R.id.recArtist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new songAdapter(getContext(),songList);
        recyclerView.setAdapter(adapter);

        return v;
    }


}
