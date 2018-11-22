package kdkproject.com.sqliteexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import kdkproject.com.sqliteexample.DataBase.Information;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newfile = (Button) findViewById(R.id.NewBtn);
        newfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),New_File .class);
                Information s1 = new Information("","","");
                intent.putExtra("inputdata",s1); // 내용을 담기위한 그릇, 세가지 데이터 그릇을 담아서 간다.
                startActivity(intent);
            }
        });

        Button listfile = (Button) findViewById(R.id.ListBtn);
        listfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), Saved_List.class);
                startActivity(intent2);
            }
        }); // 저장된 LIST를 호출하는 버튼

    }
}
