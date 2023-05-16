package com.example.getapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.getapp.MainActivity;
import com.example.getapp.Model.ToDoModel;
import com.example.getapp.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {

    private List<ToDoModel> todolist;
    private MainActivity activity;
    private FirebaseFirestore firestore;


    public ToDoAdapter(MainActivity mainActivity, List<ToDoModel> todolist){
        this.todolist = todolist;
        activity = mainActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.drawer_task_instance, parent, false);
        firestore = FirebaseFirestore.getInstance();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ToDoModel toDoModel = todolist.get(position);
        holder.mTaskName.setText(toDoModel.getTask());
        holder.mDueDate.setText(toDoModel.getDue());
        holder.mPriority.setText(toDoModel.getPriority());
        holder.mCheckBox.setChecked(toBoolean(toDoModel.getStatus()));

        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    firestore.collection("task").document(toDoModel.TaskId).update("status", 1);
                }else{
                    firestore.collection("task").document(toDoModel.TaskId).update("status", 0);
                }
            }
        });

    }

    private boolean toBoolean(int status){
        return status != 0;
    }

    @Override
    public int getItemCount() {
        return todolist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mDueDate;
        TextView mTaskName;
        CheckBox mCheckBox;
        TextView mPriority;
        public MyViewHolder(@NonNull View ItemView) {
            super(ItemView);

            mDueDate = itemView.findViewById(R.id.dueDate);
            mCheckBox = itemView.findViewById(R.id.checkBox3);
            mTaskName = itemView.findViewById(R.id.taskName);
            mPriority = itemView.findViewById(R.id.priority);
        }
    }
}