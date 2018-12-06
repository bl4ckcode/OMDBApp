package carlos.weatherapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import carlos.weatherapp.R;
import carlos.weatherapp.models.ShortMovieModel;


public class ListaFilmesAdapter extends RecyclerView.Adapter<ListaFilmesAdapter.ListaFilmesViewholder> {
    public interface OnItemClicked {
        void onRecyclerViewItemClicked(int positon);
    }

    private List<ShortMovieModel> movieArrayList = new ArrayList<>();
    private OnItemClicked onItemClicked;

    public ListaFilmesAdapter(OnItemClicked onItemClicked) {
        this.onItemClicked = onItemClicked;
    }

    class ListaFilmesViewholder extends RecyclerView.ViewHolder {
        TextView textViewFilme;

        ListaFilmesViewholder(View itemView) {
            super(itemView);

            textViewFilme = itemView.findViewById(R.id.tv_adapter_filmes);
        }
    }

    @NonNull
    @Override
    public ListaFilmesAdapter.ListaFilmesViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListaFilmesViewholder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_filmes, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ListaFilmesAdapter.ListaFilmesViewholder holder, int position) {
        ShortMovieModel shortMovieModel = movieArrayList.get(position);
        String title = shortMovieModel.getTitle() + "(" + shortMovieModel.getYear() + ")";
        holder.textViewFilme.setText(title);
        holder.textViewFilme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListaFilmesAdapter.this.onItemClicked.onRecyclerViewItemClicked(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public void setMovieArrayList(List<ShortMovieModel> movieArrayList) {
        this.movieArrayList = movieArrayList;
    }
}
