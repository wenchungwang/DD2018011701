package com.program.play.dd2018011701;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Student on 2018/1/18.
 */

public class StudentCloudDAO implements StudentDAO{
    public ArrayList<Student> mylist;
    Context context;
    FirebaseDatabase database;
    DatabaseReference myRef;
    public StudentCloudDAO(final Context context)
    {
        this.context = context;
        mylist = new ArrayList<>();     ////
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("scores");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Gson gson = new Gson();
                mylist = gson.fromJson(value, new TypeToken<ArrayList<Student>>(){}.getType());
////
                if (mylist == null)
                {
                    mylist = new ArrayList<>();
                }
////
                ((MainActivity) context).refreshData(); //
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
/*
        if (mylist == null)
        {
            mylist = new ArrayList<>();

//            String value = dataSnapshot.getValue(String.class);
//            Gson gson = new Gson();
//            mylist = gson.fromJson(value, new TypeToken<ArrayList<Student>>(){}.getType());

        }
*/
    }
    public boolean add(Student s)
    {
        if (mylist == null)
        {
            mylist = new ArrayList<>();
        }

        mylist.add(s);
        saveFile();
        return true;
    }
    private void saveFile()
    {

        // Write a message to the database
        Gson gson = new Gson();
        String data = gson.toJson(mylist);


        myRef.setValue(data);
/*
        File f = new File(context.getFilesDir(), "mydata.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            Gson gson = new Gson();
            String data = gson.toJson(mylist);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
    }
/*
    private void load()
    {
        File f = new File(context.getFilesDir(), "mydata.txt");
        FileReader fr = null;
        try {
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            Gson gson = new Gson();
            mylist = gson.fromJson(str, new TypeToken<ArrayList<Student>>(){}.getType());
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
*/
    public ArrayList<Student> getList()
    {
 //       load();
        return mylist;
    }
    public Student getStudent(int id)
    {
     //   load();
        for (Student s : mylist)
        {
            if (s.id == id)
            {
                return s;
            }
        }
        return null;
    }
    public boolean update(Student s)
    {
        //load();
        for (Student t : mylist)
        {
            if (t.id == s.id)
            {
                t.name = s.name;
                t.score = s.score;
                saveFile();
                return true;
            }
        }
        return false;
    }
    public boolean delete(int id)
    {
        //load();
        for (int i=0;i<mylist.size();i++)
        {
            if (mylist.get(i).id == id)
            {
                mylist.remove(i);
                saveFile();
                return true;
            }
        }
        return false;
    }
}