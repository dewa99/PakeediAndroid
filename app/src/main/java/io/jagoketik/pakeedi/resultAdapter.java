package io.jagoketik.pakeedi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.module.AppGlideModule;
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
    public void onBindViewHolder(@NonNull resultViewHolder resultViewHolder, int position) {
        results Result = result.get(position);
        resultViewHolder.name.setText(Result.getTitle());
        ;
        try {
            InputStream in = (InputStream) new URL(Result.getUrlimage()).getContent();
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            resultViewHolder.imageprof.setImageBitmap(bitmap);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class resultViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView imageprof;
        public resultViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.judullagu);
            imageprof = (ImageView) itemView.findViewById(R.id.imageResult);

        }
    }
}
