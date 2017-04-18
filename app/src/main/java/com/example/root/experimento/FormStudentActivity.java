package com.example.root.experimento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.root.experimento.model.Student;

public class FormStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_student);

        //Pegar a referência das Views
        final EditText etName = (EditText) findViewById(R.id.et_student_name);
        final EditText etEmail = (EditText) findViewById(R.id.et_student_email);
        final EditText etPhone = (EditText) findViewById(R.id.et_student_phone);
        Button btSave = (Button) findViewById(R.id.bt_save);


        //Setar para botão salvar os dados
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                student.setName(etName.getText().toString());
                student.setEmail(etEmail.getText().toString());
                student.setPhone(etPhone.getText().toString());
                //Toast.makeText(FormStudentActivity.this,"Nome: "+student.getName()+"\nEmail: "+student.getEmail()+"\nPhone: "+student.getPhone(),Toast.LENGTH_LONG).show();
                Intent it = getIntent().putExtra("student",student);
                setResult(RESULT_OK, it);
                finish();
            }
        });




    }
}
