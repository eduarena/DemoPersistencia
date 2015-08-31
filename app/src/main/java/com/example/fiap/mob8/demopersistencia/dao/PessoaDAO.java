package com.example.fiap.mob8.demopersistencia.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fiap.mob8.demopersistencia.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rm49684 on 26/08/2015.
 */
public class PessoaDAO {

    private DBHelper dbHelper;
    private Context context;

    public PessoaDAO(Context context){
        dbHelper = new DBHelper(context);
        this.context = context;
    }

    public void salvar(Pessoa pessoa){

        if(pessoa.getId() == null){
            inserir(pessoa);
        }else{
            atualizar(pessoa);
        }
    }

    private void inserir(Pessoa pessoa){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            ContentValues valores = new ContentValues();
            valores.put("nome", pessoa.getNome());
            valores.put("idade", pessoa.getIdade());

            long id = db.insert("pessoa", "", valores);
            pessoa.setId(id);
        }finally {
            db.close();
        }

    }

    private void atualizar(Pessoa pessoa){
        ContentValues valores = new ContentValues();
        valores.put("nome",pessoa.getNome());
        valores.put("idade",pessoa.getIdade());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.update("pessoa",valores,"_id=?", new String[]{pessoa.getId().toString()});
        db.close();
    }

    public List<Pessoa> listAll(){
        List<Pessoa> pessoas = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("pessoa", null, null, null, null, null, "nome");

        try{
            while (cursor.moveToNext()){
                Pessoa pessoa = new Pessoa();
                pessoa.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                pessoa.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                pessoa.setIdade(cursor.getInt(cursor.getColumnIndex("idade")));
                pessoas.add(pessoa);
            }
        }finally {
            cursor.close();
        }
        db.close();
        return pessoas;
    }

    public void selecionar(){
        String id = "1";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("pessoa", new String[]{"_id","nome","idade"},"_id?", new String[]{id},null,null,null);
        if(cursor.getCount() > 0){
            Pessoa pessoa = new Pessoa();
            cursor.moveToFirst();
            pessoa.setId(cursor.getLong(cursor.getColumnIndex("_id")));
            pessoa.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            pessoa.setIdade(cursor.getInt(cursor.getColumnIndex("idade")));
        }
    }

}