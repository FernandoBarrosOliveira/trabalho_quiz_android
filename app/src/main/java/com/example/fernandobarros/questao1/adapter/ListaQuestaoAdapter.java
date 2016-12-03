package com.example.fernandobarros.questao1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fernandobarros.questao1.R;
import com.example.fernandobarros.questao1.interfaces.OnItemQuestaoClickListener;
import com.example.fernandobarros.questao1.modelo.Questao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fernando on 29/11/16.
 */

public class ListaQuestaoAdapter extends RecyclerView.Adapter<ListaQuestaoAdapter.ViewHolder> {
    private List<Questao> listaQuestoes = new ArrayList<>();
    private OnItemQuestaoClickListener ontItemClickListener;

    public ListaQuestaoAdapter(List<Questao> listaQuestoes){
        this.listaQuestoes = listaQuestoes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_questao, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Questao questao = listaQuestoes.get(position);
        holder.textView.setText(questao.getQuestao());

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ontItemClickListener.OnItemClick(questao);
            }
        };
        holder.textView.setOnClickListener(listener);



    }

    @Override
    public int getItemCount() {
        return listaQuestoes.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView  = (TextView) itemView.findViewById(R.id.textView);
        }
    }

    public OnItemQuestaoClickListener getOntItemClickListener() {
        return ontItemClickListener;
    }

    public void setOntItemClickListener(OnItemQuestaoClickListener ontItemClickListener) {
        this.ontItemClickListener = ontItemClickListener;
    }
}
