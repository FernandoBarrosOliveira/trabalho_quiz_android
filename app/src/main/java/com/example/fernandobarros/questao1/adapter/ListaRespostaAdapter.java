package com.example.fernandobarros.questao1.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.fernandobarros.questao1.R;
import com.example.fernandobarros.questao1.interfaces.OnItemRespostaClickListener;
import com.example.fernandobarros.questao1.modelo.Resposta;

import java.util.ArrayList;

/**
 * Created by fernando on 02/12/16.
 */

public class ListaRespostaAdapter extends RecyclerView.Adapter<ListaRespostaAdapter.ViewHolder> {


    private ArrayList<Resposta> listaResposta = new ArrayList<>();
    private OnItemRespostaClickListener ontItemClickListener;
    private boolean exibeMudancaCor;


    public ListaRespostaAdapter(ArrayList<Resposta> listaResposta){
        this.listaResposta =listaResposta;
    }

    public ListaRespostaAdapter(boolean exibeMudancaCor) {
        this.exibeMudancaCor = exibeMudancaCor;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_resposta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Resposta resposta = listaResposta.get(position);
        holder.txtResposta.setText(resposta.getResposta());

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ontItemClickListener.OnItemClick(resposta);
            }
        };
        holder.txtResposta.setOnClickListener(listener);
        if(resposta.isValorResposta() && exibeMudancaCor)
        holder.txtResposta.setBackgroundColor(Color.parseColor("#ff4081"));
    }

    @Override
    public int getItemCount() {
        return listaResposta.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        private TextView txtResposta;


        public ViewHolder(View itemView) {
            super(itemView);
            txtResposta  = (TextView) itemView.findViewById(R.id.lista_resposta_txt_resposta);
        }
    }

    public void setListaResposta(ArrayList<Resposta> listaResposta) {
        this.listaResposta = listaResposta;
    }

    public OnItemRespostaClickListener getOntItemClickListener() {
        return ontItemClickListener;
    }

    public void setOntItemClickListener(OnItemRespostaClickListener ontItemClickListener) {
        this.ontItemClickListener = ontItemClickListener;
    }
}
