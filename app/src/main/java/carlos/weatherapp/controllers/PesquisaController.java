package carlos.weatherapp.controllers;

import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import carlos.weatherapp.R;
import carlos.weatherapp.activities.PesquisarActivity;
import carlos.weatherapp.models.SearchModel;

public class PesquisaController {
    private PesquisarActivity pesquisarActivity;

    public PesquisaController(PesquisarActivity activity) {
        this.pesquisarActivity = activity;
    }

    public void buscarFilmes(final String title) {
        pesquisarActivity.updateLoading(false);

        RequestQueue queue = Volley.newRequestQueue(pesquisarActivity);
        String url = pesquisarActivity.getString(R.string.url_title_search, title);

        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                                .create();
                        SearchModel searchModelList = gson.fromJson(response, SearchModel.class);
                        pesquisarActivity.preencherLista(searchModelList.getSearch());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(pesquisarActivity, pesquisarActivity.getString(R.string.mensagem_erro_requisicao_pesquisa),
                                Toast.LENGTH_SHORT).show();
                        buscarFilmes(title);
                    }
                });

        queue.add(stringRequest);
    }
}
