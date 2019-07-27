package io.jagoketik.pakeedi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.jagoketik.model.results_artist;
import io.jagoketik.model.result_album;

public class result_AlbumAdapter extends RecyclerView.Adapter<result_AlbumAdapter.result_AlbumAdapterViewHolder> {

    private Context mcx;
    private List<result_album> album;


    public result_AlbumAdapter(Context mcx, List<result_album> album) {
        this.mcx = mcx;
        this.album = album;
    }

    @NonNull
    @Override
    public result_AlbumAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mcx);
        View view = inflater.inflate(R.layout.item_artist,null);
        return new result_AlbumAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull result_AlbumAdapterViewHolder result_albumAdapterViewHolder, int i) {
        result_album iniAlbum = album.get(i);
        result_albumAdapterViewHolder.name.setText(iniAlbum.getTitle());
        Picasso.get()
                .load(iniAlbum.getImgSrc())
                .placeholder(R.drawable.music_placeholder)
                .error(R.drawable.music_placeholder)
                .into((ImageView) result_albumAdapterViewHolder.a.findViewById(R.id.imageResult));
        result_albumAdapterViewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return album.size();
    }

    class result_AlbumAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        View a;
        public result_AlbumAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameResult);
            a = itemView;

        }
    }
}
