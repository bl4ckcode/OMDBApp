package carlos.weatherapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import carlos.weatherapp.models.Cidade;

/**
 * Created by Carlos on 16/08/2018.
 */

public class CidadesAdapter extends RecyclerView.Adapter<CidadesAdapter.CidadeViewholder> {
    public CidadesAdapter(Context context, ArrayList<Cidade> cidadeArrayList) {

    }

    @NonNull
    @Override
    public CidadeViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CidadeViewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CidadeViewholder extends RecyclerView.ViewHolder {

        public CidadeViewholder(View itemView) {
            super(itemView);
        }
    }
}
