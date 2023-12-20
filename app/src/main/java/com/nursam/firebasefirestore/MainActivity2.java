package com.nursam.firebasefirestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nursam.firebasefirestore.databinding.ActivityMain2Binding;
import com.nursam.firebasefirestore.databinding.ActivityMainBinding;

public class MainActivity2 extends AppCompatActivity {
    private ActivityMain2Binding binding;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            int itemId = item.getItemId();
            if (itemId == R.id.home){
                fragment = new HomeFragment();
            } else if (itemId == R.id.order) {
                fragment = new OrderFragment();
            } else if (itemId == R.id.profile) {
                fragment = new ProfileFragment();
            }
            if (fragment != null){
                replaceFragment(fragment);
                return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        if (savedInstanceState == null){
            binding.bottomNavigationView.setSelectedItemId(R.id.home);
        }
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout2, fragment, fragment.getClass().getSimpleName()).commit();
    }
}