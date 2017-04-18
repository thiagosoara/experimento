package com.example.root.experimento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.root.experimento.dao.StudentDAO;
import com.example.root.experimento.model.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final int STUDENT_RESQUEST_CODE = 200;


    List<Student> students = new ArrayList<Student>();
    private ArrayAdapter<String> adapter;
    private ListView lvStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Pegar Referência  do ListView e Button
        lvStudents = (ListView) findViewById(R.id.lv_students);
        Button btAdd = (Button) findViewById(R.id.bt_add);

        //Popular a Listagem
        /*adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,students);
        lvStudents.setAdapter(adapter); */

        loadStudents();


        //Adicionar comportamento para o botão add.
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, FormStudentActivity.class);
                startActivityForResult(it,STUDENT_RESQUEST_CODE);
            }
        });
    }

    private void loadStudents() {

        StudentDAO dao = new StudentDAO(this);
        students = dao.getAllStudents();

        List<String> studentsNames = new ArrayList<String>();

        for (Student student : students) {
            studentsNames.add(student.getName());
        }

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,studentsNames);
        lvStudents.setAdapter(adapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadStudents();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == STUDENT_RESQUEST_CODE){
                Student student = (Student) data.getSerializableExtra("student");
                //students.add(student.getName());
                StudentDAO dao = new StudentDAO(this);
                dao.insert(student);

            }
        }
    }
}
