package org.jeonfeel.perpack7;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyListNoteAdapter extends RecyclerView.Adapter<MyListNoteAdapter.ViewHolder>
        implements OnMyNoteItemClickListener {

        ArrayList<MyListNote> items = new ArrayList<MyListNote>();
        OnMyNoteItemClickListener listener;

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View itemView = inflater.inflate(R.layout.mylistnote_item, viewGroup, false);

            return new ViewHolder(itemView, this);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
            MyListNote item = items.get(position);
            viewHolder.setItem(item);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public void addItem(MyListNote item) {
            items.add(item);
        }
        public void removeItem(int position){
            items.remove(position);
        }
        public void refresh(){
            notifyDataSetChanged();
        }
        public int itemCheckNull(){
        if(items.size() == 0){
            return 0;
        }else{
            return 1;
        }
    }
        public void setItems(ArrayList<MyListNote> items) {
            this.items = items;
        }

        public MyListNote getItem(int position) {
            return items.get(position);
        }

        public void setOnItemClickListener(OnMyNoteItemClickListener listener) {
            this.listener = listener;
        }

        @Override
        public void onItemClick2(ViewHolder holder, View view, int position) {
            if (listener != null) {
                listener.onItemClick2(holder, view, position);
            }
        }
        static class ViewHolder extends RecyclerView.ViewHolder {

            LinearLayout layout12;

            ImageView purposeImageView2;

            TextView contentsTextView2;

            int id = getAdapterPosition();

            public ViewHolder(final View itemView, final MyListNoteAdapter listener) {
                super(itemView);

                layout12 = itemView.findViewById(R.id.layout12);

                purposeImageView2 = itemView.findViewById(R.id.purpose2);

                contentsTextView2 = itemView.findViewById(R.id.contentsTextView2);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = getAdapterPosition();
                        if (listener != null) {
                            listener.onItemClick2(ViewHolder.this, view, position);
                        }
                    }
                });
            }
            public void setItem(MyListNote item) {
                String purpose = item.getPurpose2();
                int purposeIndex = Integer.parseInt(purpose);
                setPurposeImage(purposeIndex);
                contentsTextView2.setText(item.getContents2());
            }

            public void setPurposeImage(int purposeIndex) {
                switch (purposeIndex) {
                    case 0:
                        purposeImageView2.setImageResource(R.drawable.check);
                        break;
                }
            }
        }
    }
