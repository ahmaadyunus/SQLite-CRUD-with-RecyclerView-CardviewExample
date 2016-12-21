package com.example.ahmaadyunus.sqlite;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.message;

public class HomeActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    private RecyclerView rv_student;
    private List<Student> studentList = new ArrayList<>();
    private StudentAdapter mAdapter;
    private String id, name, surname, mark;
    private String name_update,surname_update,mark_update;
    EditText name_input, surname_input, mark_input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        myDB = new DatabaseHelper(this);

        studentList();
        rv_student = (RecyclerView) findViewById(R.id.rv_student);
        mAdapter = new StudentAdapter(studentList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_student.setLayoutManager(mLayoutManager);
        rv_student.setItemAnimator(new DefaultItemAnimator());
        rv_student.setAdapter(mAdapter);
        rv_student.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), rv_student, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Toast.makeText(getApplicationContext(), studentList.get(position).getId() + " is clicked!", Toast.LENGTH_SHORT).show();
                name_update=studentList.get(position).getName();
                surname_update=studentList.get(position).getSurname();
                mark_update=studentList.get(position).getMark();
                update_student(studentList.get(position).getId(),name_update,surname_update,mark_update);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), studentList.get(position).getId() + " is long pressed!", Toast.LENGTH_SHORT).show();

            }
        }));

    }
    public void studentList(){
        Cursor students = myDB.list_student();
        if (students.getCount() == 0) {

            return;
        }
        while (students.moveToNext()) {
            id=students.getString(0);
            name=students.getString(1);
            surname=students.getString(2);
            mark=students.getString(3);
            Student student = new Student(id, name, surname, mark);
            studentList.add(student);
        }

    }

    public void addStudent(View view){
        add_student();
    }
    public void add_student() {
        LayoutInflater inflater = getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.layout_input, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.add_student);
        builder.setView(dialoglayout);
        builder.setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Dialog dialog1 = (Dialog) dialog;
                name_input = (EditText) dialog1.findViewById(R.id.name_input_ET);
                surname_input = (EditText) dialog1.findViewById(R.id.surname_input_ET);
                mark_input = (EditText) dialog1.findViewById(R.id.mark_input_ET);
                boolean result = myDB.save_student(name_input.getText().toString(),
                        surname_input.getText().toString(),
                        mark_input.getText().toString()
                );
                if (result) {
                    Toast.makeText(HomeActivity.this, "Success Add Student", Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(HomeActivity.this, "Fails Add Student", Toast.LENGTH_LONG).show();
                }
                finish();
                startActivity(getIntent());
            }
        });
        builder.show();

    }
    public void update_student(final String position, String name, String surname, String mark){
        LayoutInflater inflater = getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.layout_input, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        name_input = (EditText) dialoglayout.findViewById(R.id.name_input_ET);
        surname_input = (EditText) dialoglayout.findViewById(R.id.surname_input_ET);
        mark_input = (EditText) dialoglayout.findViewById(R.id.mark_input_ET);
        name_input.setText(name);
        surname_input.setText(surname);
        mark_input.setText(mark);

        builder.setTitle(R.string.update);
        builder.setView(dialoglayout);

        builder.setPositiveButton(R.string.update, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Dialog dialog2 = (Dialog) dialog;
                name_input = (EditText) dialog2.findViewById(R.id.name_input_ET);
                surname_input = (EditText) dialog2.findViewById(R.id.surname_input_ET);
                mark_input = (EditText) dialog2.findViewById(R.id.mark_input_ET);
                boolean result = myDB.update_student(position,name_input.getText().toString(),
                        surname_input.getText().toString(),
                        mark_input.getText().toString()
                );
                if (result) {
                    Toast.makeText(HomeActivity.this, "Success Update Student with ID : " +position, Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(HomeActivity.this, "Fails Add Student", Toast.LENGTH_LONG).show();
                }
                finish();
                startActivity(getIntent());
            }
        });
        builder.show();
    }

}
