package org.jeonfeel.perpack7;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;

public class listAddDel extends AppCompatActivity implements Serializable {
    //<string name="admob_app_id">ca-app-pub-8481465476603755~2584104654</string>
    // <string name="banner_ad_unit_id">ca-app-pub-8481465476603755/5744690273</string>
    // <string name="banner_ad_unit_id_for_test">ca-app-pub-3940256099942544/1033173712</string>
    SQLiteDatabase database;
    String purpose;
    String str;
    String mYear;
    String mMonth;
    String mDay;
    ArrayList<String> items;
    ListView listView;
    Button btn_AddButton;
    EditText Edit_item;
    Button btn_storage;
    JSONArray mList;
    ButtonListAdapter buttonListAdapter;
    ArrayList<Boolean> m;
    JSONArray b;
    ArrayList<String> checkList;
    ArrayList<Integer> myListId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_add_del);

        m = new ArrayList<Boolean>();
        m.add(false);

        b = new JSONArray();
        for (int i = 0; i < m.size(); i++) {
            b.put(m.get(i));
        }

        Intent intent = getIntent();
        checkList = (ArrayList<String>) intent.getSerializableExtra("checkList");
        str = intent.getStringExtra("str");
        mYear = intent.getStringExtra("year");
        mMonth = intent.getStringExtra("month");
        mDay = intent.getStringExtra("day");
        purpose = intent.getStringExtra("purpose");
        myListId = (ArrayList<Integer>) intent.getSerializableExtra("nextid");

        items = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.listview);
        Edit_item = (EditText) findViewById(R.id.Edit_item);
        btn_AddButton = (Button) findViewById(R.id.btn_AddButton);

        btn_storage = findViewById(R.id.btn_storage);
        buttonListAdapter = new ButtonListAdapter(this, items);
        listView.setAdapter(buttonListAdapter);

        if (checkList != null && checkList.size() > 0) {
            for (int i = 0; i < checkList.size(); i++) {
                String a = checkList.get(i);
                items.add(a);
            }
        }
        MyListAdd();
        btn_AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = Edit_item.getText().toString();
                if (text.length() != 0) {
                    items.add(text);
                    Edit_item.setText("");
                    buttonListAdapter.notifyDataSetChanged();
                    listView.setSelection(items.size());
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (checkList != null)
            checkList.clear();
        if (items != null)
            items.clear();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        mList = new JSONArray();
        for (int i = 0; i < items.size(); i++) {
            mList.put(items.get(i));
        }

        insertRecord("Note");

        Toast.makeText(this, "저장 되었습니다.", Toast.LENGTH_SHORT).show();

        database.close();

        finish();

        return super.onOptionsItemSelected(item);
    }

    private void insertRecord(String name) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        database.execSQL("insert into " + name
                + "(TITLE, YEAR, MONTH, DAY, LIST, PURPOSE) values(" +
                "'" + str + "', " +
                "'" + mYear + "', " +
                "'" + mMonth + "', " +
                "'" + mDay + "', " +
                "'" + mList + "', " +
                "'" + purpose + "')");
    }

    public void MyListAdd() {

        if(myListId != null) {
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            database = dbHelper.getWritableDatabase();

            for (int i = 0; i < myListId.size(); i++) {
                Cursor cursor = database.rawQuery("select mylist_id,mylistTITLE,mylistCHECKL from mylist_note where mylist_id = " + myListId.get(i), null);
                int recordCount = cursor.getCount();

                for (int j = 0; j < recordCount; j++) {
                    cursor.moveToNext();

                    int id = cursor.getInt(0);
                    String TITLE = cursor.getString(1);
                    String CHECKL = cursor.getString(2);

                    if (CHECKL != null) {
                        try {
                            JSONArray a = new JSONArray(CHECKL);
                            for (int m = 0; m < a.length(); m++) {
                                String url = a.optString(m);
                                items.add(url);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    cursor.close();
                }
            }
        }
    }
}