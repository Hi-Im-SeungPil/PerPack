package org.jeonfeel.perpack7;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import static org.jeonfeel.perpack7.List_View.mCheck;

public class ButtonListAdapter2 extends BaseAdapter implements  View.OnClickListener {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<String> data;
    int real;

    public interface ListBtnClickListener {
        void onListBtnClick(int position) ;
    }
    private ListBtnClickListener listBtnClickListener ;

    public ButtonListAdapter2(Context context, ArrayList<String> data, int real, ListBtnClickListener clickListener) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
        this.real = real;
        this.listBtnClickListener = clickListener ;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

            final View view = layoutInflater.inflate(R.layout.customlistview2, null);
            final CheckBox checkBox = view.findViewById(R.id.checkedbox);
            final TextView textView = view.findViewById(R.id.custom_textView1);
            textView.setText(data.get(position));

            Button button = view.findViewById(R.id.btn_custom);

            button.setTag(position);
            button.setOnClickListener(this);

            checkBox.setFocusable(false);
            checkBox.setClickable(false);
            button.setFocusable(false);

                    if (mCheck.get(position) == true) {
                        checkBox.setChecked(true);
                        textView.setTextColor(Color.parseColor("#D5D5D5"));
                    } else {
                        checkBox.setChecked(false);
                        textView.setTextColor(Color.BLACK);
                    }
            return view;
    }
    public void onClick(View v) {
        if (this.listBtnClickListener != null) {
            this.listBtnClickListener.onListBtnClick((int)v.getTag()) ;
        }
    }
    public void addItem(String s){
        data.add(s);
        notifyDataSetChanged();
    }
    }

