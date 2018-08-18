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

import java.util.ArrayList;

import carlos.weatherapp.R;
import carlos.weatherapp.adapters.ListaCidadesDetalhadaAdapter;
import carlos.weatherapp.models.Cidade;
import carlos.weatherapp.util.Utility;

import static carlos.weatherapp.util.Constantes.POSICAO_MENU_PESQUISAR;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_PESQUISA = 0;
    public static final int REQUEST_DETALHES = 1;

    private ListaCidadesDetalhadaAdapter listaCidadesDetalhadaAdapter;

    private RelativeLayout rlListaVazia;
    private RecyclerView rvCidades;

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

        rvCidades = findViewById(R.id.rv_main_activity);
        rlListaVazia = findViewById(R.id.rl_activity_main_lista_vazia);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvCidades.setLayoutManager(layoutManager);

        ArrayList<Cidade> cidades = Utility.obterCidades(this);
        if (!cidades.isEmpty()) {
            listaCidadesDetalhadaAdapter = new ListaCidadesDetalhadaAdapter(this,
                    cidades);
            rvCidades.setAdapter(listaCidadesDetalhadaAdapter);
        } else {
            rlListaVazia.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            ArrayList<Cidade> cidades = Utility.obterCidades(this);

            if (cidades.isEmpty()) {
                rlListaVazia.setVisibility(View.VISIBLE);
            } else {
                rlListaVazia.setVisibility(View.GONE);
            }

            listaCidadesDetalhadaAdapter = new ListaCidadesDetalhadaAdapter(this,
                    cidades);
            rvCidades.setAdapter(listaCidadesDetalhadaAdapter);
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
}
