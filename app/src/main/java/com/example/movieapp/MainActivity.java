package com.example.movieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.movieapp.fragments.FragmentFavorite;
import com.example.movieapp.fragments.FragmentMovie;
import com.example.movieapp.fragments.FragmentTv;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Map<Integer, Fragment> fragmentMap;
    private BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentMap = new HashMap<>();
        bottomNav = findViewById(R.id.bottom_navigation);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new FragmentMovie()).commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        fragmentMap.put(R.id.nav_movie, FragmentMovie.newInstance());
        fragmentMap.put(R.id.nav_tvShow, FragmentTv.newInstance());
        fragmentMap.put(R.id.nav_favorite, FragmentFavorite.newInstance());

        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.nav_movie);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = fragmentMap.get(item.getItemId());
            assert selectedFragment != null;

            switch (item.getItemId()){
                case R.id.nav_favorite:
                    getSupportActionBar().setTitle("Bookmark");
                    break;
                case R.id.nav_tvShow:
                    getSupportActionBar().setTitle("TV Show");
                    break;

                case R.id.nav_movie:
                    getSupportActionBar().setTitle("Movie");
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();

            return true;
        }
    };
}