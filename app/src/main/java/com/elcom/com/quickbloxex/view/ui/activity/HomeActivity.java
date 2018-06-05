package com.elcom.com.quickbloxex.view.ui.activity;


import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.elcom.com.quickbloxex.R;
import com.elcom.com.quickbloxex.view.ui.fragment.NewsFragment;

public class HomeActivity extends AppCompatActivity {


    private BottomNavigationView navigation;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupView();
    }

    public void setupView(){
        fragmentManager = getSupportFragmentManager();
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            switch (id){
                case R.id.action_item1:
                    fragment = new NewsFragment();
                    break;
                case R.id.action_item2:
                    fragment = new NewsFragment();
                    break;
                case R.id.action_item3:
                    fragment = new NewsFragment();
                    break;
            }
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame_layout, fragment).commit();
            return true;
        });

    }
}
