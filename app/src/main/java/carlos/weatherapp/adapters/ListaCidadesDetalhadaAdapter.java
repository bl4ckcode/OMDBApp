package carlos.weatherapp.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import carlos.weatherapp.R;
import carlos.weatherapp.activities.DetalhesActivity;
import carlos.weatherapp.activities.MainActivity;
import carlos.weatherapp.models.Cidade;
import carlos.weatherapp.util.Utility;

import static carlos.weatherapp.util.Constantes.ARG_CIDADE;

/**
 * Created by Carlos on 16/08/2018.
 */

public class ListaCidadesDetalhadaAdapter extends RecyclerView.Adapter<ListaCidadesDetalhadaAdapter.CidadeViewholder> {
    private static final int TABLE_ROW_PADDING = 5;

    private List<Cidade> cidadeArrayList;
    private Activity activity;

    public ListaCidadesDetalhadaAdapter(Activity activity, List<Cidade> cidadeArrayList) {
        this.activity = activity;
        this.cidadeArrayList = cidadeArrayList;
    }

    class CidadeViewholder extends RecyclerView.ViewHolder {
        TableLayout tlCidades;

        CidadeViewholder(View itemView) {
            super(itemView);

            tlCidades = itemView.findViewById(R.id.tl_adapter_cidades);
        }
    }

    @NonNull
    @Override
    public CidadeViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CidadeViewholder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_lista_cidades_detalhada, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final CidadeViewholder holder, int position) {
        final Cidade cidade = cidadeArrayList.get(position);

        TableRow.LayoutParams layoutParamsTr = new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);

        float pixel = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, TABLE_ROW_PADDING, activity.getResources().getDisplayMetrics());

        TableRow trLinha = new TableRow(activity);
        trLinha.setLayoutParams(layoutParamsTr);
        trLinha.setPadding(Math.round(pixel), Math.round(pixel), Math.round(pixel), Math.round(pixel));

        TextView tvCidade = new TextView(activity);
        tvCidade.setLayoutParams(layoutParamsTr);
        TextView tvClima = new TextView(activity);
        tvClima.setLayoutParams(layoutParamsTr);
        TextView tvTemp = new TextView(activity);
        tvTemp.setLayoutParams(layoutParamsTr);

        tvCidade.setText(cidade.getName());
        tvClima.setText(cidade.getWeather().get(0).getMain());
        tvTemp.setText(Utility.converterCelsiusKelvin(cidade.getMain().getTemp()));

        trLinha.addView(tvCidade);
        trLinha.addView(tvClima);
        trLinha.addView(tvTemp);

        trLinha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetalhesActivity.class);
                intent.putExtra(ARG_CIDADE, cidadeArrayList.get(holder.getAdapterPosition()));
                activity.startActivityForResult(intent, MainActivity.REQUEST_DETALHES);
            }
        });

        holder.tlCidades.addView(trLinha);
    }

    @Override
    public int getItemCount() {
        return cidadeArrayList.size();
    }
}
