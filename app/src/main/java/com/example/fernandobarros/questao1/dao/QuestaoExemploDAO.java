package com.example.fernandobarros.questao1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fernandobarros.questao1.db.DbHelper;
import com.example.fernandobarros.questao1.modelo.Questao;
import com.example.fernandobarros.questao1.modelo.QuestaoExemplo;

/**
 * Created by Fernando on 19/11/2016.
 */

public class QuestaoExemploDAO {
    private Context context;

    public QuestaoExemploDAO(Context context){
        this.context = context;
    }

    public void inserirQuestao(QuestaoExemplo  questao){
        DbHelper dbHelper = new DbHelper(this.context);
        SQLiteDatabase db =  dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.COLUMN_QUESTAO, questao.getQuestao());
        contentValues.put(dbHelper.COLUMN_RESPOSTA1, questao.getResposta1());
        contentValues.put(dbHelper.COLUMN_RESPOSTA2, questao.getResposta2());
        contentValues.put(dbHelper.COLUMN_RESPOSTA3, questao.getResposta3());
        contentValues.put(dbHelper.COLUMN_RESPOSTA4, questao.getResposta4());

        db.insert(dbHelper.TABLE_QUESTAO, null, contentValues);
        db.close();
    }

    public QuestaoExemplo listaQuestao(int id){
        DbHelper dbHelper = new DbHelper(this.context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor curso = db.query(dbHelper.TABLE_QUESTAO, new String[] {
                dbHelper.COLUMN_ID, dbHelper.COLUMN_QUESTAO, dbHelper.COLUMN_RESPOSTA1,
                dbHelper.COLUMN_RESPOSTA2, dbHelper.COLUMN_RESPOSTA3, dbHelper.COLUMN_RESPOSTA4},
                dbHelper.COLUMN_ID + " =? ", new String[] {String.valueOf(id)}, null, null, null,null
        );

       // Cursor curso = db.rawQuery("SELECT * FROM " + dbHelper.TABLE_QUESTAO + "WHERE " +
        //  dbHelper.COLUMN_ID + " = " + String.valueOf(id), null);

        if (curso != null){
            curso.moveToFirst();
        }

        QuestaoExemplo questaExemplo = new QuestaoExemplo();
        questaExemplo.setId(Integer.parseInt(curso.getString(curso.getColumnIndex(dbHelper.COLUMN_ID))));
        questaExemplo.setQuestao(curso.getString(curso.getColumnIndex(dbHelper.COLUMN_QUESTAO)));
        questaExemplo.setResposta1(curso.getString(curso.getColumnIndex(dbHelper.COLUMN_RESPOSTA1)));
        questaExemplo.setResposta2(curso.getString(curso.getColumnIndex(dbHelper.COLUMN_RESPOSTA2)));
        questaExemplo.setResposta3(curso.getString(curso.getColumnIndex(dbHelper.COLUMN_RESPOSTA3)));
        questaExemplo.setResposta4(curso.getString(curso.getColumnIndex(dbHelper.COLUMN_RESPOSTA4)));

        curso.close();
        db.close();

        return questaExemplo;
    }
}
