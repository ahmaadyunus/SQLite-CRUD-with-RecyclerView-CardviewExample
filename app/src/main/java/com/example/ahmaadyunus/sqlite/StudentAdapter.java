package com.example.ahmaadyunus.sqlite;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ahmaadyunus on 21/12/16.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private List<Student> studentList;
    public StudentAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }
    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content, parent, false);

        return new StudentViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        holder.id.setText(studentList.get(position).getId());
        holder.name.setText(studentList.get(position).getName());
        holder.surname.setText(studentList.get(position).getSurname());
        holder.mark.setText(studentList.get(position).getMark());
    }
    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView id;
        public TextView name;
        public TextView surname;
        public TextView mark;

        public StudentViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.id_tv);
            name = (TextView) view.findViewById(R.id.name_tv);
            surname =(TextView)view.findViewById(R.id.surname_tv);
            mark = (TextView) view.findViewById(R.id.mark_tv);
        }
    }
}
