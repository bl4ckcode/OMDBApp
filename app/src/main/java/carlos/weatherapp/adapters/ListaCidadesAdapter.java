package carlos.weatherapp.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import carlos.weatherapp.R;
import carlos.weatherapp.activities.DetalhesActivity;
import carlos.weatherapp.models.Cidade;

import static carlos.weatherapp.util.Constantes.ARG_CIDADE;

public class ListaCidadesAdapter extends RecyclerView.Adapter<ListaCidadesAdapter.ListaCidadesViewholder> {
    private List<Cidade> cidadeArrayList;
    private Activity activity;

    public ListaCidadesAdapter(Activity activity, List<Cidade> cidadeArrayList) {
        this.activity = activity;
        this.cidadeArrayList = cidadeArrayList;
    }

    class ListaCidadesViewholder extends RecyclerView.ViewHolder {
        TextView tvCidades;
        TextView tvCidadesPrimeiraPosicao;

        ListaCidadesViewholder(View itemView) {
            super(itemView);

            tvCidades = itemView.findViewById(R.id.tv_adapter_lista_cidades);
            tvCidadesPrimeiraPosicao = itemView.findViewById(R.id.tv_adapter_lista_cidades_cidade);
        }
    }

    @NonNull
    @Override
    public ListaCidadesAdapter.ListaCidadesViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListaCidadesViewholder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_lista_cidades, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ListaCidadesAdapter.ListaCidadesViewholder holder, int position) {
        if (position != 0) {
            Cidade cidade = cidadeArrayList.get(position);

            holder.tvCidadesPrimeiraPosicao.setVisibility(View.GONE);
            holder.tvCidades.setVisibility(View.VISIBLE);

            holder.tvCidades.setText(cidade.getName());
            holder.tvCidades.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, DetalhesActivity.class);
                    intent.putExtra(ARG_CIDADE, cidadeArrayList.get(holder.getAdapterPosition()));
                    activity.startActivity(intent);
                }
            });
        } else {
            holder.tvCidadesPrimeiraPosicao.setVisibility(View.VISIBLE);
            holder.tvCidades.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return cidadeArrayList.size();
    }
}
