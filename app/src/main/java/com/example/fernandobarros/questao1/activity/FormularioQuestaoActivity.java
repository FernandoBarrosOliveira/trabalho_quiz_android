package com.example.fernandobarros.questao1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fernandobarros.questao1.R;
import com.example.fernandobarros.questao1.dao.QuestaoExemploDAO;
import com.example.fernandobarros.questao1.modelo.QuestaoExemplo;

/**
 * Created by Fernando on 19/11/2016.
 */

public class FormularioQuestaoActivity extends AppCompatActivity {
    private EditText edtQuestao;
    private EditText edtResposta1;
    private EditText edtResposta2;
    private EditText edtResposta3;
    private EditText edtResposta4;
    private Button btnSalvar;
    private EditText editText2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cadastro);

        btnSalvar = (Button) findViewById(R.id.formulario_btn_salvar);
        edtQuestao = (EditText) findViewById(R.id.formuario_edt_questao);
        edtResposta1 = (EditText) findViewById(R.id.formulario_edt_resposta1);
        edtResposta2 = (EditText) findViewById(R.id.formulario_edt_resposta2);
        edtResposta3 = (EditText) findViewById(R.id.formulario_edt_resposta3);
        edtResposta4 = (EditText) findViewById(R.id.formulario_edt_resposta4);
        editText2 = (EditText) findViewById(R.id.editText2);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestaoExemplo questaoExemplo = new QuestaoExemplo();
                FormularioToQuestaoExemplo(questaoExemplo);
                QuestaoExemploDAO questaoExemploDAO = new QuestaoExemploDAO(FormularioQuestaoActivity.this);
                questaoExemploDAO.inserirQuestao(questaoExemplo);

                QuestaoExemplo questaoExemploBanco = new QuestaoExemplo();
                questaoExemploBanco = questaoExemploDAO.listaQuestao(2);
                Toast.makeText(FormularioQuestaoActivity.this, questaoExemploBanco.getQuestao() + " Salvo", Toast.LENGTH_SHORT).show();


            }
        });

    }

    private void FormularioToQuestaoExemplo(QuestaoExemplo questaoExemplo){
        questaoExemplo.setQuestao(edtQuestao.getText().toString());
        questaoExemplo.setResposta1(edtResposta1.getText().toString());
        questaoExemplo.setResposta2(edtResposta2.getText().toString());
        questaoExemplo.setResposta3(edtResposta3.getText().toString());
        questaoExemplo.setResposta4(edtResposta4.getText().toString());

    }



}
