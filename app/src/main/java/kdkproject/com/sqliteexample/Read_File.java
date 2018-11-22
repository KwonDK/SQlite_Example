package kdkproject.com.sqliteexample;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import kdkproject.com.sqliteexample.DataBase.DBOpenHelper;
import kdkproject.com.sqliteexample.DataBase.Information;

public class Read_File extends AppCompatActivity {

    private DBOpenHelper mDbOpenHelper;
    TextView content1;
    TextView content2;
    TextView content3;

    Intent intent;
    Cursor mCursor;

    String con1;
    String con2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read__file);

        mDbOpenHelper = new DBOpenHelper(this);
        try {
            mDbOpenHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        content1 = (TextView) findViewById(R.id.mycontentView1);
        content2 = (TextView) findViewById(R.id.mycontentView2);
        content3 = (TextView) findViewById(R.id.mycontentView3); // 선택된 곳에서 데이터를 알맞게 가져온다.

        intent = getIntent();

        Information data = intent.getParcelableExtra("saveddata");

        mDbOpenHelper.create();

        mCursor = mDbOpenHelper.selectColumns();

        con1 = data.getContent1();
        con2 = data.getContent2();

        while (mCursor.moveToNext()) {
            String Content1 = mCursor.getString(mCursor.getColumnIndex("content1"));
            String Content2 = mCursor.getString(mCursor.getColumnIndex("content2"));
            if (Content1.equals(con1) && Content2.equals(con2)) {
                content1.setText(mCursor.getString(mCursor.getColumnIndex("content1")));
                content2.setText(mCursor.getString(mCursor.getColumnIndex("content2")));
                content3.setText(mCursor.getString(mCursor.getColumnIndex("content3")));
            }
        }

        mDbOpenHelper.create();

        mCursor = mDbOpenHelper.selectColumns();


    }
}
