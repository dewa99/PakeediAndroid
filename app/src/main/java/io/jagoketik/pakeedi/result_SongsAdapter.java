package io.jagoketik.pakeedi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.jagoketik.model.songs;

public class result_SongsAdapter extends RecyclerView.Adapter<result_SongsAdapter.result_SongsViewHolder> {
    private Context mcx;
    private List<io.jagoketik.model.songs> songs;

    public result_SongsAdapter(Context mcx, List<io.jagoketik.model.songs> songs) {
        this.mcx = mcx;
        this.songs = songs;
    }

    @NonNull
    @Override
    public result_SongsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mcx);
        View view = inflater.inflate(R.layout.items,viewGroup,false);
        return new result_SongsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull result_SongsViewHolder result_songsViewHolder, int i) {
        final songs Songs = songs.get(i);
        result_songsViewHolder.title.setText(Songs.getTitle());
        result_songsViewHolder.artist.setText(Songs.getArtist());
        result_songsViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               33333
                Toast.makeText(mcx, ""+Songs.getUrl(), Toast.LENGTH_SHORT).show();
                if(mcx instanceof MainActivity){
                    ((MainActivity)mcx).play(Songs.getUrl());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    class result_SongsViewHolder extends RecyclerView.ViewHolder {
        TextView artist,title;
        CardView card;
        public result_SongsViewHolder(@NonNull View itemView) {
            super(itemView);
            artist = itemView.findViewById(R.id.artistMusic);
            title = itemView.findViewById(R.id.musicTitle);
            card = (CardView) itemView.findViewById(R.id.itemList);
        }
    }
}
