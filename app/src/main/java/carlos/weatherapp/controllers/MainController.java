package carlos.weatherapp.controllers;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    public String retornarCaminhoImagem(String url) throws IOException, NoSuchAlgorithmException {
        byte[] bytesOfMessage = url.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        String imageName = new String(md.digest(bytesOfMessage), "UTF-8");

        return Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES) + File.separator + imageName + ".jpg";
    }
}
