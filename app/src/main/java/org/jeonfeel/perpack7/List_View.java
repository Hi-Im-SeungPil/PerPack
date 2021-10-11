package org.jeonfeel.perpack7;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class List_View extends AppCompatActivity implements ButtonListAdapter2.ListBtnClickListener {

    Fragment_list fragment_list;
    ArrayList<String> items;
    ListView listView;
    SQLiteDatabase database;
    String title, year, month, day;
    Button btn_Addbutton2;
    Button btn_mylist;
    JSONArray mList;
    Intent intent;
    int Noteid;
    CheckBox checkBox;
    public static ArrayList<Boolean> mCheck = null;
    JSONArray jsonCheck;
    JSONArray mylistJson;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_view);

        fragment_list = new Fragment_list();

        intent = getIntent();
        Noteid = intent.getIntExtra("id", 0);

        items = new ArrayList<String>();

        final ButtonListAdapter2 buttonListAdapter2 = new ButtonListAdapter2(this, items, Noteid, this);
        btn_Addbutton2 = findViewById(R.id.btn_AddButton2);
        final EditText et_item2 = findViewById(R.id.Edit_item2);
        listView = findViewById(R.id.listview2);
        listView.setAdapter(buttonListAdapter2);
        listView.setOnItemClickListener(mItemClickListener);
        final Button check_weather = findViewById(R.id.check_weather);
        btn_mylist = findViewById(R.id.btn_mylist);

        mCheck = new ArrayList<Boolean>();
        executeQuery();

        setTitle(title);

        check_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_weather.getText().equals("날씨 확인하러 가기")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://n.weather.naver.com/"));
                    startActivity(intent);
                }
            }
        });

            btn_Addbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = et_item2.getText().toString();
                if (text.length() != 0) {
                    mCheck.add(false);
                    listView.setAdapter(null);
                    listView.setAdapter(buttonListAdapter2);
                    buttonListAdapter2.addItem(text);
                    et_item2.setText("");
                    listView.setSelection(items.size());
                }
            }
        });
        btn_mylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertMyList("mylist_note");
                Toast.makeText(getApplication(), "내 리스트로 저장 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        dataupdate("Note");
        database.close();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.btn_del:

                new AlertDialog.Builder(this)
                        .setTitle("PerPack")
                        .setMessage("삭제 하시겠습니까?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseHelper dbHelper = new DatabaseHelper(getApplication());
                                database = dbHelper.getWritableDatabase();
                                database.execSQL("DELETE FROM Note where _id = '" + Noteid + "';");
                                Toast.makeText(getApplication(), "삭제 되었습니다.", Toast.LENGTH_SHORT).show();

                                MainActivity MA = (MainActivity) MainActivity.activity;
                                MA.finish();
                                Intent intent2 = new Intent(getApplication(),MainActivity.class);
                                startActivity(intent2);
                                database.close();

                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplication(), "삭제 취소", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                        break;

            case R.id.btn_edit2:

                Intent intent = new Intent(this, ReEdit.class);
                intent.putExtra("Noteid", Noteid);
                startActivity(intent);

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void executeQuery() {

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("select _id, TITLE, YEAR, MONTH, DAY, HOUR, MIN, LIST, CHECKBOX from Note where _id = " + Noteid, null);
        int recordCount = cursor.getCount();

        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext();

            int id = cursor.getInt(0);
            String TITLE = cursor.getString(1);
            String YEAR = cursor.getString(2);
            String MONTH = cursor.getString(3);
            String DAY = cursor.getString(4);
            String LIST = cursor.getString(7);
            String CHECKBOX = cursor.getString(8);

            title = TITLE;
            year = YEAR;
            month = MONTH;
            day = DAY;


            if (LIST != null) {
                try {
                    JSONArray a = new JSONArray(LIST);
                    for (int j = 0; j < a.length(); j++) {
                        String url = a.optString(j);
                        items.add(url);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if(CHECKBOX != null){
                try {
                    JSONArray b = new JSONArray(CHECKBOX);
                    for(int k = 0; k < b.length(); k++){
                        boolean url = b.optBoolean(k);
                        mCheck.add(url);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else{
                for(int m = 0; m < items.size(); m ++) {
                    mCheck.add(false);
                }
            }
            cursor.close();
        }
    }
    public void insertMyList(String name){
        mylistJson = new JSONArray();
        for(int i = 0; i < items.size(); i++){
            mylistJson.put(items.get(i));
        }
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        database.execSQL("insert into " + name
                + "(mylistTITLE, mylistCHECKL) values(" +
                "'"+ title + "', " +
                "'"+ mylistJson + "')");
    }

    public void dataupdate(String name) {

        mList = new JSONArray();
        jsonCheck = new JSONArray();
        for (int i = 0; i < items.size(); i++) {
            mList.put(items.get(i));
            jsonCheck.put(mCheck.get(i));
        }

            DatabaseHelper dbHelper = new DatabaseHelper(this);
            database = dbHelper.getWritableDatabase();
            String sql = " update " + name + " set " +
                    " LIST = '" + mList + "'" +
                    ", CHECKBOX = '" + jsonCheck + "'" +
                    " where " + " _id = " + Noteid;
            database.execSQL(sql);

    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onListBtnClick(int position) {
        ButtonListAdapter2 buttonListAdapter2 = new ButtonListAdapter2(this,items,Noteid,this);
        listView.setAdapter(buttonListAdapter2);
        items.remove(position);
        mCheck.remove(position);
        listView.setSelection(position);
        buttonListAdapter2.notifyDataSetChanged();

    }
    AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ButtonListAdapter2 buttonListAdapter2 = new ButtonListAdapter2(getApplication(),items,Noteid,null);
            checkBox = view.findViewById(R.id.checkedbox);
            TextView textView = view.findViewById(R.id.custom_textView1);

            if (checkBox.isChecked() == false) {
                checkBox.setChecked(true);
                mCheck.remove(position);
                mCheck.add(position,true);
                textView.setTextColor(Color.parseColor("#D5D5D5"));
                buttonListAdapter2.notifyDataSetChanged();
            }
            else {
                checkBox.setChecked(false);
                mCheck.remove(position);
                mCheck.add(position,false);
                textView.setTextColor(Color.BLACK);
                buttonListAdapter2.notifyDataSetChanged();
            }

            }

    };
}