package com.example.getapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;

public class BraceMapOutline extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    ImageView imageMenu;
    private FloatingActionButton nFab;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mindmap_activity_bracemap_outline);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_View);
        imageMenu = findViewById(R.id.imageMenu2);
        firestore = FirebaseFirestore.getInstance();

        toggle = new ActionBarDrawerToggle(BraceMapOutline.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.btnHome:
                        Intent intentH = new Intent(BraceMapOutline.this, MindMapPage.class);
                        startActivity(intentH);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.btnMindMap:
                        Toast.makeText(BraceMapOutline.this, "Showing Mind Map", Toast.LENGTH_SHORT).show();
                        Intent intentM = new Intent(BraceMapOutline.this, BraceMapActivity.class);
                        startActivity(intentM);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.btnOutline:
                        drawerLayout.closeDrawers();
                        break;

                }

                return false;
            }
        });

        // App Bar Click Event
        imageMenu = findViewById(R.id.imageMenu2);

        imageMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code Here
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
}
