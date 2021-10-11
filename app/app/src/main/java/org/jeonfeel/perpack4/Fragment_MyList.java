package org.jeonfeel.perpack4;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

public class Fragment_MyList extends Fragment {
    private static final String TAG = "Fragment_Mylist";

    RecyclerView recyclerView2;
    MyListNoteAdapter adapter;

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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_mylist, container, false);

        initUI(rootView);



        return rootView;
    }
    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.menu_my_list_note,menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        intent = new Intent(getActivity(), Mylist_add.class);
        startActivity(intent);

        return super.onOptionsItemSelected(item);
        }

    private void initUI(ViewGroup rootView) {

        recyclerView2 = rootView.findViewById(R.id.recyclerView2);

        setHasOptionsMenu(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView2.setLayoutManager(layoutManager);

        adapter = new MyListNoteAdapter();

        recyclerView2.setAdapter(adapter);

//        adapter.setOnItemClickListener(new OnNoteItemClickListener() {
//            @Override
//            public void onItemClick(NoteAdapter.ViewHolder holder, View view, int position) {
//                MyListNote item = adapter.getItem(position);
//                intent = new Intent(getActivity(),List_View.class);
//                intent.putExtra("id",item._id);
//                startActivity(intent);
//            }
//        });

    }
}
