package com.example.root.experimento.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.root.experimento.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 17/04/17.
 */

public class StudentDAO extends SQLiteOpenHelper{

    public StudentDAO(Context context) {
        super(context, "experimento", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Students (name TEXT NOT NULL, email TEXT NOT NULL, phone TEXT NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Students;";
        db.execSQL(sql);
        onCreate(db);
    }

    //INSERT
    public void insert(Student student){
        String sql = "INSERT INTO Students (name,email,phone) VALUES ('" +student.getName()+"','"+student.getEmail()+"','"+student.getPhone()+"');";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }

    //GetAllStudents
    public List<Student> getAllStudents(){
        String sql = "SELECT * FROM Students;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        List<Student> students = new ArrayList<Student>();

        while(c.moveToNext()){
            Student student = new Student();
            student.setName(c.getString(c.getColumnIndex("name")));
            student.setEmail(c.getString(c.getColumnIndex("email")));
            student.setPhone(c.getString(c.getColumnIndex("phone")));
            students.add(student);
        }

        db.close();

        return students;
    }
}
