package io.jagoketik.pakeedi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import io.jagoketik.model.results;

public class resultAdapter extends RecyclerView.Adapter<resultAdapter.resultViewHolder> {
    private Context mcx;
    private List<io.jagoketik.model.results> result;

    public resultAdapter(Context mcx, List<results> result) {
        this.mcx = mcx;
        this.result = result;
    }

    @NonNull
    @Override
    public resultViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mcx);
        View view = inflater.inflate(R.layout.item_artist,null);
        return new resultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final resultViewHolder resultViewHolder, int position) {
        final results Result = result.get(position);
        resultViewHolder.name.setText(Result.getTitle());
        Picasso.get().load(Result.getUrlimage().replace("\"" , "")).into((ImageView) resultViewHolder.a.findViewById(R.id.imageResult));
        resultViewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                String data = Result.getId().replace("\"","");
                bundle.putString("artist","http://165.22.97.31/artist/list/"+data);
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment frag = new hasil_artist();
                frag.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.containermain, frag).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class resultViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        View a;
        public resultViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.judullagu);
            a = itemView;

        }
    }
}
