package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import cdi.appresavion.DatabaseHandler;


/**
 * Created by RENAUD on 07/09/2016.
 */
public abstract class DAOBase {
    protected final static int VERSION = 1;
    protected final static String DATABASE_NAME = "database.db";

    protected SQLiteDatabase mDb = null;
    protected DatabaseHandler mHandler = null;

    public DAOBase(Context context){
        this.mHandler = new DatabaseHandler(context, DATABASE_NAME, null, VERSION);
    }

    public SQLiteDatabase open(){
        mDb = mHandler.getWritableDatabase();
        return mDb;
    }

    public void close(){
        mDb.close();
    }

    public SQLiteDatabase getDb(){
        return mDb;
    }
}
