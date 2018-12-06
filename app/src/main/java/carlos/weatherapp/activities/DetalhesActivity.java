package carlos.weatherapp.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import carlos.weatherapp.R;
import carlos.weatherapp.controllers.DetalhesController;
import carlos.weatherapp.models.MovieModel;
import carlos.weatherapp.util.Constantes;
import carlos.weatherapp.util.Utility;

import static carlos.weatherapp.util.Constantes.POSICAO_MENU_EXCLUIR;
import static carlos.weatherapp.util.Constantes.POSICAO_MENU_SALVAR;

public class DetalhesActivity extends AppCompatActivity {

    private MovieModel filme;

    private DetalhesController detalhesController;

    private ProgressBar progressBar;
    private LinearLayout llDescricaoDetalhesActivity;
    private TextView sinopseDetalhesActivity;
    private LinearLayout llAnoDetalhesActivity;
    private TextView anoDetalhesActivity;
    private LinearLayout llDuracaoDetalhesActivity;
    private TextView duracaoDetalhesActivity;
    private LinearLayout llGeneroDetalhesActivity;
    private TextView generoDetalhesActivity;
    private LinearLayout llDiretoresDetalhesActivity;
    private TextView diretoresDetalhesActivity;
    private LinearLayout llAtoresDetalhesActivity;
    private TextView atoresDetalhesActivity;
    private LinearLayout llPremiacoesDetalhesActivity;
    private TextView premiacoesDetalhesActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        Bundle extras = getIntent().getExtras();
        assert extras != null;

        filme = extras.getParcelable(Constantes.ARG_FILME);
        assert filme != null;

        boolean offline = extras.getBoolean(Constantes.ARG_OFFLINE);
        assert filme != null;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.detalhes_title, filme.getTitle(), filme.getYear()));

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.pb_detalhes_activity);
        llDescricaoDetalhesActivity = findViewById(R.id.ll_descricao_detalhes_activity);
        sinopseDetalhesActivity = findViewById(R.id.sinopse_detalhes_activity);
        llAnoDetalhesActivity = findViewById(R.id.ll_ano_detalhes_activity);
        anoDetalhesActivity = findViewById(R.id.ano_detalhes_activity);
        llDuracaoDetalhesActivity = findViewById(R.id.ll_duracao_detalhes_activity);
        duracaoDetalhesActivity = findViewById(R.id.duracao_detalhes_activity);
        llGeneroDetalhesActivity = findViewById(R.id.ll_genero_detalhes_activity);
        generoDetalhesActivity = findViewById(R.id.genero_detalhes_activity);
        llDiretoresDetalhesActivity = findViewById(R.id.ll_diretores_detalhes_activity);
        diretoresDetalhesActivity = findViewById(R.id.diretores_detalhes_activity);
        llAtoresDetalhesActivity = findViewById(R.id.ll_atores_detalhes_activity);
        atoresDetalhesActivity = findViewById(R.id.atores_detalhes_activity);
        llPremiacoesDetalhesActivity = findViewById(R.id.ll_premiacoes_detalhes_activity);
        premiacoesDetalhesActivity = findViewById(R.id.premiacoes_detalhes_activity);

        detalhesController = new DetalhesController(this);

        if (!offline)
            detalhesController.obterDetalhesFilme(filme.getImdbID());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search_item, menu);

        if (Utility.buscarFilme(this, filme.getImdbID()) != null) {
            menu.getItem(POSICAO_MENU_SALVAR).setVisible(true);
            menu.getItem(POSICAO_MENU_EXCLUIR).setVisible(true);
        } else {
            menu.getItem(POSICAO_MENU_SALVAR).setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(RESULT_OK);
                finish();
                return true;
            case R.id.action_salvar:
                detalhesController.inserirFilme(filme);
                setResult(RESULT_OK);
                return true;
            case R.id.action_unfavorite:
                detalhesController.removerFilme(filme.getImdbID());
                setResult(RESULT_OK);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void preencherDetalhes(MovieModel filme) {
        this.filme = filme;

        if (filme.getPlot() != null) {
            sinopseDetalhesActivity.setText(filme.getPlot());
        } else {
            llDescricaoDetalhesActivity.setVisibility(View.GONE);
        }

        if (filme.getReleased() != null) {
            anoDetalhesActivity.setText(filme.getReleased());
        } else {
            llAnoDetalhesActivity.setVisibility(View.GONE);
        }

        if (filme.getRuntime() != null) {
            duracaoDetalhesActivity.setText(filme.getRuntime());
        } else {
            llDuracaoDetalhesActivity.setVisibility(View.GONE);
        }

        if (filme.getGenre() != null) {
            generoDetalhesActivity.setText(filme.getGenre());
        } else {
            llGeneroDetalhesActivity.setVisibility(View.GONE);
        }

        if (filme.getDirector() != null) {
            diretoresDetalhesActivity.setText(filme.getPlot());
        } else {
            llDiretoresDetalhesActivity.setVisibility(View.GONE);
        }

        if (filme.getActors() != null) {
            atoresDetalhesActivity.setText(filme.getPlot());
        } else {
            llAtoresDetalhesActivity.setVisibility(View.GONE);
        }

        if (filme.getAwards() != null) {
            premiacoesDetalhesActivity.setText(filme.getPlot());
        } else {
            llPremiacoesDetalhesActivity.setVisibility(View.GONE);
        }

        progressBar.setVisibility(View.GONE);
    }
}
