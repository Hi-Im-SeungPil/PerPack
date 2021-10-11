package org.jeonfeel.perpack4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>
        implements OnNoteItemClickListener {

    ArrayList<Note> items = new ArrayList<Note>();
    OnNoteItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.note_item, viewGroup, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Note item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Note item) {
        items.add(item);
    }
    public void removeItem(int position){
        items.remove(position);
    }
    public void refresh(){
        notifyDataSetChanged();
    }
    public void setItems(ArrayList<Note> items) {
        this.items = items;
    }

    public Note getItem(int position) {
        return items.get(position);
    }

    public void setOnItemClickListener(OnNoteItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }
    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout layout1;

        ImageView purposeImageView;

        TextView contentsTextView;

        TextView startTextView;

        TextView dateTextView;

        int id = getAdapterPosition();

        public ViewHolder(final View itemView, final OnNoteItemClickListener listener) {
            super(itemView);

            layout1 = itemView.findViewById(R.id.layout1);

            purposeImageView = itemView.findViewById(R.id.purpose);

            contentsTextView = itemView.findViewById(R.id.contentsTextView);

            dateTextView = itemView.findViewById(R.id.date);

            startTextView = itemView.findViewById(R.id.start);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }
        public void setItem(Note item) {
            String purpose = item.getPurpose();
            int purposeIndex = Integer.parseInt(purpose);
            setPurposeImage(purposeIndex);

            contentsTextView.setText(item.getContents());

            dateTextView.setText(item.getCreateDatestr());
        }

        public void setPurposeImage(int purposeIndex) {
            switch (purposeIndex) {
                case 0:
                    purposeImageView.setImageResource(R.drawable.images);
                    break;
                case 1:
                    purposeImageView.setImageResource(R.drawable.ilsang);
                    break;
            }
        }
    }
}

