package org.jeonfeel.perpack7;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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
    private long backKeyPressedTime = 0;
    private Toast toast;

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
    @Override
    public void onBackPressed() {
        // 기존 뒤로가기 버튼의 기능을 막기위해 주석처리 또는 삭제
        // super.onBackPressed();

        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지났으면 Toast Show
        // 2000 milliseconds = 2 seconds
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "뒤로 버튼을 한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지나지 않았으면 종료
        // 현재 표시된 Toast 취소
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
            toast.cancel();
        }
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