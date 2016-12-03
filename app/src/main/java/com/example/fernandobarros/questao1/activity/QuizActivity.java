package com.example.fernandobarros.questao1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fernandobarros.questao1.R;
import com.example.fernandobarros.questao1.adapter.ListaRespostaAdapter;
import com.example.fernandobarros.questao1.dao.QuestaoDAO;
import com.example.fernandobarros.questao1.interfaces.OnItemQuestaoClickListener;
import com.example.fernandobarros.questao1.interfaces.OnItemRespostaClickListener;
import com.example.fernandobarros.questao1.modelo.Questao;
import com.example.fernandobarros.questao1.modelo.Resposta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.makeText;

public class QuizActivity extends AppCompatActivity {
    private ImageView imgQuestao;
    private TextView txtQuestao;
    private int numeroQuestao;
    private int numeroAcertos;
    private int numeroTotalQuestao;
    private String nomeUsuario;
    private RecyclerView recyclerResposta;
    private ListaRespostaAdapter listaRespostaAdapter;
    private int activityPai;
    private String dificuldade;
    private ArrayList<Questao> listaQuestao;
    private Intent intent;

    @Override
    protected void onResume() {
        super.onResume();
        numeroQuestao = 1;
        numeroAcertos = 0;




        if (activityPai == R.id.activity_lista_questoes){
            Questao questao = (Questao) intent.getSerializableExtra("QUESTAO");
            montarEstrutura(questao);
        }else{
            dificuldade = intent.getStringExtra("DIFICULDADE"); // trocar dificuldade para um enumerator
            numeroTotalQuestao = pegaNumeroTotalQuestao(dificuldade);
            nomeUsuario = intent.getStringExtra("NOME_USUARIO");
            listaQuestao = new QuestaoDAO(this).retornaQuestoRandomica(numeroTotalQuestao);
            montarEstrutura(gerarQuestao(numeroQuestao));

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        imgQuestao = (ImageView) findViewById(R.id.quiz_img_questao);
        txtQuestao = (TextView) findViewById(R.id.quiz_txt_questao);
        recyclerResposta = (RecyclerView) findViewById(R.id.quiz_recycle_perguntaS);
        intent = getIntent();
        activityPai = intent.getIntExtra("ACTIVITY_PAI",0);
        listaRespostaAdapter = new ListaRespostaAdapter(activityPai==R.id.activity_lista_questoes);




        listaRespostaAdapter.setOntItemClickListener(new OnItemRespostaClickListener() {
            @Override
            public void OnItemClick(Resposta resposta) {
                String msg;

                if (resposta.isValorResposta()){
                    msg = "Correto";
                    numeroAcertos++;
                }else{
                    msg = "Errado";
                }

                makeText(QuizActivity.this, msg, Toast.LENGTH_SHORT).show();

                if (activityPai == R.id.activity_apresentacao){
                    if (numeroQuestao < numeroTotalQuestao){
                        numeroQuestao++;
                        makeText(QuizActivity.this, msg, Toast.LENGTH_SHORT).show();
                        montarEstrutura(gerarQuestao(numeroQuestao));
                    }else{
                        Intent intent = new Intent(QuizActivity.this, ResultadoActivity.class);
                        intent.putExtra("NOME_USUARIO", nomeUsuario);
                        intent.putExtra("QTD_ACERTO", numeroAcertos);
                        intent.putExtra("QTD_QUESTAO", numeroQuestao);
                        startActivity(intent);
                    }
                }

            }
        });


    }

    private int pegaNumeroTotalQuestao(String dificuldade){

        if (dificuldade == "DIFICIL"){
            return  15;
        }else if (dificuldade == "MEDIO"){
            return 10;
        }else{
            return 5;
        }

    }



    private Questao gerarQuestao(int numeroPergunta){


       return listaQuestao.get(numeroPergunta-1);

    }


    private void  montarEstrutura(Integer id){
        QuestaoDAO questaoDAO = new QuestaoDAO(this);
        montarEstrutura(questaoDAO.listaQuestao(id));

    }


    private void  montarEstrutura(Questao questao){
        //imgQuestao.setImageResource(questao.getEnderecoImagem());
        txtQuestao.setText(questao.getQuestao());
        ArrayList<Resposta> respostas = questao.getRespostas();

        listaRespostaAdapter.setListaResposta(respostas);
        recyclerResposta.setAdapter(listaRespostaAdapter);
        recyclerResposta.setLayoutManager(new LinearLayoutManager(this));

    }




}
