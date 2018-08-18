package carlos.weatherapp.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import carlos.weatherapp.activities.PesquisarActivity;
import carlos.weatherapp.models.Cidade;

/**
 * Created by Carlos on 17/08/2018.
 */

public class PesquisaController {
    private PesquisarActivity activity;

    public PesquisaController(PesquisarActivity activity) {
        this.activity = activity;
    }

    public ArrayList<Cidade> obterCidades() {
        ArrayList<Cidade> cidades = new ArrayList<>();

        try {
            InputStream inputStream = activity.getAssets().open("city.list.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            cidades = parseJSON(new String(buffer, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cidades;
    }

    private ArrayList<Cidade> parseJSON(String json) {
        ArrayList<Cidade> cidades = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for(int i = 0; i < jsonArray.length(); i++) {
                cidades.add(new Gson().fromJson(jsonArray.get(i).toString(), Cidade.class));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cidades;
    }
}
