package carlos.weatherapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.yarolegovich.discretescrollview.DiscreteScrollView;

import java.util.ArrayList;

import carlos.weatherapp.R;
import carlos.weatherapp.adapters.CarouselAdapter;
import carlos.weatherapp.adapters.ListaFilmesAdapter;
import carlos.weatherapp.controllers.MainController;
import carlos.weatherapp.models.ShortMovieModel;
import carlos.weatherapp.util.Constantes;
import carlos.weatherapp.util.Utility;

import static carlos.weatherapp.util.Constantes.POSICAO_MENU_PESQUISAR;

public class MainActivity extends AppCompatActivity implements CarouselAdapter.OnItemClicked,
    ListaFilmesAdapter.OnItemClicked {

    private MainController mainController;
    private CarouselAdapter carouselAdapter;
    private ListaFilmesAdapter listaFilmesAdapter;
    private DiscreteScrollView picker;
    private RecyclerView rvFilmes;
    private RelativeLayout rlListaVazia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.main_title);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;

        actionBar.setHomeButtonEnabled(true);

        picker = findViewById(R.id.picker);
        rvFilmes = findViewById(R.id.rv_main_activity);
        rlListaVazia = findViewById(R.id.rl_lista_vazia_main_activity);

        mainController = new MainController(this);
        mainController.buscarFilmesResumidos();

        carouselAdapter = new CarouselAdapter(mainController, this);
        listaFilmesAdapter = new ListaFilmesAdapter(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mainController.buscarFilmesResumidos();
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
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void preencherCarrousel(ArrayList<ShortMovieModel> filmesResumidos) {
        if (filmesResumidos.isEmpty()) {
            rlListaVazia.setVisibility(View.VISIBLE);
        } else {
            rlListaVazia.setVisibility(View.GONE);

            carouselAdapter.setMovieArrayList(filmesResumidos);
            picker.setAdapter(carouselAdapter);

            listaFilmesAdapter.setMovieArrayList(filmesResumidos);
            rvFilmes.setLayoutManager(new LinearLayoutManager(this));
            rvFilmes.setAdapter(listaFilmesAdapter);
        }
    }

    @Override
    public void onCarouselItemClicked(String imdbId) {
        Intent intent = new Intent(MainActivity.this, DetalhesActivity.class);
        intent.putExtra(Constantes.ARG_FILME, Utility.buscarFilme(this, imdbId));
        startActivity(intent);
        finish();
    }

    @Override
    public void onRecyclerViewItemClicked(int positon) {
        picker.scrollToPosition(positon);
    }
}
