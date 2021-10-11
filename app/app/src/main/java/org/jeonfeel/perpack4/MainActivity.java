package org.jeonfeel.perpack4;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements OnTabItemSelectedListener {
    private static final String TAG = "MainActivity";

    public static Activity activity;

    Fragment_MyList fragment_myList;
    Fragment_list fragment_list;
    Fragment_write fragment_write;
    BottomNavigationView bottomNavigationView;
    DatabaseHelper dbHelper;

    SQLiteDatabase database;

    listAddDel listAddDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = MainActivity.this;

        dbHelper = new DatabaseHelper(this);
        dbHelper.getWritableDatabase();


        int intercheck = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        int permiCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permiCheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(intercheck == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.INTERNET}, 0);
        }
        if(permiCheck == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
        if(permiCheck2 == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }

        createDatabase("NewDataBase");
        createDatabase("SecondDB");

        fragment_list = new Fragment_list();
        fragment_write = new Fragment_write();
        fragment_myList = new Fragment_MyList();
        listAddDel = new listAddDel();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment_list).commit();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.btn_list:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_list).commit();
                                return true;
                            case R.id.btn_add:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_write).commit();
                                return true;
                            case R.id.btn_chec:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment_myList).commit();
                                return true;
                        }
                        return false;
                    }
                });
    }
    public void onTabSelected(int position){
        if (position == 0){
            bottomNavigationView.setSelectedItemId(R.id.btn_list);
        }else if(position == 1){
            bottomNavigationView.setSelectedItemId(R.id.btn_add);
        }else if(position == 2){
            bottomNavigationView.setSelectedItemId(R.id.btn_chec);
        }

    }

    public void showDatePicker(View view){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }

    public void processDatePickerResult(int year,int month,int day){
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);

        fragment_write.newSetDate(year_string,month_string,day_string);
    }

    private void createDatabase(String name){
        dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();
    }
}