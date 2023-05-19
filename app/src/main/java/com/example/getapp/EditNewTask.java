package com.example.getapp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EditNewTask extends BottomSheetDialogFragment {
    public static final String TAG = "AddNewTask";
    private TextView setDueDate;
    private EditText mTaskEdit;
    private Button saveBtn;
    private Button cancelBtn;
    private FirebaseFirestore firestore;
    private Context context;
    private String duedate = "";

    private RadioButton lowBtn;
    private RadioButton mediumBtn;
    private RadioButton highBtn;
    private String priority;
    private String id = "";
    private String duedateUpdate = "";
    private EditText description;
    public static EditNewTask newInstance(){
        return new EditNewTask();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_edittask, container, true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setDueDate = view.findViewById(R.id.editTextDate);
        mTaskEdit = view.findViewById(R.id.editTextTextPersonName);
        saveBtn = view.findViewById(R.id.addbtn);
        cancelBtn = view.findViewById(R.id.cancelbtn);
        lowBtn = view.findViewById(R.id.lowbtn);
        mediumBtn = view.findViewById(R.id.mediumbtn);
        highBtn = view.findViewById(R.id.highbtn);
        description = view.findViewById(R.id.editTextTextMultiLine);
        firestore = FirebaseFirestore.getInstance();


        boolean isUpdate = false;

        final Bundle bundle = getArguments();
        if(bundle != null){
            isUpdate = true;
            String task = bundle.getString("task");
            id = bundle.getString("id");
            duedateUpdate = bundle.getString("due");

            mTaskEdit.setText(task);
            setDueDate.setText(duedate);

            if(task.length() > 0){
                saveBtn.setEnabled(false);
                saveBtn.setBackgroundResource(R.drawable.btn_disabbled);
            }
        }

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        mTaskEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")) {
                    saveBtn.setEnabled(false);
                    saveBtn.setBackgroundResource(R.drawable.btn_disabled);
                } else {
                    saveBtn.setEnabled(true);
                    saveBtn.setBackgroundResource(R.drawable.btn_add);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        setDueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int MONTH = calendar.get(Calendar.MONTH);
                int YEAR = calendar.get(Calendar.YEAR);
                int DAY = calendar.get(Calendar.DATE);

                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1 = i1 + 1;
                        setDueDate.setText(i2 + "/" + i1 + "/" + i);
                        duedate = i2 + "/" + i1 + "/" + i;
                    }
                }, YEAR, MONTH, DAY);
                datePickerDialog.show();
            }
        });

        boolean finalIsUpdate = isUpdate;
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task = mTaskEdit.getText().toString();
                String desc = description.getText().toString();

                if(finalIsUpdate){
                    firestore.collection("task").document(id).update("task", task, "due", duedate, "description", desc);
                    Toast.makeText(context, "Task Updated", Toast.LENGTH_SHORT).show();

                }else {
                    if (task.isEmpty()) {
                        Toast.makeText(context, "Empty Task is not allowed", Toast.LENGTH_SHORT).show();
                    } else {
                        if (lowBtn.isChecked()) {
                            priority = "Low";
                        } else if (mediumBtn.isChecked()) {
                            priority = "Medium";
                        } else if (highBtn.isChecked()) {
                            priority = "High";
                        } else {
                            priority = "Priority not set";
                        }

                        Map<String, Object> taskMap = new HashMap<>();
                        taskMap.put("task", task);
                        taskMap.put("due", duedate);
                        taskMap.put("priority", priority);
                        taskMap.put("status", 0);
                        taskMap.put("description", desc);
                        taskMap.put("time", FieldValue.serverTimestamp());

                        firestore.collection("task").add(taskMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(context, "Task Saved", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                dismiss();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();
        if(activity instanceof OnDialogCloseListener){
            ((OnDialogCloseListener)activity).onDialogClose(dialog);
        }
    }
}
