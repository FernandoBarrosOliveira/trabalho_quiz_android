package com.example.fernandobarros.questao1.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Fernando on 19/11/2016.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "trabalho";

    public static final String TABLE_QUESTAO = "questao";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_QUESTAO = "questao";
    public static final String COLUMN_RESPOSTA1 = "resposta1";
    public static final String COLUMN_RESPOSTA2 = "resposta2";
    public static final String COLUMN_RESPOSTA3 = "resposta3";
    public static final String COLUMN_RESPOSTA4 = "resposta4";

    public DbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_QUESTAO = " CREATE  TABLE " +  TABLE_QUESTAO + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_QUESTAO +" TEXT NOT NULL," +
                COLUMN_RESPOSTA1 +" TEXT NOT NULL, " +
                COLUMN_RESPOSTA2 +" TEXT NOT NULL, " +
                COLUMN_RESPOSTA3 +" TEXT NOT NULL, " +
                COLUMN_RESPOSTA4 +" TEXT NOT NULL); " ;

        db.execSQL(CREATE_TABLE_QUESTAO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTAO);
        onCreate(db);
    }
}
