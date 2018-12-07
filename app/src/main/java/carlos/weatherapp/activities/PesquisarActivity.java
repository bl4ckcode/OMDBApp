package carlos.weatherapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import carlos.weatherapp.R;
import carlos.weatherapp.adapters.ListaFilmesResumidosAdapter;
import carlos.weatherapp.controllers.PesquisaController;
import carlos.weatherapp.models.ShortMovieModel;
import carlos.weatherapp.util.Constantes;

public class PesquisarActivity extends AppCompatActivity implements ListaFilmesResumidosAdapter.OnItemClicked {

    private PesquisaController pesquisaController;
    private ListaFilmesResumidosAdapter listaFilmesResumidosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.pesquisar_title);

        EditText editText = findViewById(R.id.edtText_pesquisar_activity);
        editText.setVisibility(View.VISIBLE);
        editText.addTextChangedListener(new TextWatcher() {
            private Timer timer = new Timer();
            private final long DELAY = 1000;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(final Editable editable) {
                timer.cancel();
                timer = new Timer();
                timer.schedule(
                        new TimerTask() {
                            @Override
                            public void run() {
                                if (editable.length() >= 3) {
                                    pesquisaController.buscarFilmes(editable.toString());
                                }
                            }
                        },
                        DELAY
                );
            }
        });

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        RecyclerView rvFilmesResumidos = findViewById(R.id.rv_pesquisar_activity);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        listaFilmesResumidosAdapter = new ListaFilmesResumidosAdapter(this);

        rvFilmesResumidos.setLayoutManager(layoutManager);
        rvFilmesResumidos.setAdapter(listaFilmesResumidosAdapter);

        pesquisaController = new PesquisaController(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(RESULT_OK);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRecyclerViewItemClicked(ShortMovieModel shortMovieModel) {
        Intent intent = new Intent(PesquisarActivity.this, DetalhesActivity.class);
        intent.putExtra(Constantes.ARG_IMBD_ID, shortMovieModel.getImdbID());
        startActivity(intent);
        finish();
    }

    public void preencherLista(List<ShortMovieModel> movieModels) {
        if (movieModels != null && !movieModels.isEmpty()) {
            listaFilmesResumidosAdapter.setShortMovieModels(movieModels);
            listaFilmesResumidosAdapter.notifyDataSetChanged();
        } else {

        }
    }
}
