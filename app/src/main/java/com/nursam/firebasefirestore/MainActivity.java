package com.nursam.firebasefirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.nursam.firebasefirestore.databinding.ActivityMainBinding;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText mMainText, mMainText1;
    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainText = findViewById(R.id.mainText);
        mMainText1 = findViewById(R.id.mainText2);
        Button mSaveBtn = findViewById(R.id.saveBtn);
        Button mRegisBtn = findViewById(R.id.regisBtn);
        ProgressBar progressBar = findViewById(R.id.progress_bar);

        FirebaseApp.initializeApp(this);
        mFirestore = FirebaseFirestore.getInstance();


        mSaveBtn.setOnClickListener(v -> {
            String username = mMainText.getText().toString();
            String password = mMainText1.getText().toString();

            Map<String, String> userMap = new HashMap<>();

            userMap.put( "username", username);
            userMap.put("password", password);

            ProgressBar progressbar = findViewById(R.id.progress_bar);


            progressBar.setVisibility(View.VISIBLE);
            if (!username.equals("") && !password.equals("")) {
                // Mencari dokumen dengan username yang sesuai di Firestore
                mFirestore.collection("pengguna")
                        .whereEqualTo("nama", username)
                        .get()
                        .addOnSuccessListener(queryDocumentSnapshots -> {
                            if (!queryDocumentSnapshots.isEmpty()) {
                                // Dokumen dengan username yang sesuai ditemukan
                                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                                    // Memeriksa apakah password sesuai
                                    String storedPassword = document.getString("stambuk");
                                    if (storedPassword != null && storedPassword.equals(password)) {
                                        // Username dan password sesuai
                                        progressBar.setVisibility(View.GONE);

                                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        // Password tidak sesuai
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(MainActivity.this, "Password salah", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                // Tidak ada dokumen dengan username yang sesuai
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(MainActivity.this, "Username tidak ditemukan", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(e -> {
                            // Kesalahan saat mengambil data dari Firestore
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            } else {
                Toast.makeText(MainActivity.this, "Username dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }
        });

        mRegisBtn.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);

            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });
    }
}