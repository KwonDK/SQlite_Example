package kdkproject.com.sqliteexample;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kdkproject.com.sqliteexample.DataBase.DBOpenHelper;
import kdkproject.com.sqliteexample.DataBase.Information;

public class New_File extends AppCompatActivity{

    EditText mycontent1,mycontent2, mycontent3;
    private DBOpenHelper mDbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__file);

        init();
        mDbOpenHelper = new DBOpenHelper(this);
        try {
            mDbOpenHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Button close = (Button) findViewById(R.id.CloseBtn);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button save = (Button) findViewById(R.id.SaveBtn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToList();
            }
        });
    }

    public void init(){
        mycontent1 = (EditText)findViewById(R.id.mycontent1);
        mycontent2 = (EditText)findViewById(R.id.mycontent2);
        mycontent3 = (EditText)findViewById(R.id.mycontent3);
    }

    public void saveToList(){

        //객체로 저장 후 객체를 Saved_List로 전달
        Information inputed = getIntent().getParcelableExtra("inputdata");
        inputed.information(mycontent1.getText().toString(),mycontent2.getText().toString(),mycontent3.getText().toString());

        mDbOpenHelper.insertColumn(inputed.getContent1(),inputed.getContent2(),inputed.getContent3());

        Intent intent2 = new Intent(this, Saved_List.class);
        startActivity(intent2);

        //액티비티가 종료 될 때 디비를 닫아준다
        finish();

    }
}
