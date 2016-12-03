package com.example.fernandobarros.questao1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.example.fernandobarros.questao1.db.DbHelper;
import com.example.fernandobarros.questao1.modelo.Questao;
import com.example.fernandobarros.questao1.modelo.QuestaoExemplo;
import com.example.fernandobarros.questao1.modelo.Resposta;

import java.util.ArrayList;

/**
 * Created by fernando on 27/11/16.
 */

public class QuestaoDAO {
    private Context context;

    public QuestaoDAO(Context context){
        this.context = context;
    }

    public void inserirQuestao(Questao questao){
        DbHelper dbHelper = new DbHelper(this.context);
        SQLiteDatabase db =  dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.COLUMN_QUESTAO, questao.getQuestao());

        long id = db.insert(dbHelper.TABLE_QUESTAO, null, contentValues);
        db.close();

        RespostaDAO  respostaDAO = new RespostaDAO(context);
        respostaDAO.inserirRespotas(questao.getRespostas(), id);
    }

    public ArrayList<Questao> listaTodasQuestoes(){
        DbHelper dbHelper = new DbHelper(this.context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(dbHelper.TABLE_QUESTAO, new String[] {
                        dbHelper.COLUMN_QUESTAO_ID, dbHelper.COLUMN_QUESTAO},
                        null, null, null, null, null,null);

        ArrayList<Questao> questoes = new ArrayList<>();

        while (cursor.moveToNext()){
            Questao questao = new Questao();
            questao.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_QUESTAO_ID))));
            questao.setQuestao(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_QUESTAO)));
            questao.setRespostas(new RespostaDAO(context).retornaRespostaQuestao(questao.getId()));
            questoes.add(questao);
        }
        cursor.close();

        return questoes;
    }

    public Questao listaQuestao(int id){
        DbHelper dbHelper = new DbHelper(this.context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor curso = db.query(dbHelper.TABLE_QUESTAO, new String[] {
                        dbHelper.COLUMN_QUESTAO_ID, dbHelper.COLUMN_QUESTAO},
                dbHelper.COLUMN_QUESTAO_ID + "=? ", new String[] {String.valueOf(id)}, null, null, null,null
        );


        if (curso != null){
            curso.moveToFirst();
        }

        Questao questao = new Questao();
        questao.setId(Integer.parseInt(curso.getString(curso.getColumnIndex(dbHelper.COLUMN_QUESTAO_ID))));
        questao.setQuestao(curso.getString(curso.getColumnIndex(dbHelper.COLUMN_QUESTAO)));
        questao.setRespostas(retornaResposta(questao.getId()));
        curso.close();
        db.close();

        return questao;
    }

    public ArrayList<Questao> retornaQuestoRandomica(int Limite){
        DbHelper dbHelper = new DbHelper(this.context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + dbHelper.TABLE_QUESTAO +" ORDER BY RANDOM() LIMIT " + String.valueOf(Limite);
        Cursor cursor = db.rawQuery(sql, null);

        ArrayList<Questao> questoes = new ArrayList<>();

        while (cursor.moveToNext()){
            Questao questao = new Questao();
            questao.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_QUESTAO_ID))));
            questao.setQuestao(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_QUESTAO)));
            questao.setRespostas(new RespostaDAO(context).retornaRespostaQuestao(questao.getId()));
            questoes.add(questao);
        }
        cursor.close();

        return questoes;
    }

    public int quantidadeQuestoCadastras(){
        DbHelper dbHelper = new DbHelper(this.context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "SELECT count(*) FROM " + dbHelper.TABLE_QUESTAO;
        long quantidadeRegistro = DatabaseUtils.longForQuery(db, sql,null);
        return (int)quantidadeRegistro;

    }

    private ArrayList<Resposta>  retornaResposta(Integer id){
        RespostaDAO respostaDAO = new RespostaDAO(context);
        return respostaDAO.retornaRespostaQuestao(id);

    }

}
