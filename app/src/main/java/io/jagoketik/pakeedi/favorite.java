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
import io.jagoketik.model.songs;

import java.util.List;

import io.jagoketik.helper.DataHelper;


public class favorite extends Fragment {
    result_FavAdapter adapter;
    RecyclerView recyclerView;
    DataHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView = v.findViewById(R.id.recArtist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        dbHelper = new DataHelper(getContext());
        List<io.jagoketik.model.songs> playlist = dbHelper.getPlaylist();
        adapter = new result_FavAdapter(getContext(),playlist);
        recyclerView.setAdapter(adapter);
        return v;
    }

}
