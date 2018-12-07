package carlos.weatherapp.controllers;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import carlos.weatherapp.R;
import carlos.weatherapp.activities.DetalhesActivity;
import carlos.weatherapp.interfaces.Colunas;
import carlos.weatherapp.models.MovieModel;
import carlos.weatherapp.util.Constantes;
import carlos.weatherapp.util.Utility;

public class DetalhesController {
    private DetalhesActivity detalhesActivity;

    public DetalhesController(DetalhesActivity activity) {
        this.detalhesActivity = activity;
    }

    public void obterDetalhesFilme(final String imbdId) {
        RequestQueue queue = Volley.newRequestQueue(detalhesActivity);
        String url = detalhesActivity.getString(R.string.url_get_movie, imbdId);

        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                                .create();
                        MovieModel movieModel = gson.fromJson(response, MovieModel.class);
                        detalhesActivity.preencherDetalhes(movieModel);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(detalhesActivity, detalhesActivity.getString(R.string.mensagem_erro_requisicao_detalhes),
                                Toast.LENGTH_SHORT).show();
                        obterDetalhesFilme(imbdId);
                    }
                });

        queue.add(stringRequest);
    }

    public void inserirFilme(final MovieModel movieModel) {
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                File file = new File(detalhesActivity.getFilesDir(), movieModel.getImdbID() + ".jpg");

                try (FileOutputStream out = new FileOutputStream(file)) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };

        Picasso.get().load(movieModel.getPoster()).into(target);

        ContentValues contentValues = new ContentValues();

        contentValues.put(Colunas.IMDB_ID, movieModel.getImdbID());
        contentValues.put(Colunas.TITLE, movieModel.getTitle());
        contentValues.put(Colunas.YEAR, movieModel.getYear());
        contentValues.put(Colunas.POSTER, movieModel.getPoster());

        Utility.insert(Constantes.TABLE_FILMES_RESUMIDOS, contentValues, detalhesActivity);

        contentValues.put(Colunas.RELEASED, movieModel.getReleased());
        contentValues.put(Colunas.RUNTIME, movieModel.getRuntime());
        contentValues.put(Colunas.GENRE, movieModel.getGenre());
        contentValues.put(Colunas.DIRECTOR, movieModel.getDirector());
        contentValues.put(Colunas.ACTORS, movieModel.getActors());
        contentValues.put(Colunas.PLOT, movieModel.getPlot());
        contentValues.put(Colunas.AWARDS, movieModel.getAwards());

        Utility.insert(Constantes.TABLE_FILMES, contentValues, detalhesActivity);

        detalhesActivity.invalidateOptionsMenu();
    }

    public void removerFilme(String imdbID) {
        Utility.remove(Colunas.IMDB_ID + " = " + imdbID, detalhesActivity);
        detalhesActivity.invalidateOptionsMenu();
    }
}