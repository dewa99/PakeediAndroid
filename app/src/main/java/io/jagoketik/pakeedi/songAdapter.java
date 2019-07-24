package io.jagoketik.pakeedi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import io.jagoketik.model.songs;

public class songAdapter extends RecyclerView.Adapter<songAdapter.songViewHolder> {
    private Context mcx;
    private List<io.jagoketik.model.songs> songs;


    public songAdapter(Context mcx, List<songs> songs) {
        this.mcx = mcx;
        this.songs = songs;
    }

    @NonNull
    @Override
    public songViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mcx);
        View view = inflater.inflate(R.layout.item_artist,null);
        return new songViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull songViewHolder holder, int position) {
        songs Songs = songs.get(position);
        holder.title.setText(Songs.getTitle());

    }

    @Override
    public int getItemCount() {
        return songs.size();
    }


    class songViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        public songViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.artistPanel);
        }
    }
}
