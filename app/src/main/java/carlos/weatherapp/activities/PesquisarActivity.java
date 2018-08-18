package carlos.weatherapp.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import carlos.weatherapp.R;
import carlos.weatherapp.adapters.ListaCidadesAdapter;
import carlos.weatherapp.controllers.PesquisaController;

public class PesquisarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.pesquisar_title);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        RecyclerView rvCidades = findViewById(R.id.rv_pesquisar_activity);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        PesquisaController pesquisaController = new PesquisaController(this);
        ListaCidadesAdapter listaCidadesAdapter = new ListaCidadesAdapter(this, pesquisaController.obterCidades());

        rvCidades.setLayoutManager(layoutManager);
        rvCidades.setAdapter(listaCidadesAdapter);
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
}
