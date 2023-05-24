package com.example.getapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.getapp.Adapter.MindMapAdapter;
import com.example.getapp.Adapter.ToDoAdapter;
import com.example.getapp.Model.MindMapModel;
import com.example.getapp.Model.ToDoModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MindMapPage extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageView imageMenu;

//    private RecyclerView recyclerView;
    private FloatingActionButton nFab;
    private FirebaseFirestore firestore;
    private MindMapAdapter adapter;
    private List<MindMapModel> mList;
    private ImageButton mindmap1;
    private ImageButton mindmap2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mindmap_page);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_View);
        imageMenu = findViewById(R.id.imageMenu);
//        recyclerView = findViewById(R.id.recyclerView);
        nFab = findViewById(R.id.floatingActionButton2);
        firestore = FirebaseFirestore.getInstance();
        mindmap1 = findViewById(R.id.mindmap1);
        mindmap2 = findViewById(R.id.mindmap2);

//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(MindMapPage.this));

        nFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewMindMap.newInstance().show(getSupportFragmentManager(), AddNewMindMap.TAG);
            }
        });
//        mList = new ArrayList<>();
//        adapter = new MindMapAdapter(MindMapPage.this, mList);

//        recyclerView.setAdapter(adapter);
//        showData();


        toggle = new ActionBarDrawerToggle(MindMapPage.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Drawer click event
        // Drawer item Click event ------
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.mTasks:
                        Toast.makeText(MindMapPage.this,"Showing tasks", Toast.LENGTH_SHORT).show();
                        Intent intentM = new Intent(MindMapPage.this, MainActivity.class);
                        startActivity(intentM);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.mindMap:
                        Toast.makeText(MindMapPage.this, "Showing Mind Maps", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.maps:
                        Toast.makeText(MindMapPage.this, "Opening GMap", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:10.316670, 123.916670"));
                        startActivity(intent2);
                        break;

                }

                return false;
            }
        });
        //------------------------------

        // ------------------------
        // App Bar Click Event
        imageMenu = findViewById(R.id.imageMenu);

        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code Here
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        mindmap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MindMapPage.this, BraceMapActivity.class);
                startActivity(intent);
            }
        });
    }


//    private void showData(){
//        firestore.collection("mindmap").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                for(DocumentChange documentChange : value.getDocumentChanges()){
//                    if(documentChange.getType() == DocumentChange.Type.ADDED){
//                        String id = documentChange.getDocument().getId();
//                        MindMapModel mindMapModel = documentChange.getDocument().toObject(MindMapModel.class).withId(id);
//
//                        mList.add(mindMapModel);
//                        adapter.notifyDataSetChanged();
//                    }
//                }
//                Collections.reverse(mList);
//            }
//        });
//    }
}