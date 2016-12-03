package com.example.fernandobarros.questao1.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Fernando on 19/11/2016.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "trabalho";

    public static final String TABLE_QUESTAO = "questao";
    public static final String COLUMN_QUESTAO_ID = "id";
    public static final String COLUMN_QUESTAO = "questao";

    public static final String TABLE_RESPOSTA = "resposta";
    public static final String COLUMN_RESPOSTA_ID = "id";
    public static final String COLUMN_RESPOSTA_ID_QUESTAO = "idQuestao";
    public static final String COLUMN_RESPOSTA = "resposta";
    public static final String COLUMN_VALOR_RESPOSTA = "valor_resposta";


    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_QUESTAO = " CREATE  TABLE " +  TABLE_QUESTAO + "(" +
                COLUMN_QUESTAO_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_QUESTAO +" TEXT NOT NULL);" ;
        db.execSQL(CREATE_TABLE_QUESTAO);

        String CREATE_TABLE_RESPOSTA = " CREATE  TABLE " +  TABLE_RESPOSTA + "(" +
                COLUMN_RESPOSTA_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_RESPOSTA_ID_QUESTAO + " INTEGER, " +
                COLUMN_RESPOSTA +" TEXT NOT NULL," +
                COLUMN_VALOR_RESPOSTA + " INTEGER NOT NULL);" ;
                //"FOREIGN KEY ("+ COLUMN_RESPOSTA_ID_QUESTAO + ") REFERENCES "+TABLE_QUESTAO+"("+COLUMN_QUESTAO_ID+"));" ;
        db.execSQL(CREATE_TABLE_RESPOSTA);

        String teste = "passou";


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTAO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESPOSTA);
        onCreate(db);
    }
}
