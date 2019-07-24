package io.jagoketik.pakeedi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import io.jagoketik.model.results_artist;

public class result_artistAdapter extends RecyclerView.Adapter<result_artistAdapter.result_artistViewHolder> {
    private Context mcx;
    private List<results_artist> artist;

    public result_artistAdapter(Context mcx, List<results_artist> artist) {
        this.mcx = mcx;
        this.artist = artist;
    }

    @NonNull
    @Override
    public result_artistViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mcx);
        View view = inflater.inflate(R.layout.item_artist,null);
        return new result_artistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull result_artistViewHolder result_artistViewHolder, int i) {
        results_artist artis = artist.get(i);
        result_artistViewHolder.title.setText(artis.getName());
        result_artistViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return artist.size();
    }

    class result_artistViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        public result_artistViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.judullagu);
        }
    }
}
