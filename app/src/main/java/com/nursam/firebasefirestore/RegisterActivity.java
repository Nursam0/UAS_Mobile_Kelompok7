package com.nursam.firebasefirestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private EditText mMainText, mMainText1;
    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mMainText = findViewById(R.id.mainText);
        mMainText1 = findViewById(R.id.mainText2);
        Button mRegisBtn = findViewById(R.id.regisBtn);
        ProgressBar progressBar = findViewById(R.id.progress_bar);

        FirebaseApp.initializeApp(this);
        mFirestore = FirebaseFirestore.getInstance();


        mRegisBtn.setOnClickListener(v -> {
            String username = mMainText.getText().toString();
            String stambuk = mMainText1.getText().toString();

            Map<String, String> userMap = new HashMap<>();

            userMap.put("nama", username);
            userMap.put("stambuk", stambuk);

            progressBar.setVisibility(View.VISIBLE);
            if (!username.equals("") && !stambuk.equals("")) {
                mFirestore.collection("pengguna").add(userMap).addOnSuccessListener(documentReference -> {
                    Toast.makeText(RegisterActivity.this, "Pengguna Ditambahkan di Firestore", Toast.LENGTH_SHORT).show();
                    mMainText.setText("");
                    mMainText1.setText("");
                    progressBar.setVisibility(View.GONE);

                    // Mengarahkan ke MainActivity
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Optional: Mengakhiri aktivitas saat ini agar tidak dapat dikembalikan dengan tombol "Back"
                }).addOnFailureListener(e -> {
                    String error = e.getMessage();
                    Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                });
            } else {
                Toast.makeText(RegisterActivity.this, "Username dan Stambuk tidak boleh kosong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}