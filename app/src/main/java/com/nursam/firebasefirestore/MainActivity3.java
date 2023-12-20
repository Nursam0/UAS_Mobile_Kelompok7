package com.nursam.firebasefirestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();
        String name = intent.getStringExtra("data_name");
        String deskripsi = intent.getStringExtra("data_deskripsi");
        int foto = intent.getIntExtra("data_foto", 0);


        Button contact_btn = findViewById(R.id.contact_btn);
        TextView nameTextView = findViewById(R.id.textViewNamaFurniture);
        TextView deskripsiTextView = findViewById(R.id.textViewDeskripsiFurniture);
        ImageView fotoTextView = findViewById(R.id.imageViewFurniture);

        nameTextView.setText(name);
        deskripsiTextView.setText(deskripsi);
        fotoTextView.setImageResource(foto);


        contact_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "6285281742267";

                String url = "https://api.whatsapp.com/send?phone=" + phoneNumber;

                try {

                    PackageManager pm = getApplicationContext().getPackageManager();
                    pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);


                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                } catch (PackageManager.NameNotFoundException e) {

                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                }
            }
        });

    }
}