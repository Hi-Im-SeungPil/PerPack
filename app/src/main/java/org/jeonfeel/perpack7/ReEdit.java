package org.jeonfeel.perpack7;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class ReEdit extends AppCompatActivity {

    listAddDel listAddDell;
    SQLiteDatabase database;
    int Noteid;
    String title,year,month,day;
    String mtitle,myear,mmonth,mday;
    TextView dateText2;
    EditText edit_title2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reedit);

        listAddDell = new listAddDel();
        dateText2 = findViewById(R.id.dateText2);
        edit_title2 = findViewById(R.id.edit_title2);

        Intent intent = getIntent();
        Noteid = intent.getIntExtra("Noteid",0);

        executeQuery();
            }



    public void executeQuery() {

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("select _id, TITLE, YEAR, MONTH, DAY, HOUR, MIN from Note where _id = " + Noteid,null);
        int recordCount = cursor.getCount();

        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext();

            int id = cursor.getInt(0);
            String TITLE = cursor.getString(1);
            String YEAR = cursor.getString(2);
            String MONTH = cursor.getString(3);
            String DAY = cursor.getString(4);

            title = TITLE;
            year = YEAR;
            month = MONTH;
            day = DAY;

            mtitle = TITLE;
            myear = YEAR;
            mmonth = MONTH;
            mday = DAY;


            edit_title2.setText(title);
            dateText2.setText("  "+year+"년 "+month+"월 "+day+"일");
            }
            cursor.close();
        }
    public void showDatePicker2(View view){
        DialogFragment newFragment = new DatePickerFragment2();
        newFragment.show(getSupportFragmentManager(),"datePicker2");
    }
    public void processDatePickerResult2(int year,int month,int day){
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);

        newSetDate2(year_string,month_string,day_string);
    }
    public void newSetDate2(String year,String month,String day){
         myear = year;
         mmonth = month;
         mday = day;
        dateText2.setText("  "+year+"년 "+month+"월 "+day+"일");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reedit_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        updateData("Note");

        Toast.makeText(this, "수정 되었습니다.", Toast.LENGTH_SHORT).show();

        List_View list_view = new List_View();
        MainActivity MA = (MainActivity) MainActivity.activity;
        MA.finish();
        list_view.finish();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("id",Noteid);
        startActivity(intent);

        database.close();

        finish();
        return super.onOptionsItemSelected(item);
    }
    public void updateData(String name){
        mtitle = edit_title2.getText().toString();
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();
        String sql = " update " + name + " set " +
                " TITLE = '" + mtitle + "'" +
                " ,YEAR = '" + myear + "'" +
                " ,MONTH = '" + mmonth + "'" +
                " ,DAY = '" + mday + "'" +
                " where " + " _id = " + Noteid;
        database.execSQL(sql);
    }
}

