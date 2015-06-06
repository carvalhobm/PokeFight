package com.example.gohorse.pokefight.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.gohorse.pokefight.adapter.Information;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bruno on 06/06/2015.
 */
public class DataHelper {

    private static final String DATABASE_NAME = "PokeFight.db";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_NAME = "pkftbl001_pokemon";

    private Context context;
    private SQLiteDatabase db;

    private SQLiteStatement insertStmt;
    private static final String INSERT = "INSERT INTO " + TABLE_NAME + "(_id, name, tipo) VALUES (?, ?, ?)";

    public DataHelper(Context context){
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStmt = this.db.compileStatement(INSERT);
    }

    public long insert(Information information){
        this.insertStmt.bindLong(1, information.getIndex());
        this.insertStmt.bindString(2, information.getNome());
        this.insertStmt.bindString(3, information.getTipo());
        Log.d("INSERT", String.valueOf(information.getIndex()));
        return this.insertStmt.executeInsert();
    }

    public void deleteAll(){
        this.db.delete(TABLE_NAME, null, null);
    }

    public List<Information> selectAll(){
        List<Information> list = new ArrayList<Information>();
        Cursor cursor = this.db.query(TABLE_NAME, new String[]{"_id", "name", "tipo"}, null, null, null, null, "_id asc");
        Log.d("CURSOR", String.valueOf(cursor.getCount()));
        if(cursor.moveToFirst()){
            do{
                int index = cursor.getInt(0);
                String nome = cursor.getString(1);
                String tipo = cursor.getString(2);
                Information info = new Information(index, nome, tipo);
                Log.d("MOVE", String.valueOf(index));
                list.add(info);
            }while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()){
            cursor.isClosed();
        }
        Log.d("TAGATG", String.valueOf(list.size()));
        return list;
    }

    private static class OpenHelper extends SQLiteOpenHelper{

        OpenHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + "(_id INTEGER PRIMARY KEY NOT NULL, name TEXT NOT NULL, tipo TEXT NOT NULL)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("Example", "Upgrading database, this will drop tables and recreate.");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
