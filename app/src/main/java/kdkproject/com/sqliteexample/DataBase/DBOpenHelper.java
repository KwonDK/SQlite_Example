package kdkproject.com.sqliteexample.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper {
    private static final String DATABASE_NAME = "CustomDATABASE(SQLITE).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        //최초 DB를 만들 때 한번만 호출
        @Override
        public void onCreate(SQLiteDatabase db) {
            if(db != null){
                db.execSQL(DataBaseFile.CreateDB._CREATE0);
            }
        }

        //버전이 업데이트 되었을 경우 DB를 다시 만들어주는 메소드
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //버전이 업데이트 되었을 경우 DB를 다시 만들어주는 메소드
            db.execSQL("DROP TABLE IF EXISTS " + DataBaseFile.CreateDB._TABLENAME0);
            onCreate(db);
        }
    }

    public DBOpenHelper(Context context) {
        this.mCtx = context;
    }

    public DBOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void create() {
        mDBHelper.onCreate(mDB);
    }

    public void close() {
        mDB.close();
    }

    public long insertColumn(String content1, String content2, String content3){
        ContentValues values = new ContentValues();
        values.put(DataBaseFile.CreateDB.CONTENT1, content1);
        values.put(DataBaseFile.CreateDB.CONTENT2, content2);
        values.put(DataBaseFile.CreateDB.CONTENT3, content3);
        return mDB.insert(DataBaseFile.CreateDB._TABLENAME0, null, values);
    }
    public boolean updateColumn(long id, String content1, String content2, String content3){
         ContentValues values = new ContentValues();
        values.put(DataBaseFile.CreateDB.CONTENT1, content1);
        values.put(DataBaseFile.CreateDB.CONTENT2, content2);
        values.put(DataBaseFile.CreateDB.CONTENT3, content3);
        return mDB.update(DataBaseFile.CreateDB._TABLENAME0, values, "_id=" + id, null) > 0;
    } // DB를 업데이트합니다.

    public void deleteAllColumns() {
        mDB.delete(DataBaseFile.CreateDB._TABLENAME0, null, null);
    }// 전부 지우기

    public boolean deleteColumn(long id){
        return mDB.delete(DataBaseFile.CreateDB._TABLENAME0, "_id="+id, null) > 0;
    }// DB를 제거함

    public Cursor selectColumns(){
        return mDB.query(DataBaseFile.CreateDB._TABLENAME0, null, null, null, null, null, null);
    }// DB를 선택함


    public Cursor sortColumn(String sort){
        Cursor c = mDB.rawQuery( "SELECT * FROM usertable ORDER BY " + sort + ";", null);
        return c;
    }// 행을 정렬함

    public Cursor getAllColumns() {
        return mDB.query(DataBaseFile.CreateDB._TABLENAME0, null, null, null, null, null, null);
    }//커서 전체를 선택함

    public Cursor getColumn(long id) {
        Cursor c = mDB.query(DataBaseFile.CreateDB._TABLENAME0, null,
                "_id="+id, null, null, null, null);
        if (c != null && c.getCount() != 0)
            c.moveToFirst();
        return c;
    }//ID 컬럼 얻어오기
}
