package com.dingyl.xiaominote.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dingyl.xiaominote.data.BaseData;
import com.dingyl.xiaominote.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class DBUtil {

    private static final String KEY_ID = "id";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DATE = "date";

    private SQLiteDatabase database;
    private Context context;
    private DBHelper helper;

    public DBUtil(Context context){
        this.context = context;
    }

    public void openDateBase(){
        helper = new DBHelper(context);
        try {
            database = helper.getWritableDatabase();
        }catch (Exception e){
            database = helper.getReadableDatabase();
        }
    }

    public void closeDatebase(){
        if(database != null){
            database.close();
        }
    }

    public void insertData(BaseData baseData){
        ContentValues values = new ContentValues();
        values.put(KEY_CONTENT,baseData.getContent());
        values.put(KEY_DATE,baseData.getDate());
        database.insert(Constants.TABLE_BASE,null,values);
    }

    public void deleteData(int id){
        database.delete(Constants.TABLE_BASE,KEY_ID + "=" + id,null);
    }

    public void deleteAllData(){
        database.delete(Constants.TABLE_BASE,null,null);
    }

    public void updateData(int id,BaseData baseData){
        ContentValues values = new ContentValues();
        values.put(KEY_CONTENT,baseData.getContent());
        values.put(KEY_DATE,baseData.getDate());
        database.update(Constants.TABLE_BASE,values,KEY_ID + "=" + id,null);
    }

    public BaseData queryData(int id){
        Cursor cursor = database.query(Constants.TABLE_BASE,new String[]{KEY_CONTENT,KEY_DATE}
                ,KEY_ID + "=" + id,null,null,null,null);
        return convert(cursor).get(0);
    }

    public ArrayList<BaseData> queryAllData(){
        Cursor cursor = database.query(Constants.TABLE_BASE,new String[]{KEY_CONTENT,KEY_DATE}
        ,null,null,null,null,null);
        return convert(cursor);
    }

    private ArrayList<BaseData> convert(Cursor cursor){
        ArrayList<BaseData> list = new ArrayList<>();
        while (cursor.moveToNext()){
            BaseData baseData = new BaseData();
            baseData.setId(cursor.getInt(0));
            baseData.setContent(cursor.getString(cursor.getColumnIndex(KEY_CONTENT)));
            baseData.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            list.add(baseData);
        }
        return list;
    }
    class DBHelper extends SQLiteOpenHelper{

        private static final String BASE_DATE_TABLE_CREATE_SQL = "create TABLE "
                + Constants.TABLE_BASE
                + "("
                + "id integer primary key autoincrement,"
                + "content text,"
                + "date text"
                + ");";

        public DBHelper(Context context){
            super(context, Constants.DB_NAME,null,Constants.DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(BASE_DATE_TABLE_CREATE_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
