package com.example.fernandobarros.questao1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.fernandobarros.questao1.R;
import com.example.fernandobarros.questao1.adapter.ListaQuestaoAdapter;
import com.example.fernandobarros.questao1.dao.QuestaoDAO;
import com.example.fernandobarros.questao1.interfaces.OnItemQuestaoClickListener;
import com.example.fernandobarros.questao1.modelo.Questao;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaQuestoesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_questoes);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        ListaQuestaoAdapter listaQuestaoAdapter = new ListaQuestaoAdapter(alimentaLista());
        recyclerView.setAdapter(listaQuestaoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listaQuestaoAdapter.setOntItemClickListener(new OnItemQuestaoClickListener() {
            @Override
            public void OnItemClick(Questao questao) {
                Intent intent = new Intent(ListaQuestoesActivity.this, QuizActivity.class);
                intent.putExtra("QUESTAO", (Serializable) questao);
                intent.putExtra("ACTIVITY_PAI", R.id.activity_lista_questoes);
                startActivity(intent);
            }
        });
    }

    private ArrayList<Questao> alimentaLista(){
        QuestaoDAO questaoDAO = new QuestaoDAO(this);
        return questaoDAO.listaTodasQuestoes();
    }
}
