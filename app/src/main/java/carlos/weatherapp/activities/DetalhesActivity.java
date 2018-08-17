package carlos.weatherapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import carlos.weatherapp.R;
import carlos.weatherapp.models.Cidade;
import carlos.weatherapp.util.Constantes;

public class DetalhesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        Bundle extras = getIntent().getExtras();
        assert extras != null;

        Cidade cidade = extras.getParcelable(Constantes.ARG_CIDADE);
    }
}
