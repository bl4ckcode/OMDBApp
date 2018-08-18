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
import carlos.weatherapp.models.Cidade;
import carlos.weatherapp.util.Constantes;
import carlos.weatherapp.util.Utility;

public class DetalhesController {
    private DetalhesActivity activity;

    public DetalhesController(DetalhesActivity activity) {
        this.activity = activity;
    }

    public void obterDetalhesCidade(final int idCidade) {
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = activity.getString(R.string.url_current_by_city_id, idCidade);

        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Cidade cidade = gson.fromJson(response, Cidade.class);
                        activity.preencherDetalhes(cidade);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(activity, activity.getString(R.string.mensagem_erro_requisicao_detalhes),
                                Toast.LENGTH_SHORT).show();
                        obterDetalhesCidade(idCidade);
                    }
                });

        queue.add(stringRequest);
    }

    public void atualizarFavorito(Cidade cidade, boolean favoritar) {
        int idCidade = cidade.getId();

        if (favoritar) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Colunas.ID_CIDADE, idCidade);
            contentValues.put(Colunas.NOME_CIDADE, cidade.getName());

            Utility.insert(Constantes.TABLE_CIDADE, contentValues, activity);
        } else {
            Utility.remove(Colunas.ID_CIDADE + " = " + idCidade, activity);
        }
    }
}