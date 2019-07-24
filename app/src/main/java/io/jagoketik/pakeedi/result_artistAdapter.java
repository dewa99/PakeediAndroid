package io.jagoketik.pakeedi;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import io.jagoketik.pakeedi.MainActivity;

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
    public void onBindViewHolder(@NonNull final result_artistViewHolder result_artistViewHolder, int i) {
        final results_artist artis = artist.get(i);
        result_artistViewHolder.title.setText(artis.getName());
        Picasso.get().load(artis.getUrl().replace("\"","")).into((ImageView) result_artistViewHolder.a.findViewById(R.id.imageResult));
        result_artistViewHolder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String json = artis.getId().replace("\"","");
                String data = "http://165.22.97.31/artist/album/" + json;
                Bundle bundle = new Bundle();
                bundle.putString("url",data);
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment frag = new hasil_album();
                frag.setArguments(bundle);
//                activity.getSupportFragmentManager().beginTransaction().add(R.id.resul2, frag).commit();
//                if(mcx instanceof MainActivity){
//                    ((MainActivity)mcx).play(data);
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return artist.size();
    }

    class result_artistViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        View a;
        public result_artistViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.nameResult);
            a = itemView;
        }
    }

}
