package com.example.fernandobarros.questao1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.fernandobarros.questao1.R;
import com.example.fernandobarros.questao1.dao.QuestaoDAO;
import com.example.fernandobarros.questao1.modelo.Questao;

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
    private RadioGroup rgValor;
    private RadioButton rbValor1, rbValor2, rbValor3, rbValor4;



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
        rgValor = (RadioGroup) findViewById(R.id.formaulario_rg_valor_resposta);
        rbValor1 = (RadioButton) findViewById(R.id.formulario_rb_valor1);
        rbValor2 = (RadioButton) findViewById(R.id.formulario_rb_valor2);
        rbValor3 = (RadioButton) findViewById(R.id.formulario_rb_valor3);
        rbValor4 = (RadioButton) findViewById(R.id.formulario_rb_valor4);


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Questao questao = new Questao();
                FormularioToQuestaoExemplo(questao);
                QuestaoDAO questaoDAO = new QuestaoDAO(FormularioQuestaoActivity.this);
                questaoDAO.inserirQuestao(questao);

                Toast.makeText(FormularioQuestaoActivity.this, questao.getQuestao() + " Salvo", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void FormularioToQuestaoExemplo(Questao questao){
        questao.setQuestao(edtQuestao.getText().toString());
        questao.adicionarResposta((edtResposta1.getText().toString()),rbValor1.isChecked());
        questao.adicionarResposta((edtResposta2.getText().toString()),rbValor2.isChecked());
        questao.adicionarResposta((edtResposta3.getText().toString()),rbValor3.isChecked());
        questao.adicionarResposta((edtResposta4.getText().toString()),rbValor4.isChecked());

    }



}
