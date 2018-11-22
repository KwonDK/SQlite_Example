package kdkproject.com.sqliteexample;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import kdkproject.com.sqliteexample.DataBase.DBOpenHelper;
import kdkproject.com.sqliteexample.DataBase.Information;
import kdkproject.com.sqliteexample.DataBase.MyAdapter1;

public class Saved_List extends AppCompatActivity {

    ListView mListview;
    MyAdapter1 adapter1;
    ArrayList<Information> list_itemArrayList;
    private DBOpenHelper mDbOpenHelper;
    private Cursor mCursor;
    private Information mInfoClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved__list);
        /* 위젯과 멤버변수 참조 획득 */

        mListview = (ListView)findViewById(R.id.listView);

        mDbOpenHelper = new DBOpenHelper(this);
        try {
            mDbOpenHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        list_itemArrayList = new ArrayList<Information>();

        doWhileCursorToArray();

        //값이 제대로 입력됬는지 확인하기 위해 로그를 찍어본다
        for (Information i : list_itemArrayList) {
            Log.i("TAG", "CONTENT1 = " + i.getContent1());
            Log.i("TAG", "CONTENT2 = " + i.getContent2());
            Log.i("TAG", "CONTENT3 = " + i.getContent3());
        }

        adapter1 = new MyAdapter1(Saved_List.this, list_itemArrayList);
        adapter1.notifyDataSetChanged();
        mListview.setAdapter(adapter1);

        setListview();

        Button close = (Button) findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void setListview(){
        //어댑터 만들기 (데이터와 화면을 연결)
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Read_File.class);
                intent.putExtra("saveddata",list_itemArrayList.get(position));

                startActivity(intent);
            }
        });


//리스트뷰의 아이템을 길게 눌렀을 경우 삭제하기 위해 롱클릭 리스너 따로 설정
            mListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent,
                                               View view, int position, long id) {
                    Log.i("TAG", "position = " + position);

                    mCursor.moveToPosition(position);
                    int id_val = mCursor.getInt(0);
                    boolean result = mDbOpenHelper.deleteColumn(id_val);
                    Log.i("TAG", "result = " + result);

                    if(result){
                        list_itemArrayList.remove(position);
                        adapter1.setArrayList(list_itemArrayList);
                        Toast.makeText(getApplicationContext(), "Complete", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
                    }
                    adapter1.notifyDataSetChanged();

                    return false;
                }
            }); //스와이프 삭제 기능 구현
    }

    //doWhile문을 이용하여 Cursor에 내용을 다 InfoClass에 입력 후 InfoClass를 ArrayList에 Add
    private void doWhileCursorToArray() {

        mCursor = null;
        //DB에 있는 모든 컬럼을 가져옴
        mCursor = mDbOpenHelper.getAllColumns();
        //컬럼의 갯수 확인
        Log.i("TAG", "Count = " + mCursor.getCount());

        while (mCursor.moveToNext()) {
            //InfoClass에 입력된 값을 압력
            mInfoClass = new Information(
                    mCursor.getString(mCursor.getColumnIndex("content1")),
                    mCursor.getString(mCursor.getColumnIndex("content2")),
                    mCursor.getString(mCursor.getColumnIndex("content3"))
            );
            //입력된 값을 가지고 있는 InfoClass를 InfoArray에 add
            list_itemArrayList.add(mInfoClass);
        }
    }

    //액티비티가 종료 될 때 디비를 닫아준다
    @Override
    protected void onDestroy() {
        mDbOpenHelper.close();
        super.onDestroy();
    }

}
