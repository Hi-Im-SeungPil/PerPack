package org.jeonfeel.perpack7;

import android.content.Intent;
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

import java.util.ArrayList;

public class MyListaAddDel extends AppCompatActivity {
    //<string name="admob_app_id">ca-app-pub-8481465476603755~2584104654</string>
    // <string name="banner_ad_unit_id">ca-app-pub-8481465476603755/5744690273</string>
    // <string name="banner_ad_unit_id_for_test">ca-app-pub-3940256099942544/1033173712</string>
    SQLiteDatabase database;
    String str;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylist_add_del);

        m = new ArrayList<Boolean>();
        m.add(false);

        b = new JSONArray();
        for (int i = 0; i < m.size(); i++){
            b.put(m.get(i));
        }

        Intent intent = getIntent();
        checkList =  (ArrayList<String>) intent.getSerializableExtra("checkList");
        str = intent.getStringExtra("str");
        items = new ArrayList<String>();
        listView = (ListView) findViewById(R.id.mylist_listview);
        Edit_item = (EditText) findViewById(R.id.mylist_Edit_item);
        btn_AddButton = (Button) findViewById(R.id.mylist_btn_AddButton);

        btn_storage = findViewById(R.id.btn_storage);
        buttonListAdapter = new ButtonListAdapter(this,items);
        listView.setAdapter(buttonListAdapter);

        if(checkList != null && checkList.size() > 0){
            for(int i = 0; i < checkList.size(); i++) {
                String a = checkList.get(i);
                items.add(a);
            }
        }
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
        if(checkList != null)
            checkList.clear();
        if(items != null)
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
        for (int i = 0; i < items.size(); i++){
            mList.put(items.get(i));
        }

        insertRecord("mylist_note");

        Toast.makeText(this, "저장 되었습니다.", Toast.LENGTH_SHORT).show();

        MainActivity MA = (MainActivity) MainActivity.activity;
        MA.finish();
        Intent intent2 = new Intent(getApplication(),MainActivity.class);
        startActivity(intent2);

        database.close();

        finish();

        return super.onOptionsItemSelected(item);
    }
    private void insertRecord(String name){
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        database.execSQL("insert into " + name
                + "(mylistTITLE, mylistCHECKL) values(" +
                "'"+ str + "', " +
                "'"+ mList + "')");
    }
}

