package com.example.getapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getapp.BraceMapActivity;
import com.example.getapp.Model.BraceMapModel;
import com.example.getapp.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class BraceMapAdapter extends RecyclerView.Adapter<BraceMapAdapter.MyViewHolder> {
    private List<BraceMapModel> braceMapList;
    private BraceMapActivity activity;
    private FirebaseFirestore firestore;

    public BraceMapAdapter(BraceMapActivity braceMapActivity, List<BraceMapModel> braceMapList) {
        this.braceMapList = braceMapList;
        activity = braceMapActivity;
    }

    @NonNull
    @Override
    public BraceMapAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.mindmap_activity_bracemap, parent, false);
        firestore = FirebaseFirestore.getInstance();
        return new BraceMapAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BraceMapAdapter.MyViewHolder holder, int position) {
        BraceMapModel braceMapModel = braceMapList.get(position);
        holder.subject.setText(braceMapModel.getSubject());
        holder.subTopic1.setText(braceMapModel.getSubtopic1());
        holder.subTopic2.setText(braceMapModel.getSubtopic2());
        holder.sub1SubTopic1.setText(braceMapModel.getSub1subtopic1());
        holder.sub1SubTopic2.setText(braceMapModel.getSub1subtopic2());
        holder.sub2SubTopic1.setText(braceMapModel.getSub2subtopic1());
        holder.sub2SubTopic2.setText(braceMapModel.getSub2subtopic2());

    }

    private boolean toBoolean(int status){
        return status != 0;
    }

    @Override
    public int getItemCount() {
        return braceMapList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView subject;
        TextView subTopic1;
        TextView subTopic2;
        TextView sub1SubTopic1;
        TextView sub1SubTopic2;
        TextView sub2SubTopic1;
        TextView sub2SubTopic2;

        public MyViewHolder(@NonNull View ItemView) {
            super(ItemView);

            subject = itemView.findViewById(R.id.btnHome);
            subTopic1 = itemView.findViewById(R.id.subtopic1);
            subTopic2 = itemView.findViewById(R.id.subtopic2);
            sub1SubTopic1 = itemView.findViewById(R.id.sub1subtopic1);
            sub1SubTopic2 = itemView.findViewById(R.id.sub1subtopic2);
            sub2SubTopic1 = itemView.findViewById(R.id.sub2subtopic1);
            sub2SubTopic2 = itemView.findViewById(R.id.sub2subtopic2);
        }
    }
}
