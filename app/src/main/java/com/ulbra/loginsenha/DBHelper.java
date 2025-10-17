package com.ulbra.loginsenha;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BancoDados.db";
    private static final int DATABASE_VERSION = 1;

    // Construtor - cria o banco de dados
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Cria a tabela de usuários quando o app é instalado
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE utilizador(username TEXT PRIMARY KEY, password TEXT)";
        db.execSQL(sql);
    }

    // Atualiza o banco se a versão mudar
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS utilizador");
        onCreate(db);
    }

    // Salva novo usuário no banco
    public long criarUtilizador(String userName, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", userName);
        cv.put("password", password);
        return db.insert("utilizador", null, cv);
    }

    // Verifica se login e senha estão corretos
    public String validarLogin(String userName, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM utilizador WHERE username = ? AND password = ?", new String[]{userName, password});
        if (c.getCount() > 0) {
            return "OK";
        }
        return "ERRO";
    }
}