package com.example.fernandobarros.questao1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fernandobarros.questao1.db.DbHelper;
import com.example.fernandobarros.questao1.modelo.Resposta;

import java.util.ArrayList;

/**
 * Created by fernando on 27/11/16.
 */

public class RespostaDAO {

    private Context context;

    public RespostaDAO(Context context){
        this.context = context;
    }

    public void inserirResposta(Resposta resposta){
        DbHelper dbHelper = new DbHelper(this.context);
        SQLiteDatabase db =  dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.COLUMN_RESPOSTA_ID_QUESTAO, resposta.getIdQuestao());
        contentValues.put(dbHelper.COLUMN_RESPOSTA, resposta.getResposta());
        contentValues.put(dbHelper.COLUMN_VALOR_RESPOSTA, resposta.isValorResposta()? 1 : 0 );
        db.insert(dbHelper.TABLE_RESPOSTA, null, contentValues);
        db.close();
    }

    public void inserirRespotas(ArrayList<Resposta> respostas, long idQuestao){
        for (Resposta resposta : respostas){
            resposta.setIdQuestao((int)idQuestao);
            inserirResposta(resposta);
        }
    }



    public ArrayList<Resposta> retornaRespostaQuestao(Integer idQuestao){
        //String.valueOf(idQuestao)
        //dbHelper.COLUMN_RESPOSTA_ID_QUESTAO + " =  " +String.valueOf(idQuestao)
        DbHelper dbHelper = new DbHelper(this.context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(dbHelper.TABLE_RESPOSTA, new String[] {
                        dbHelper.COLUMN_RESPOSTA_ID,
                        dbHelper.COLUMN_RESPOSTA_ID_QUESTAO,
                        dbHelper.COLUMN_RESPOSTA,
                        dbHelper.COLUMN_VALOR_RESPOSTA},
                dbHelper.COLUMN_RESPOSTA_ID_QUESTAO + " =  " +String.valueOf(idQuestao), null, null, null,null
        );

        ArrayList<Resposta> respostas = new ArrayList<Resposta>();

        while (cursor.moveToNext()){
            Resposta resposta = new Resposta();
            resposta.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_RESPOSTA_ID))));
            resposta.setIdQuestao(Integer.parseInt(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_RESPOSTA_ID_QUESTAO))));
            resposta.setResposta(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_RESPOSTA)));
            resposta.setValorResposta(cursor.getInt(cursor.getColumnIndex(dbHelper.COLUMN_VALOR_RESPOSTA))!=0 );
            respostas.add(resposta);
        }
        cursor.close();
        return respostas;

    }

}
