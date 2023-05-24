package com.example.getapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddNewMindMap extends BottomSheetDialogFragment {
    public static final String TAG = "AddNewMindMap";
    private Spinner spinner;
    private Button addBtn;
    private Button cancelBtn;
    private FirebaseFirestore firestore;
    private Context context;
    private RadioButton braceMap;
    private RadioButton bubbleMap;
    private RadioButton circleMap;
    private RadioButton treeMap;
    private String task;
    private Drawable map;
    private String mapName;

    public static AddNewMindMap newInstance() {
        return new AddNewMindMap();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_addmindmap, container, true);

    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();

        if (dialog != null) {
            View bottomSheet = dialog.findViewById(R.id.layout_addmindmap);
            bottomSheet.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        View view = getView();
        view.post(() -> {
            View parent = (View) view.getParent();
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) (parent).getLayoutParams();
            CoordinatorLayout.Behavior behavior = params.getBehavior();
            BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) behavior;
            bottomSheetBehavior.setPeekHeight(view.getMeasuredHeight());
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinner = view.findViewById(R.id.spinner);
        addBtn = view.findViewById(R.id.addbtn);
        cancelBtn = view.findViewById(R.id.cancelbtn);
        braceMap = view.findViewById(R.id.bracemapbtn);
        bubbleMap = view.findViewById(R.id.bubblemapbtn);
        circleMap = view.findViewById(R.id.circlemapbtn);
        treeMap = view.findViewById(R.id.treemapbtn);
        firestore = FirebaseFirestore.getInstance();


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        firestore.collection("task").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<String> dataList = new ArrayList<>();

                for (DocumentSnapshot document : task.getResult()) {
                    String taskText = document.getString("task");
                    dataList.add(taskText);
                }

                // Create an ArrayAdapter to populate the spinner with the data list
                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        context,
                        android.R.layout.simple_spinner_item,
                        dataList
                );
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // Set the adapter on the spinner
                spinner.setAdapter(adapter);
            }
        });

        braceMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapName = "Brace map";
                map = ContextCompat.getDrawable(context, R.drawable.brace_map);
                bubbleMap.setChecked(false);
                circleMap.setChecked(false);
                treeMap.setChecked(false);
            }
        });

        bubbleMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapName = "Bubble map";
                map = ContextCompat.getDrawable(context, R.drawable.bubble_map);
                braceMap.setChecked(false);
                circleMap.setChecked(false);
                treeMap.setChecked(false);
            }
        });

        circleMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapName = "Circle map";
                map = ContextCompat.getDrawable(context, R.drawable.circle_map);
                braceMap.setChecked(false);
                bubbleMap.setChecked(false);
                treeMap.setChecked(false);
            }
        });

        treeMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapName = "Tree map";
                map = ContextCompat.getDrawable(context, R.drawable.tree_map);
                braceMap.setChecked(false);
                bubbleMap.setChecked(false);
                circleMap.setChecked(false);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task = spinner.getSelectedItem().toString();
                Map<String, Object> mindMap = new HashMap<>();
                mindMap.put("task", task);
                mindMap.put("map", map);

                firestore.collection("mindmap").add(mindMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            Intent intent;
                            if (mapName.equals("Brace Map")) {
                                intent = new Intent(getContext(), BraceMapActivity.class);
                            } else if (mapName.equals("Bubble Map")) {
                                intent = new Intent(getContext(), BraceMapActivity.class);
                            } else if (mapName.equals("Circle Map")) {
                                intent = new Intent(getContext(), BraceMapActivity.class);
                            } else {
                                intent = new Intent(getContext(), BraceMapActivity.class);
                            }
                            startActivity(intent);
                            Toast.makeText(context, "Creating new " + mapName, Toast.LENGTH_SHORT).show();
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
        if (activity instanceof OnDialogCloseListener) {
            ((OnDialogCloseListener) activity).onDialogClose(dialog);
        }
    }
}

