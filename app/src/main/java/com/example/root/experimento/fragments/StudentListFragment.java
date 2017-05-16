package com.example.root.experimento.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.root.experimento.FormStudentActivity;
import com.example.root.experimento.MainActivity;
import com.example.root.experimento.R;
import com.example.root.experimento.dao.StudentDAO;
import com.example.root.experimento.model.Student;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by root on 15/05/17.
 */

public class StudentListFragment extends Fragment {



    List<Student> students = new ArrayList<Student>();
    private ArrayAdapter<String> adapter;
    private ListView lvStudents;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_list, container, false );
        //Pegar Referência  do ListView e Button
        lvStudents = (ListView) view.findViewById(R.id.lv_students);
        Button btAdd = (Button) view.findViewById(R.id.bt_add);

        //Popular a Listagem
        /*adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,students);
        lvStudents.setAdapter(adapter); */

        loadStudents();

        lvStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                loadStudentForm(students.get(position));
            }
        });




        //Adicionar comportamento para o botão add.
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isLandScape()){
                    loadStudentForm(null);
                }else{
                    Intent it = new Intent(getActivity(), FormStudentActivity.class);
                    startActivity(it);
                }
            }
        });
        return view;
    }

    public boolean isLandScape(){
        Configuration configuration = getResources().getConfiguration();
        if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
            return true;
        return false;
    }

    private void loadStudentForm(Student student) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction tx = manager.beginTransaction();
        Fragment fragment = new StudentFormFragment();
        if(student != null){
            Bundle bundle = new Bundle();
            bundle.putSerializable("student", student);
            fragment.setArguments(bundle);
        }
        tx.replace(R.id.fragment_student_form, fragment);
        tx.addToBackStack(null);
        tx.commit();
    }

    public void loadStudents() {

        StudentDAO dao = new StudentDAO(getActivity());
        students = dao.getAllStudents();

        List<String> studentsNames = new ArrayList<String>();

        for (Student student : students) {
            studentsNames.add(student.getName());
        }

        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,studentsNames);
        lvStudents.setAdapter(adapter);
    }


    @Override
    public void onStart() {
        super.onStart();
        loadStudents();
    }


}
