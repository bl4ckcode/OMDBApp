package carlos.weatherapp.controllers;

import android.content.ContentValues;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

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
                        Gson gson = new Gson();
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

    public void inserirFilme(MovieModel movieModel) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(Colunas.IMDB_ID, movieModel.getImdbID());
        contentValues.put(Colunas.TITLE, movieModel.getTitle());
        contentValues.put(Colunas.YEAR, movieModel.getYear());
        contentValues.put(Colunas.POSTER, movieModel.getPoster());

        Utility.insert(Constantes.TABLE_FILMES_RESUMIDOS, contentValues, detalhesActivity);

        contentValues.put(Colunas.RELEASED, movieModel.getYear());
        contentValues.put(Colunas.RUNTIME, movieModel.getYear());
        contentValues.put(Colunas.GENRE, movieModel.getYear());
        contentValues.put(Colunas.DIRECTOR, movieModel.getYear());
        contentValues.put(Colunas.ACTORS, movieModel.getYear());
        contentValues.put(Colunas.PLOT, movieModel.getYear());
        contentValues.put(Colunas.AWARDS, movieModel.getYear());

        Utility.insert(Constantes.TABLE_FILMES, contentValues, detalhesActivity);

        detalhesActivity.invalidateOptionsMenu();
    }

    public void removerFilme(String imdbID) {
        Utility.remove(Colunas.IMDB_ID + " = " + imdbID, detalhesActivity);
        detalhesActivity.invalidateOptionsMenu();
    }
}