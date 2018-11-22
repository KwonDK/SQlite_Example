package kdkproject.com.sqliteexample.DataBase;

import android.provider.BaseColumns;

public final class DataBaseFile {
    public static final class CreateDB implements BaseColumns {
        public static final String CONTENT1 = "content1";
        public static final String CONTENT2 = "content2";
        public static final String CONTENT3 = "content3";
        public static final String _TABLENAME0 = "usertable";
        public static final String _CREATE0 = "create table if not exists " + _TABLENAME0 + "("
                + _ID + " integer primary key autoincrement, "
                + CONTENT1 + " text not null , "
                + CONTENT2 + " text not null , "
                + CONTENT3 + " text not null ); ";
    }
}