package carlos.weatherapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import carlos.weatherapp.R;

public class PesquisarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        RecyclerView rvCidades = findViewById(R.id.rv_pesquisar_activity);

    }
}
