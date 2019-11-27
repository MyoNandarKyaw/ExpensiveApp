package com.techhousestudio.expensiveapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ReportFragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.techhousestudio.expensiveapp.Fragment.ExpenseFragment;
import com.techhousestudio.expensiveapp.Fragment.HistoryFragment;
import com.techhousestudio.expensiveapp.Fragment.HomeFragment;
import com.techhousestudio.expensiveapp.Fragment.IncomeFragment;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        navigationView=findViewById(R.id.bottomnavigation);
        setSupportActionBar(toolbar);
        toolbar.setTitle(null);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment currentFragment=null;
                switch (item.getItemId()){
                    case R.id.menuhome:
                        currentFragment=new HomeFragment();
                        break;
                    case R.id.income:
                        currentFragment=new IncomeFragment();
                        break;
                    case R.id.expense:
                        currentFragment=new ExpenseFragment();
                        break;
                    case R.id.history:
                        currentFragment=new HistoryFragment();


                }
                return loadFragment(currentFragment);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting_menu,menu);
        return true;
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment!=null){
            Toast.makeText(this,"Fragment",Toast.LENGTH_LONG).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
