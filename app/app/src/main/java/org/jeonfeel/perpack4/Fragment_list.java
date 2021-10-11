package org.jeonfeel.perpack4;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragment_list extends Fragment {
    private static final String TAG = "Fragment_list";

    SQLiteDatabase database;
    RecyclerView recyclerView;
    NoteAdapter adapter;

    Context context;
    OnTabItemSelectedListener listener;
    Intent intent;

    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;

        if (context instanceof OnTabItemSelectedListener) {
            listener = (OnTabItemSelectedListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (context != null) {
            context = null;
            listener = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);

        initUI(rootView);

        executeQuery();

        return rootView;
    }

    private void initUI(ViewGroup rootView) {

        recyclerView = rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new NoteAdapter();

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnNoteItemClickListener() {
            @Override
            public void onItemClick(NoteAdapter.ViewHolder holder, View view, int position) {
                Note item = adapter.getItem(position);
                intent = new Intent(getActivity(),List_View.class);
                intent.putExtra("id",item._id);
                startActivity(intent);
            }
        });

    }

    public void executeQuery() {

        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        database = dbHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("select _id, TITLE, YEAR, MONTH, DAY, HOUR, MIN, LIST, CHECKBOX, PURPOSE from Note",null);
        int recordCount = cursor.getCount();

        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext();
            int id = cursor.getInt(0);
            String TITLE = cursor.getString(1);
            String YEAR = cursor.getString(2);
            String MONTH = cursor.getString(3);
            String DAY = cursor.getString(4);
            String PURPOSE = cursor.getString(9);

            if(MONTH.length() == 1){
                MONTH = "0" + MONTH;
            }
            if(DAY.length() == 1){
                DAY = "0" + DAY;
            }
            adapter.addItem(new Note(id, PURPOSE, TITLE, "  출발일 : " + YEAR + ". " + MONTH + ". " + DAY+"  "));
        }
        cursor.close();
    }
}

