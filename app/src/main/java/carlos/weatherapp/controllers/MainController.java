package carlos.weatherapp.controllers;

import java.io.File;

import carlos.weatherapp.activities.MainActivity;
import carlos.weatherapp.util.Utility;

public class MainController {
    private MainActivity mainActivity;

    public MainController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void buscarFilmesResumidos() {
        mainActivity.preencherCarrousel(Utility.buscarFilmesResumidos(mainActivity));
    }

    public String retornarCaminhoImagem(String imdbId) {
        return mainActivity.getFilesDir().getAbsolutePath() + File.separator + imdbId + ".jpg";
    }
}
