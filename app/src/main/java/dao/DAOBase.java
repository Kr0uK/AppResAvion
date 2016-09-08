package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import cdi.appresavion.DatabaseHandler;


/**
 * Created by RENAUD on 07/09/2016.
 */
public class DAOBase {
    public final static String DATABASE_NAME = "database.db";
    public final static int VERSION = 1;

    static SQLiteDatabase mDb = null;
    static DatabaseHandler mHandler = null;

    public DAOBase(Context context){
        this.mHandler = new DatabaseHandler(context, DATABASE_NAME, VERSION);
    }

    public static SQLiteDatabase getWDb(){
        mDb = mHandler.getWritableDatabase();
        return mDb;
    }

    public static SQLiteDatabase rDb(){
        mDb = mHandler.getReadableDatabase();
        return mDb;
    }

    public void close(){
        mDb.close();
    }

    public SQLiteDatabase getDb(){
        return mDb;
    }
}
