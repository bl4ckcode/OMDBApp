package carlos.weatherapp.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import carlos.weatherapp.R;
import carlos.weatherapp.controllers.MainController;
import carlos.weatherapp.models.ShortMovieModel;

public class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>  {
    public interface OnItemClicked {
        void onCarouselItemClicked(String imdbId);
    }

    private List<ShortMovieModel> movieArrayList = new ArrayList<>();
    private List<Bitmap> data = new ArrayList<>();
    private OnItemClicked onItemClicked;
    private MainController mainController;

    public CarouselAdapter(MainController mainController, OnItemClicked onItemClicked) {
        this.mainController = mainController;
        this.onItemClicked = onItemClicked;
    }

    @NonNull
    @Override
    public CarouselAdapter.CarouselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CarouselAdapter.CarouselViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_carousel, parent, false));
    }

    class CarouselViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;

        CarouselViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.adapter_carousel_imageView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final CarouselAdapter.CarouselViewHolder holder, int position) {
        holder.image.setImageBitmap(data.get(position));
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CarouselAdapter.this.onItemClicked.onCarouselItemClicked(
                        movieArrayList.get(holder.getAdapterPosition()).getImdbID());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setMovieArrayList(List<ShortMovieModel> movieArrayList) {
        data.clear();
        this.movieArrayList = movieArrayList;

        for (ShortMovieModel shortMovieModel : movieArrayList) {
            this.data.add(BitmapFactory.decodeFile(mainController.retornarCaminhoImagem(shortMovieModel.getImdbID())));
        }
    }
}
