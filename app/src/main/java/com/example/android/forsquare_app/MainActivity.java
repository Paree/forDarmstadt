package com.example.android.forsquare_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    public Fragment selectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
     //   requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        final ActionBar actionBar = getSupportActionBar();
        selectedFragment = null;
        actionBar.hide();
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                        switch (item.getItemId()) {
                            case R.id.navigation_search:

                                if(!(selectedFragment instanceof searchFrag)){
                                    selectedFragment = searchFrag.newInstance();
                                    actionBar.hide();
                                }
                               ;
                                break;
                            case R.id.navigation_history:

                                if(!(selectedFragment instanceof historyFrag)) {
                                    selectedFragment = historyFrag.newInstance();
                                    actionBar.show();
                                }
                                break;
                            case R.id.navigation_lists:
                                if(!(selectedFragment instanceof listsFrag)) {
                                    selectedFragment = listsFrag.newInstance();
                                    actionBar.show();
                                }

                                break;
                            case R.id.navigation_me:
                                if(!(selectedFragment instanceof listsFrag)) {
                                    selectedFragment = meFrag.newInstance();
                                    actionBar.show();
                                }
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.content, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content, searchFrag.newInstance());
        transaction.commit();
    }

}
