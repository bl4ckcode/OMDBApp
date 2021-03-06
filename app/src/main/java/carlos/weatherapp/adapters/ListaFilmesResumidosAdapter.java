package carlos.weatherapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import carlos.weatherapp.R;
import carlos.weatherapp.models.ShortMovieModel;

public class ListaFilmesResumidosAdapter extends RecyclerView.Adapter<ListaFilmesResumidosAdapter.ListaFilmesResumidosViewholder> {
    public interface OnItemClicked {
        void onRecyclerViewItemClicked(ShortMovieModel shortMovieModel);
    }

    private List<ShortMovieModel> shortMovieModels = new ArrayList<>();
    private OnItemClicked onItemClicked;

    public ListaFilmesResumidosAdapter(OnItemClicked onItemClicked) {
        this.onItemClicked = onItemClicked;
    }

    class ListaFilmesResumidosViewholder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        ImageView imageView;
        TextView textView;

        ListaFilmesResumidosViewholder(View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.adapter_filmes_resumidos_ll);
            imageView = itemView.findViewById(R.id.adapter_filmes_resumidos_imView);
            textView = itemView.findViewById(R.id.adapter_filmes_resumidos_txtView);
        }
    }

    @NonNull
    @Override
    public ListaFilmesResumidosAdapter.ListaFilmesResumidosViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListaFilmesResumidosViewholder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_filmes_resumidos, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull final ListaFilmesResumidosAdapter.ListaFilmesResumidosViewholder holder, int position) {
        ShortMovieModel shortMovieModel = shortMovieModels.get(position);
        String title = shortMovieModel.getTitle() + " (" + shortMovieModel.getYear() + ")";

        holder.textView.setText(title);

        Picasso.get().load(shortMovieModel.getPoster()).into(holder.imageView);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListaFilmesResumidosAdapter.this.onItemClicked.onRecyclerViewItemClicked(
                        shortMovieModels.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return shortMovieModels.size();
    }

    public void setShortMovieModels(List<ShortMovieModel> shortMovieModels) {
        this.shortMovieModels = shortMovieModels;
    }
}
