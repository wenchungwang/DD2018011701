package com.program.play.dd2018011701;

/**
 * Created by Student on 2018/1/17.
 */

import java.util.ArrayList;
import java.util.regex.Pattern;  //?? check thomas wang 20180117
/**
 * Created by Student on 2018/1/17.
 */

public class StudentScoreDAO {
    public ArrayList<Student> mylist;
    public StudentScoreDAO()
    {
        mylist = new ArrayList<>();
    }
    public boolean  add(Student s)
    {
        mylist.add(s);
        return true;
    }
    public ArrayList<Student> getList()
    {
        return mylist;
    }

    public Student getStudent(int id)
    {
        for (Student s : mylist)
        {
            if (s.id == id)
            {
                return s;
            }
        }
        return null;
    }

    public boolean  update(Student t)
    {
       for (Student s : mylist)
        {
           if (t.id == s.id)
            {
                t.name = s.name;
                t.score = s.score;
                return true;
            }
        }
        return false;
    }

    public boolean  delete(int id)
    {
        for(int i=0;i<mylist.size();i++)
        {
            if(mylist.get(i).id ==id)
            {
                mylist.remove(i);
                return true;
            }
        }
        return false;
    }

}
