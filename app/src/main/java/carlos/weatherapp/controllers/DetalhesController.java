package carlos.weatherapp.controllers;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import carlos.weatherapp.R;
import carlos.weatherapp.activities.DetalhesActivity;
import carlos.weatherapp.models.Cidade;

public class DetalhesController {
    private DetalhesActivity activity;

    public DetalhesController(DetalhesActivity activity) {
        this.activity = activity;
    }

    public void obterDetalhesCidade(int idCidade) {
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = activity.getString(R.string.url_current_by_city_id, idCidade);

        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Cidade cidade = gson.fromJson(response, Cidade.class);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        queue.add(stringRequest);
    }
}
