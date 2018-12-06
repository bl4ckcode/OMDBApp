package carlos.weatherapp.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import carlos.weatherapp.R;
import carlos.weatherapp.adapters.CarouselAdapter;
import carlos.weatherapp.adapters.ListaFilmesAdapter;
import carlos.weatherapp.controllers.MainController;
import carlos.weatherapp.models.ShortMovieModel;

import static carlos.weatherapp.util.Constantes.POSICAO_MENU_PESQUISAR;

public class MainActivity extends AppCompatActivity implements CarouselAdapter.OnItemClicked,
    ListaFilmesAdapter.OnItemClicked {
    public static final int REQUEST_PESQUISA = 0;
    public static final int REQUEST_DETALHES = 1;

    private MainController mainController;
    private CarouselAdapter carouselAdapter;
    private ListaFilmesAdapter listaFilmesAdapter;
    private DiscreteScrollView picker;
    private RecyclerView rvFilmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;

        actionBar.setHomeButtonEnabled(true);

        picker = findViewById(R.id.picker);
        rvFilmes = findViewById(R.id.rv_main_activity);

        carouselAdapter = new CarouselAdapter(this);
        listaFilmesAdapter = new ListaFilmesAdapter(this);

        mainController = new MainController(this);
        mainController.buscarFilmesResumidos();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK)
            if (requestCode == REQUEST_PESQUISA) {

            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search_item, menu);
        menu.getItem(POSICAO_MENU_PESQUISAR).setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Intent intent = new Intent(this, PesquisarActivity.class);
                startActivityForResult(intent, REQUEST_PESQUISA);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void preencherCarrousel(ArrayList<ShortMovieModel> filmesResumidos) {
        List<Bitmap> data = new ArrayList<>();

        for(ShortMovieModel shortMovieModel : filmesResumidos) {
            try {
                data.add(BitmapFactory.decodeFile(mainController.retornarCaminhoImagem(shortMovieModel.getPoster())));
            } catch (IOException|NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        carouselAdapter.setData(data);
        picker.setAdapter(carouselAdapter);

        listaFilmesAdapter.setMovieArrayList(filmesResumidos);
        rvFilmes.setLayoutManager(new LinearLayoutManager(this));
        rvFilmes.setAdapter(listaFilmesAdapter);
    }

    @Override
    public void onCarouselItemClicked(int positon) {
        // IR PARA DETALHES
    }

    @Override
    public void onRecyclerViewItemClicked(int positon) {
        // AJUSTAR CAROUSEL
    }
}
