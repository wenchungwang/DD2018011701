package com.program.play.dd2018011701;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.program.play.dd2018011701.Student;       //check thomas wang 20180117
import com.program.play.dd2018011701.StudentFileDAO;
import com.program.play.dd2018011701.StudentScoreDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    final public static StudentScoreDAO dao = new StudentScoreDAO();
//    public static StudentFileDAO dao;     //for old method
    public static StudentDAO dao;
//    int dbType;
    DBtype dbType;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        dao = new StudentFileDAO(this);   //for old method
      dbType =DBtype.FILE;
//        dbType = 1; // 1:記憶體 2:檔案
        dao = StudentDAOFactory.getDAOInstance(this, dbType);


    }

    @Override
    protected void onResume() {
        super.onResume();
        lv = (ListView) findViewById(R.id.listView);
        ArrayList<String> studentNames = new ArrayList<>();
        for (Student s : dao.getList())
        {
            studentNames.add(s.name);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, studentNames);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent it =new Intent(MainActivity.this, ListActivity.class);
                it.putExtra("id", dao.getList().get(position).id);
                startActivity(it);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add)
        {
            Intent it = new Intent(MainActivity.this, AddActivity.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }
}
