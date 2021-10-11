package org.jeonfeel.perpack7;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import org.jeonfeel.perpack7.Fragment_MyList;
import org.jeonfeel.perpack7.Fragment_list;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MyList_View extends AppCompatActivity {

    Fragment_MyList fragment_myList;
    ArrayList<String> items;
    ListView listView;
    SQLiteDatabase database;
    String title;
    Button btn_Addbutton2;
    JSONArray mList;
    Intent intent;
    int Noteid;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylist_view);

        intent = getIntent();
        Noteid = intent.getIntExtra("id", 0);

        items = new ArrayList<String>();

        final ButtonListAdapter buttonListAdapter = new ButtonListAdapter(this, items);
        btn_Addbutton2 = findViewById(R.id.mylist_btn_AddButton2);
        final EditText et_item2 = findViewById(R.id.mylist_Edit_item2);
        listView = findViewById(R.id.mylist_listview2);
        listView.setAdapter(buttonListAdapter);

        executeQuery();



        setTitle(title);

        btn_Addbutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = et_item2.getText().toString();
                if (text.length() != 0) {
                    listView.setAdapter(null);
                    listView.setAdapter(buttonListAdapter);
                    buttonListAdapter.data.add(text);
                    et_item2.setText("");
                    listView.setSelection(items.size());
                }
            }
        });
    }

    public void onBackPressed() {
        super.onBackPressed();
        dataupdate("mylist_note");
        database.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_title_set, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.btn_mylist_del:

                new AlertDialog.Builder(this)
                        .setTitle("PerPack")
                        .setMessage("삭제 하시겠습니까?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseHelper dbHelper = new DatabaseHelper(getApplication());
                                database = dbHelper.getWritableDatabase();
                                database.execSQL("DELETE FROM mylist_note where mylist_id = '" + Noteid + "';");
                                Toast.makeText(getApplication(), "삭제 되었습니다.", Toast.LENGTH_SHORT).show();

                                MainActivity MA = (MainActivity) MainActivity.activity;
                                MA.finish();
                                Intent intent2 = new Intent(getApplication(),MainActivity.class);
                                startActivity(intent2);

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

            case R.id.btn_mylist_title:

                final Dialog dlg = new Dialog(this);

                // 액티비티의 타이틀바를 숨긴다.
                dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

                // 커스텀 다이얼로그의 레이아웃을 설정한다.
                dlg.setContentView(R.layout.custom_dialog);

                // 커스텀 다이얼로그를 노출한다.
                dlg.show();

                // 커스텀 다이얼로그의 각 위젯들을 정의한다.
                final EditText message = (EditText) dlg.findViewById(R.id.mesgase);
                final Button okButton = (Button) dlg.findViewById(R.id.okButton);
                message.setText(title);
                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String a = message.getText().toString();
                    if(!a.equals(title)) {
                        String sql = " update " + "mylist_note" + " set " +
                                " mylistTITLE = '" + a + "'" +
                                " where " + " mylist_id = " + Noteid;
                        database.execSQL(sql);

                        Toast.makeText(getApplication(), "변경되었습니다.", Toast.LENGTH_SHORT).show();

                        // 커스텀 다이얼로그를 종료한다.
                        dlg.dismiss();

                        MainActivity MA = (MainActivity) MainActivity.activity;
                        MA.finish();
                        Intent intent2 = new Intent(getApplication(), MainActivity.class);
                        startActivity(intent2);

                        finish();
                    }else{
                        dlg.dismiss();
                    }
                    }
                });

        break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void executeQuery() {

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("select mylist_id,mylistTITLE,mylistCHECKL from mylist_note where mylist_id = " + Noteid, null);
        int recordCount = cursor.getCount();

        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext();

            int id = cursor.getInt(0);
            String TITLE = cursor.getString(1);
            String LIST = cursor.getString(2);

            title = TITLE;

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
            cursor.close();
        }
    }

    public void dataupdate(String name) {

        mList = new JSONArray();

        for (int i = 0; i < items.size(); i++) {
            mList.put(items.get(i));
        }

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();
        String sql = " update " + name + " set " +
                " mylistCHECKL = '" + mList + "'" +
                " where " + " mylist_id = " + Noteid;
        database.execSQL(sql);

    }

}
