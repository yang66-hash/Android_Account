package com.example.asexperiment_end.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.asexperiment_end.Bean.RecordBean;

import java.util.LinkedList;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static String TAG ="RecordDatabaseHelper";

    public static final String DB_NAME ="account.db";
    public static final String TABLE_NAME="record";
    private static final String CREATE_TABLE="create table record ("+
                                             "id integer primary key autoincrement,"+
                                             "uuid text,"+
                                             "money double,"+
                                             "type integer,"+
                                             "remark text,"+
                                             "category text,"+
                                             "date date," +
                                               "time integer)";
    public DataBaseHelper(@Nullable Context context, @Nullable String DB_NAME, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addRecord(RecordBean recordBean){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("uuid",recordBean.getUuid());
        values.put("money",recordBean.getMoney());
        values.put("remark",recordBean.getRemark());
        values.put("type",recordBean.getType());
        values.put("category",recordBean.getCategory());
        values.put("date",recordBean.getDate());
        values.put("time",recordBean.getTimesstamp());
        sqLiteDatabase.insert(TABLE_NAME,null,values);
        values.clear();
        sqLiteDatabase.close();
        Log.d(TAG,recordBean.getUuid()+"被成功添加。");
    }

    public void delete(String uuid){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
    }



    public LinkedList<RecordBean> readRecords(String dateStr){
        LinkedList<RecordBean> records = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        //取出当天的记录
        Cursor cursor = db.rawQuery("select DISTINCT * from Record where date = ? order by type desc,time asc",new String[]{dateStr});
        if (cursor.moveToFirst()){
            do{
                String uuid = cursor.getString(cursor.getColumnIndex("uuid"));
                int type = cursor.getInt(cursor.getColumnIndex("type"));
                String category = cursor.getString(cursor.getColumnIndex("category"));
                String remark = cursor.getString(cursor.getColumnIndex("remark"));
                double money = cursor.getDouble(cursor.getColumnIndex("money"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                long timeStamp = cursor.getLong(cursor.getColumnIndex("time"));

                RecordBean record = new RecordBean();
                record.setUuid(uuid);
                if (type==1)
                    record.setType(RecordBean.RecordType.RECORD_TYPE_EXPENSE);
                else record.setType(RecordBean.RecordType.RECORD_TYPE_INCOME);
                record.setCategory(category);
                record.setRemark(remark);
                record.setMoney(money);
                record.setDate(date);
                record.setTimesstamp(timeStamp);
                records.add(record);
                Log.d(TAG+":",String.valueOf(records.size()));
                Log.d(TAG+":",record.toString());

            }while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return records;
    }
    //获取数据库中记录的日期数量，即有几天记账了。
    public LinkedList<String> getAvaliableDate(){

        LinkedList<String> dates = new LinkedList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery( "select DISTINCT * from Record order by date asc",null);
        if (cursor.moveToFirst()){
            do{
                String date = cursor.getString(cursor.getColumnIndex("date"));
                if (!dates.contains(date)){
                    dates.add(date);
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
        return dates;
    }

    public int getSumByType(int type,String date){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select sum("+"money"+") from Record where date=? and type=?",new String[]{date, String.valueOf(type)});
        if (cursor.moveToFirst()){
            return cursor.getInt(0);
        }
        cursor.close();
        sqLiteDatabase.close();
        return -1;
    }

}
