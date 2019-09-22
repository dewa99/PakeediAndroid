package io.jagoketik.pakeedi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.jagoketik.helper.DataHelper;
import io.jagoketik.model.songs;

public class result_FavAdapter extends RecyclerView.Adapter<result_FavAdapter.result_SongsViewHolder> {
    private Context mcx;
    private List<io.jagoketik.model.songs> songs;
    private DataHelper dbHelper;

    public result_FavAdapter(Context mcx, List<io.jagoketik.model.songs> songs) {
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
    public void onBindViewHolder(@NonNull final result_SongsViewHolder result_songsViewHolder, int i) {
        final songs Songs = songs.get(i);
        result_songsViewHolder.title.setText(Songs.getTitle());
        result_songsViewHolder.artist.setText(Songs.getArtist());
//        if(!Songs.getImage().replace("\"","").isEmpty())
//            Picasso.get().load(Songs.getImage().replace("\"","")).into((ImageView) result_songsViewHolder.a.findViewById(R.id.gambarlagu));
        result_songsViewHolder.btnadd.setVisibility(View.GONE);
        result_songsViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mcx, ""+Songs.getUrl(), Toast.LENGTH_SHORT).show();
                if(mcx instanceof MainActivity){
                    ((MainActivity)mcx).play(Songs.getUrl());
                }
            }
        });
        result_songsViewHolder.btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new DataHelper(mcx);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                SQLiteStatement stmt = db.compileStatement("delete from playlist where title=?" );
                stmt.bindString(1, Songs.getArtist());
                stmt.execute();

                int newPosition = result_songsViewHolder.getAdapterPosition();
                songs.remove(newPosition);
                notifyItemRemoved(newPosition);
                notifyItemChanged(newPosition,songs.size());
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
        TextView btnadd;
        TextView btnDel;
        View a;
        public result_SongsViewHolder(@NonNull View itemView) {
            super(itemView);
            artist = itemView.findViewById(R.id.artistMusic);
            title = itemView.findViewById(R.id.musicTitle);
            card = (CardView) itemView.findViewById(R.id.itemList);
            btnadd = (TextView) itemView.findViewById(R.id.addplay);
            btnDel = (TextView) itemView.findViewById(R.id.deleteFav);
            a = itemView;
        }
    }
}
