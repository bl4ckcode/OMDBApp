package carlos.weatherapp.activities;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import carlos.weatherapp.R;
import carlos.weatherapp.controllers.DetalhesController;
import carlos.weatherapp.enums.ClimaEnum;
import carlos.weatherapp.models.Cidade;
import carlos.weatherapp.util.Constantes;
import carlos.weatherapp.util.Utility;

import static carlos.weatherapp.util.Constantes.POSICAO_MENU_DESFAVORITAR;
import static carlos.weatherapp.util.Constantes.POSICAO_MENU_FAVORITAR;

public class DetalhesActivity extends AppCompatActivity {
    private Cidade cidade;
    private DetalhesController detalhesController;

    private ProgressBar progressBar;
    private TextView tvDetalhesActivityCidade;
    private TextView tvDetalhesActivityTemp;
    private ImageView ivDetalhesActivityClima;
    private TextView tvDetalhesActivityClimaDesc;
    private TextView tvDetalhesActivityMaxTemp;
    private TextView tvDetalhesActivityMinTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        Bundle extras = getIntent().getExtras();
        assert extras != null;

        cidade = extras.getParcelable(Constantes.ARG_CIDADE);
        assert cidade != null;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getString(R.string.detalhes_title, cidade.getName()));

        progressBar = findViewById(R.id.pb_detalhes_activity);
        tvDetalhesActivityCidade = findViewById(R.id.tv_detalhes_activity_cidade);
        tvDetalhesActivityTemp = findViewById(R.id.tv_detalhes_activity_temp);
        ivDetalhesActivityClima = findViewById(R.id.iv_detalhes_activity_clima);
        tvDetalhesActivityClimaDesc = findViewById(R.id.tv_detalhes_activity_clima_desc);
        tvDetalhesActivityMaxTemp = findViewById(R.id.tv_detalhes_activity_max_temp);
        tvDetalhesActivityMinTemp = findViewById(R.id.tv_detalhes_activity_min_temp);

        detalhesController = new DetalhesController(this);
        detalhesController.obterDetalhesCidade(cidade.getId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search_item, menu);

        if (Utility.obterCidade(this, cidade.getId()) != null)
            menu.getItem(POSICAO_MENU_DESFAVORITAR).setVisible(true);
        else
            menu.getItem(POSICAO_MENU_FAVORITAR).setVisible(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            case R.id.action_favorite:
                detalhesController.atualizarFavorito(cidade, true);
                setResult(RESULT_OK);
                return true;
            case R.id.action_unfavorite:
                detalhesController.atualizarFavorito(cidade, false);
                setResult(RESULT_OK);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void preencherDetalhes(Cidade cidade) {
        this.cidade = cidade;

        tvDetalhesActivityCidade.setText(cidade.getName());
        tvDetalhesActivityTemp.setText(Utility.converterCelsiusKelvin(cidade.getMain().getTemp()));
        tvDetalhesActivityMaxTemp.setText(Utility.converterCelsiusKelvin(cidade.getMain().getTemp_max()));
        tvDetalhesActivityMinTemp.setText(Utility.converterCelsiusKelvin(cidade.getMain().getTemp_min()));

        ClimaEnum climaEnum = ClimaEnum.valueOf(this, cidade.getWeather().get(0).getMain());
        assert climaEnum != null;

        tvDetalhesActivityClimaDesc.setText(climaEnum.getIdClima());
        ivDetalhesActivityClima.setImageDrawable(ResourcesCompat.getDrawable(getResources(), climaEnum.getIdIcone(), null));
        ivDetalhesActivityClima.setImageTintList(ColorStateList.valueOf(climaEnum.getIdCor()));

        progressBar.setVisibility(View.GONE);
    }
}
