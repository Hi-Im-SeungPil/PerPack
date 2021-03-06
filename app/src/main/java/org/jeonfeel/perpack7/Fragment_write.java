package org.jeonfeel.perpack7;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class Fragment_write extends Fragment {

    SQLiteDatabase database;
    RadioButton radioButton;
    Context context;
    OnTabItemSelectedListener listener;
    private String str;
    Intent intent;
    Button nextStep;
    EditText edit_title;
    TextView dateText;
    String myear,mmonth,mday;
    ToggleButton toggleButton1;
    ToggleButton toggleButton2;
    ToggleButton toggleButton3;
    ToggleButton toggleButton4;
    ToggleButton toggleButton5;
    ToggleButton toggleButton6;
    ToggleButton toggleButton7;
    ToggleButton toggleButton8;
    ToggleButton toggleButton9;
    ToggleButton toggleButton10;
    ToggleButton toggleButton11;
    ToggleButton toggleButton12;
    Button textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10,textView11,textView12;
    RadioGroup radioGroup;
    View btn_Group;
    TextView z;
    ArrayList<String> checkList;
    String purpose;
    String[] name1;
    boolean[] check1;
    ArrayList<String> name2 = new ArrayList<String>();
    ArrayList<Boolean> check2 = new ArrayList<Boolean>();
    ArrayList<Integer> ListId = new ArrayList<Integer>();
    ArrayList<Integer> nextId = new ArrayList<Integer>();



    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        this.context = context;

        if(context instanceof OnTabItemSelectedListener) {
            listener = (OnTabItemSelectedListener) context;
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();

        if(context != null){
            context = null;
            listener = null;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_write,container,false);

        purpose = "0";

        initUI(rootView);

        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        database = dbHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("select mylist_id, mylistTITLE from mylist_note", null);
        int recordCount = cursor.getCount();

        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext();

            int id = cursor.getInt(0);
            String TITLE = cursor.getString(1);

            name2.add(TITLE);
            ListId.add(id);
        }
        cursor.close();
        int size = name2.size();

        name1 = new String[size];
        for(int i = 0; i < size; i++){
            name1[i] = name2.get(i);
        }
        for(int i = 0; i < size; i++){
            name2.remove(0);
        }
        check1 = new boolean[name1.length];
        if(check1.length == 0){
            for(int i = 0; i < size; i++){
                check1[i] = false;
            }
        }



        toggleButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton1.isChecked()){
                    toggleButton1.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.toiletries));
                            checkList.add("??????");
                            checkList.add("??????");
                            checkList.add("??? ?????????");
                            checkList.add("??????");
                            checkList.add("??????");
                            checkList.add("??????");
                            checkList.add("????????????");
                            checkList.add("?????????");
                            checkList.add("??????");
                            checkList.add("??????");
                            checkList.add("???????????????");
                }else{
                    toggleButton1.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.toiletries_unclicked));
                            checkList.remove("??????");
                            checkList.remove("??????");
                            checkList.remove("??? ?????????");
                            checkList.remove("??????");
                            checkList.remove("??????");
                            checkList.remove("??????");
                            checkList.remove("????????????");
                            checkList.remove("?????????");
                            checkList.remove("??????");
                            checkList.remove("??????");
                            checkList.remove("???????????????");
                }
            }
        });
        toggleButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton2.isChecked()){
                    toggleButton2.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.clothes));
                    checkList.add("?????? ??????");
                    checkList.add("?????? ??????");
                    checkList.add("??????");
                    checkList.add("????????? ?????????");
                    checkList.add("??????");
                    checkList.add("??????");
                    checkList.add("??????");
                    checkList.add("??????");
                    checkList.add("??????");

                }else{
                    toggleButton2.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.cloth_unclicked));
                    checkList.remove("?????? ??????");
                    checkList.remove("?????? ??????");
                    checkList.remove("??????");
                    checkList.remove("????????? ?????????");
                    checkList.remove("??????");
                    checkList.remove("??????");
                    checkList.remove("??????");
                    checkList.remove("??????");
                    checkList.remove("??????");
                }
            }
        });
        toggleButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton3.isChecked()){
                    toggleButton3.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.baby));
                    checkList.add("?????????");
                    checkList.add("??????");
                    checkList.add("?????? ?????????");
                    checkList.add("?????? ?????????");
                    checkList.add("?????? ????????????");
                    checkList.add("?????? ?????????");
                    checkList.add("?????? ???");
                    checkList.add("?????? ??????");
                    checkList.add("?????? ??????");
                    checkList.add("?????? ??????");
                    checkList.add("?????? ???");
                    checkList.add("????????? ?????????");
                    checkList.add("?????? ?????????");
                    checkList.add("?????????");
                }else{
                    toggleButton3.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.baby_unclicked));
                    checkList.remove("?????????");
                    checkList.remove("??????");
                    checkList.remove("?????? ?????????");
                    checkList.remove("?????? ?????????");
                    checkList.remove("?????? ????????????");
                    checkList.remove("?????? ?????????");
                    checkList.remove("?????? ???");
                    checkList.remove("?????? ??????");
                    checkList.remove("?????? ??????");
                    checkList.remove("?????? ??????");
                    checkList.remove("?????? ???");
                    checkList.remove("????????? ?????????");
                    checkList.remove("?????? ?????????");
                    checkList.remove("?????????");
                }
            }
        });
        toggleButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton4.isChecked()){
                    toggleButton4.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.flight));
                    checkList.add("??????");
                    checkList.add("??????(????????? ?????????)");
                    checkList.add("???????????? ???????????? ?????? ??????");
                    checkList.add("???????????????");
                    checkList.add("?????????????????? ?????? ??????");
                    checkList.add("???????????????");
                    checkList.add("???????????? ?????????");
                    checkList.add("????????? ?????? ??????");
                    checkList.add("????????? ?????? ?????????");
                }else{
                    toggleButton4.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.flight_unclicked));
                    checkList.remove("??????");
                    checkList.remove("??????(????????? ?????????)");
                    checkList.remove("???????????? ???????????? ?????? ??????");
                    checkList.remove("???????????????");
                    checkList.remove("?????????????????? ?????? ??????");
                    checkList.remove("???????????????");
                    checkList.remove("???????????? ?????????");
                    checkList.remove("????????? ?????? ??????");
                    checkList.remove("????????? ?????? ?????????");
                }
            }
        });
        toggleButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton5.isChecked()){
                    toggleButton5.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.parasol));
                    checkList.add("????????????");
                    checkList.add("?????????");
                    checkList.add("??????");
                    checkList.add("??????");
                    checkList.add("?????????");
                    checkList.add("?????????");
                    checkList.add("????????????");
                    checkList.add("????????????");
                    checkList.add("???????????? ?????????");
                }else{
                    toggleButton5.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.beach_unclicked));
                    checkList.remove("????????????");
                    checkList.remove("?????????");
                    checkList.remove("??????");
                    checkList.remove("??????");
                    checkList.remove("?????????");
                    checkList.remove("?????????");
                    checkList.remove("????????????");
                    checkList.remove("????????????");
                    checkList.remove("???????????? ?????????");
                }
            }
        });
        toggleButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton6.isChecked()){
                    toggleButton6.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.trekking));
                    checkList.add("?????????");
                    checkList.add("????????? ?????????");
                    checkList.add("?????????");
                    checkList.add("?????? ?????????");
                    checkList.add("?????? ?????????");
                    checkList.add("?????? ??????");
                    checkList.add("?????? ??????");
                    checkList.add("???");
                    checkList.add("?????????");
                    checkList.add("??????");
                }else{
                    toggleButton6.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.trekking_unclicked));
                    checkList.remove("?????????");
                    checkList.remove("????????? ?????????");
                    checkList.remove("?????????");
                    checkList.remove("?????? ?????????");
                    checkList.remove("?????? ?????????");
                    checkList.remove("?????? ??????");
                    checkList.remove("?????? ??????");
                    checkList.remove("???");
                    checkList.remove("?????????");
                    checkList.remove("??????");
                }
            }
        });
        toggleButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton7.isChecked()){
                    toggleButton7.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.sakura));
                    checkList.add("???????????? ????????? ?????? ?????????");
                    checkList.add("?????????");
                    checkList.add("????????? ?????????");
                    checkList.add("?????????");
                    checkList.add("????????????");
                    checkList.add("????????????.mp3");

                }else{
                    toggleButton7.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.sakura_unclicked));
                    checkList.remove("???????????? ????????? ?????? ?????????");
                    checkList.remove("?????????");
                    checkList.remove("????????? ?????????");
                    checkList.remove("?????????");
                    checkList.remove("????????????");
                    checkList.remove("????????????.mp3");
                }
            }
        });
        toggleButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton8.isChecked()){
                    toggleButton8.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.camping_tent));
                    checkList.add("??????");
                    checkList.add("??????");
                    checkList.add("??????");
                    checkList.add("?????????");
                    checkList.add("??????");
                    checkList.add("??????");
                    checkList.add("???");
                    checkList.add("???,??????");
                    checkList.add("?????????,?????????");
                    checkList.add("?????? ????????????");
                    checkList.add("???????????? ???");
                    checkList.add("??????");
                    checkList.add("????????? ??????");


                }else{
                    toggleButton8.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.campingtent_unclicked));
                    checkList.remove("??????");
                    checkList.remove("??????");
                    checkList.remove("??????");
                    checkList.remove("?????????");
                    checkList.remove("??????");
                    checkList.remove("??????");
                    checkList.remove("???");
                    checkList.remove("???,??????");
                    checkList.remove("?????????,?????????");
                    checkList.remove("?????? ????????????");
                    checkList.remove("???????????? ???");
                    checkList.remove("??????");
                    checkList.remove("????????? ??????");
                }
            }
        });
        toggleButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton9.isChecked()){
                    toggleButton9.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.barbell_clicked));
                    checkList.add("??????");
                    checkList.add("????????????");
                    checkList.add("?????????");
                    checkList.add("??????");
                    checkList.add("?????????");
                    checkList.add("?????????");

                }else{
                    toggleButton9.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.barbell_unclicked));
                    checkList.remove("??????");
                    checkList.remove("????????????");
                    checkList.remove("?????????");
                    checkList.remove("??????");
                    checkList.remove("?????????");
                    checkList.remove("?????????");
                }
            }
        });
        toggleButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton10.isChecked()){
                    toggleButton10.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.backpack_clicked));
                    checkList.add("??????,????????????");
                    checkList.add("????????????");
                    checkList.add("?????????");
                    checkList.add("??????");
                    checkList.add("???????????????");
                    checkList.add("???????????????");

                }else{
                    toggleButton10.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.backpack_unclicked));
                    checkList.remove("??????,????????????");
                    checkList.remove("????????????");
                    checkList.remove("?????????");
                    checkList.remove("??????");
                    checkList.remove("???????????????");
                    checkList.remove("???????????????");
                }
            }
        });
        toggleButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton11.isChecked()){
                    toggleButton11.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.ski_clicked));
                    checkList.add("????????????");
                    checkList.add("????????? ?????????");
                    checkList.add("??????");
                    checkList.add("?????? ?????? ??????");
                    checkList.add("?????????");
                    checkList.add("????????????");
                    checkList.add("?????????");
                    checkList.add("?????? ????????????");
                    checkList.add("?????? ??????");
                    checkList.add("????????? ??????");
                }else{
                    toggleButton11.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.ski_unclicked));
                    checkList.remove("????????????");
                    checkList.remove("????????? ?????????");
                    checkList.remove("??????");
                    checkList.remove("?????? ?????? ??????");
                    checkList.remove("?????????");
                    checkList.remove("????????????");
                    checkList.remove("?????????");
                    checkList.remove("?????? ????????????");
                    checkList.remove("?????? ??????");
                    checkList.remove("????????? ??????");

                }
            }
        });
        toggleButton12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton12.isChecked()){
                    toggleButton12.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.bike_clicked));
                    checkList.add("??????");
                    checkList.add("??????");
                    checkList.add("??????");
                    checkList.add("??????");
                    checkList.add("???????????? ????????????");
                    checkList.add("????????? ??????");
                    checkList.add("????????? ?????? ?????????");
                    checkList.add("?????????");

                }else{
                    toggleButton12.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.bike_unclicked));
                    checkList.remove("??????");
                    checkList.remove("??????");
                    checkList.remove("??????");
                    checkList.remove("??????");
                    checkList.remove("???????????? ????????????");
                    checkList.remove("????????? ??????");
                    checkList.remove("????????? ?????? ?????????");
                    checkList.remove("?????????");

                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String result;
                if(checkedId == R.id.radiobutton){
                    btn_Group.setVisibility(View.VISIBLE);
                    z.setVisibility(View.VISIBLE);
                    purpose = "0";
                }else{
                    btn_Group.setVisibility(View.INVISIBLE);
                    z.setVisibility(View.INVISIBLE);
                    toggleButton1.setChecked(false);
                    toggleButton2.setChecked(false);
                    toggleButton3.setChecked(false);
                    toggleButton4.setChecked(false);
                    toggleButton5.setChecked(false);
                    toggleButton6.setChecked(false);
                    toggleButton7.setChecked(false);
                    toggleButton8.setChecked(false);
                    toggleButton9.setChecked(false);
                    toggleButton10.setChecked(false);
                    toggleButton11.setChecked(false);
                    toggleButton12.setChecked(false);
                    toggleButton1.setBackgroundDrawable(getResources().getDrawable(R.drawable.toiletries_unclicked));
                    toggleButton2.setBackgroundDrawable(getResources().getDrawable(R.drawable.cloth_unclicked));
                    toggleButton3.setBackgroundDrawable(getResources().getDrawable(R.drawable.baby_unclicked));
                    toggleButton4.setBackgroundDrawable(getResources().getDrawable(R.drawable.flight_unclicked));
                    toggleButton5.setBackgroundDrawable(getResources().getDrawable(R.drawable.beach_unclicked));
                    toggleButton6.setBackgroundDrawable(getResources().getDrawable(R.drawable.trekking_unclicked));
                    toggleButton7.setBackgroundDrawable(getResources().getDrawable(R.drawable.sakura_unclicked));
                    toggleButton8.setBackgroundDrawable(getResources().getDrawable(R.drawable.campingtent_unclicked));
                    toggleButton9.setBackgroundDrawable(getResources().getDrawable(R.drawable.barbell_unclicked));
                    toggleButton10.setBackgroundDrawable(getResources().getDrawable(R.drawable.backpack_unclicked));
                    toggleButton11.setBackgroundDrawable(getResources().getDrawable(R.drawable.ski_unclicked));
                    toggleButton12.setBackgroundDrawable(getResources().getDrawable(R.drawable.bike_unclicked));
                    if(checkList != null){
                        checkList.clear();
                        }
                    purpose = "1";
                    }
                }

        });
        nextStep = (Button) rootView.findViewById(R.id.nextStep);
        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < check1.length; i++){
                    if(check1[i] == true){
                        nextId.add(ListId.get(i));
                    }
                }

                if(edit_title.getText().toString().length() == 0){
                    Toast.makeText(getActivity(), "????????? ????????? ?????????", Toast.LENGTH_SHORT).show();
                }else if(dateText.length() == 0){
                    Toast.makeText(getActivity(), "????????? ????????? ?????????", Toast.LENGTH_SHORT).show();
                }else {
                    if(checkList.size() > 0){

                        str = edit_title.getText().toString();
                        intent = new Intent(getActivity(), listAddDel.class);
                        intent.putExtra("str", str);
                        intent.putExtra("year", myear);
                        intent.putExtra("month", mmonth);
                        intent.putExtra("day", mday);
                        intent.putExtra("purpose", purpose);
                        intent.putExtra("checkList", checkList);
                        if(nextId.size() != 0){
                            intent.putExtra("nextid",nextId);
                        }


                    }else{
                        str = edit_title.getText().toString();
                        intent = new Intent(getActivity(), listAddDel.class);
                        intent.putExtra("str", str);
                        intent.putExtra("year", myear);
                        intent.putExtra("month", mmonth);
                        intent.putExtra("day", mday);
                        intent.putExtra("purpose", purpose);
                        if(nextId.size() != 0){
                            intent.putExtra("nextid",nextId);
                        }

                    }

                    toggleButton1.setChecked(false);
                    toggleButton2.setChecked(false);
                    toggleButton3.setChecked(false);
                    toggleButton4.setChecked(false);
                    toggleButton5.setChecked(false);
                    toggleButton6.setChecked(false);
                    toggleButton7.setChecked(false);
                    toggleButton8.setChecked(false);
                    toggleButton9.setChecked(false);
                    toggleButton10.setChecked(false);
                    toggleButton11.setChecked(false);
                    toggleButton12.setChecked(false);
                    toggleButton1.setBackgroundDrawable(getResources().getDrawable(R.drawable.toiletries_unclicked));
                    toggleButton2.setBackgroundDrawable(getResources().getDrawable(R.drawable.cloth_unclicked));
                    toggleButton3.setBackgroundDrawable(getResources().getDrawable(R.drawable.baby_unclicked));
                    toggleButton4.setBackgroundDrawable(getResources().getDrawable(R.drawable.flight_unclicked));
                    toggleButton5.setBackgroundDrawable(getResources().getDrawable(R.drawable.beach_unclicked));
                    toggleButton6.setBackgroundDrawable(getResources().getDrawable(R.drawable.trekking_unclicked));
                    toggleButton7.setBackgroundDrawable(getResources().getDrawable(R.drawable.sakura_unclicked));
                    toggleButton8.setBackgroundDrawable(getResources().getDrawable(R.drawable.campingtent_unclicked));
                    toggleButton9.setBackgroundDrawable(getResources().getDrawable(R.drawable.barbell_unclicked));
                    toggleButton10.setBackgroundDrawable(getResources().getDrawable(R.drawable.backpack_unclicked));
                    toggleButton11.setBackgroundDrawable(getResources().getDrawable(R.drawable.ski_unclicked));
                    toggleButton12.setBackgroundDrawable(getResources().getDrawable(R.drawable.bike_unclicked));

                    radioButton.setChecked(true);
                    edit_title.setText("");
                    dateText.setText("");

                    startActivity(intent);

                    for(int i = 0; i < nextId.size(); i++){
                        nextId.remove(0);
                    }
                    for(int i = 0; i < check1.length; i++){
                        check1[i] = false;
                    }
                }
            }
        });
        return rootView;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.menu_mylistadd,menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        builder.setTitle("??? ????????? ??????");
        builder.setMultiChoiceItems(name1,check1, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                check1[which] = isChecked;
            }
        });
        builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"?????????????????????.",Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();

        return super.onOptionsItemSelected(item);
    }

    private void initUI(ViewGroup rootView){

        setHasOptionsMenu(true);
        radioButton = rootView.findViewById(R.id.radiobutton);
        toggleButton1 = rootView.findViewById(R.id.toggleButton1);
        toggleButton2 = rootView.findViewById(R.id.toggleButton2);
        toggleButton3 = rootView.findViewById(R.id.toggleButton3);
        toggleButton4 = rootView.findViewById(R.id.toggleButton4);
        toggleButton5 = rootView.findViewById(R.id.toggleButton5);
        toggleButton6 = rootView.findViewById(R.id.toggleButton6);
        toggleButton7 = rootView.findViewById(R.id.toggleButton7);
        toggleButton8 = rootView.findViewById(R.id.toggleButton8);
        toggleButton9 = rootView.findViewById(R.id.toggleButton9);
        toggleButton10 = rootView.findViewById(R.id.toggleButton10);
        toggleButton11 = rootView.findViewById(R.id.toggleButton11);
        toggleButton12 = rootView.findViewById(R.id.toggleButton12);
        textView1 = rootView.findViewById(R.id.textView1);
        textView2 = rootView.findViewById(R.id.textView2);
        textView3 = rootView.findViewById(R.id.textView3);
        textView4 = rootView.findViewById(R.id.textView4);
        textView5 = rootView.findViewById(R.id.textView5);
        textView6 = rootView.findViewById(R.id.textView6);
        textView7 = rootView.findViewById(R.id.textView7);
        textView8 = rootView.findViewById(R.id.textView8);
        textView9 = rootView.findViewById(R.id.textView9);
        textView10 = rootView.findViewById(R.id.textView10);
        textView11= rootView.findViewById(R.id.textView11);
        textView12= rootView.findViewById(R.id.textView12);
        radioGroup = rootView.findViewById(R.id.radioGroup);
        z = rootView.findViewById(R.id.z);
        checkList = new ArrayList<String>();
        btn_Group = rootView.findViewById(R.id.btn_group);
        edit_title = rootView.findViewById(R.id.edit_title);
        dateText = rootView.findViewById(R.id.dateText);
    }

    public void newSetDate(String year,String month,String day){
        myear = year;
        mmonth = month;
        mday = day;
        dateText.setText("  "+year+"??? "+month+"??? "+day+"???");
    }
    @Override
    public void onResume() {
        super.onResume();
        if(checkList != null)
        checkList.clear();

        ListId.clear();

        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        database = dbHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("select mylist_id from mylist_note", null);
        int recordCount = cursor.getCount();

        for (int i = 0; i < recordCount; i++) {
            cursor.moveToNext();

            int id = cursor.getInt(0);

            ListId.add(id);
        }
        cursor.close();

        if(nextId != null)
            nextId.clear();
    }
}


