package com.example.gohorse.pokefight.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gohorse.pokefight.R;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.List;

/**
 * Created by Bruno on 04/06/2015.
 */
public class RVAdapterInfo extends RecyclerView.Adapter<RVAdapterInfo.MyViewHolder> {

    private LayoutInflater inflater;
    List<Information> data = Collections.emptyList();

    public RVAdapterInfo(Context context, List<Information> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_view, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information current = data.get(position);
        holder.index.setText(String.valueOf(current.getIndex()));
        holder.nome.setText(current.getNome());
        holder.tipo.setText(current.getTipo());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView index;
        TextView nome;
        TextView tipo;

        public MyViewHolder(View itemView) {
            super(itemView);

            index = (TextView) itemView.findViewById(R.id.txtViewIndex);
            nome = (TextView) itemView.findViewById(R.id.txtViewNomeCard);
            tipo = (TextView) itemView.findViewById(R.id.txtViewTipoCard);
        }
    }

}
