package com.example.root.experimento;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.root.experimento.dao.StudentDAO;
import com.example.root.experimento.fragments.StudentListFragment;
import com.example.root.experimento.model.Student;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.fragment;

public class MainActivity extends AppCompatActivity {



    StudentListFragment fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentList = (StudentListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_student_list);

    }

    public void loadStudents() {
        fragmentList.loadStudents();
    }


}
