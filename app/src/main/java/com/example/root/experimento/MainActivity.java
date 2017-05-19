package com.example.root.experimento;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.root.experimento.dao.StudentDAO;
import com.example.root.experimento.fragments.StudentFormFragment;
import com.example.root.experimento.fragments.StudentListFragment;
import com.example.root.experimento.model.Student;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.fragment;
import static com.example.root.experimento.R.id.toolbar;

public class MainActivity extends AppCompatActivity implements StudentFormFragment.OnRefreshFormOK {



    StudentListFragment fragmentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentList = (StudentListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_student_list);

    }


    @Override
    public void refresh() {
        fragmentList.loadStudents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_visualize:
                Intent it = new Intent(this, ViewpagerActivity.class);
                startActivity(it);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
