package com.example.root.experimento.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.root.experimento.MainActivity;
import com.example.root.experimento.R;
import com.example.root.experimento.dao.StudentDAO;
import com.example.root.experimento.model.Student;

/**
 * Created by root on 15/05/17.
 */

public class StudentFormFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form_student,container, false);
        //Pegar a referência das Views


        final EditText etName = (EditText) view.findViewById(R.id.et_student_name);
        final EditText etEmail = (EditText) view.findViewById(R.id.et_student_email);
        final EditText etPhone = (EditText) view.findViewById(R.id.et_student_phone);

        Bundle bundle = getArguments();
        if (bundle != null){
            Student student = (Student) bundle.getSerializable("student");
            //carregar nome
            etName.setText(student.getName());
            etEmail.setText(student.getEmail());
            etPhone.setText(student.getPhone());
            //carregar email
            //carregar telefone
        }


        Button btSave = (Button) view.findViewById(R.id.bt_save);


        //Setar para botão salvar os dados
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentDAO dao = new StudentDAO(getActivity());
                Student student = new Student();
                student.setName(etName.getText().toString());
                student.setEmail(etEmail.getText().toString());
                student.setPhone(etPhone.getText().toString());
                dao.insert(student);
                OnRefreshFormOK activity = (OnRefreshFormOK) getActivity();
                activity.refresh();
                if (!isLandScape())
                    getActivity().finish();
                Toast.makeText(getActivity(),"Usuário inserido com sucesso!", Toast.LENGTH_SHORT).show();

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

    public interface OnRefreshFormOK{
        public void refresh();
    }

}
