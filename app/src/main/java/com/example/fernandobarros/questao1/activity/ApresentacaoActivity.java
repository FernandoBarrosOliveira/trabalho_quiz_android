package com.example.fernandobarros.questao1.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.fernandobarros.questao1.R;
import com.example.fernandobarros.questao1.dao.QuestaoDAO;
import com.example.fernandobarros.questao1.modelo.Questao;

import static android.widget.Toast.makeText;

public class ApresentacaoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Button btnIniciar, btnCadastrar, btnListar;
    private EditText edtNome;
    private Spinner spinnerDificuldade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao);

        btnIniciar = (Button) findViewById(R.id.apresentacao_btn_iniciar);
        btnCadastrar = (Button) findViewById(R.id.apresentacao_bt_cadastrar);
        btnListar = (Button) findViewById(R.id.apresentacao_bt_listar);
        edtNome = (EditText) findViewById(R.id.apresentacao_edt_nome);
        spinnerDificuldade = (Spinner) findViewById(R.id.apresentacao_spinner_dificuldade);
        spinnerDificuldade.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dificuldade, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerDificuldade.setAdapter(adapter);


        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApresentacaoActivity.this, QuizActivity.class);
                if (podeIrParaOQuiz(spinnerDificuldade.getSelectedItemPosition())){
                    intent.putExtra("NOME_USUARIO", edtNome.getText().toString());
                    intent.putExtra("ACTIVITY_PAI", R.id.activity_apresentacao);
                    intent.putExtra("DIFICULDADE", pegaDificuldade(spinnerDificuldade.getSelectedItemPosition()));
                    startActivity(intent);
                }else{
                    makeText(ApresentacaoActivity.this, "Não possui quantidade de questões suficiente ", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApresentacaoActivity.this, FormularioQuestaoActivity.class);
                startActivity(intent);
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ApresentacaoActivity.this, ListaQuestoesActivity.class);
                startActivity(intent);
            }
        });

    }


    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String item = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    private String pegaDificuldade(int positiChekBox){
        switch ( positiChekBox){
            case 1:
                return "MEDIO";
            case 2:
                return "DIFICIL";
            default:
                return "FACIL";
        }

    }

    private boolean podeIrParaOQuiz(int positiChekBox){
        QuestaoDAO questaoDAO = new QuestaoDAO(this);
        int qtdQuestoes = questaoDAO.quantidadeQuestoCadastras();
        switch ( positiChekBox){
            case 0:
                return qtdQuestoes >= 5;
            case 1:
                return qtdQuestoes >= 10;
            case 2:
                return qtdQuestoes >= 15;
            default:
                return false;
        }

    }

}
