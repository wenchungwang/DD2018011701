package com.program.play.dd2018011701;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.program.play.dd2018011701.Student;

public class ListActivity extends AppCompatActivity {
    Student s;
    TextView tv1, tv2, tv3;
    int id;
    boolean Fastback=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        id = getIntent().getIntExtra("id", 0);
//        s = MainActivity.dao.getStudent(id);
        tv1 = (TextView) findViewById(R.id.textView4);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
//        tv1.setText(String.valueOf(s.id));
//        tv2.setText(s.name);
//        tv3.setText(String.valueOf(s.score));

    }

    @Override
    protected void onResume() {
        super.onResume();
        s = MainActivity.dao.getStudent(id);
        tv1.setText(String.valueOf(s.id));
        tv2.setText(s.name);
        tv3.setText(String.valueOf(s.score));

        if(Fastback)
        {
            finish();
        }

    }

    public void clickedit(View v)
    {
        Intent it = new Intent(ListActivity.this, EditActivity.class);
        Fastback =true;
        it.putExtra("id",id);
        startActivity(it);
    }

    public void clickdel(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
        builder.setTitle("刪除確認");
        builder.setMessage("確認要刪除本筆資料嗎?");
        builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.dao.delete(id);
                        finish();
                    }
                });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
                builder.show();
    }


    public void clickback(View v)
    {

        finish();
    }

}
