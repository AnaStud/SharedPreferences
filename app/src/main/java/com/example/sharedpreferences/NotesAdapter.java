package com.example.sharedpreferences;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private ArrayList<UserNote> dataSet;

    public NotesAdapter(ArrayList<UserNote> dataSet) {
        this.dataSet = dataSet;
    }

    public void setNewData(ArrayList<UserNote> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getNote().setText(dataSet.get(position).note);
        viewHolder.getDate().setText(dataSet.get(position).date.toString());
        viewHolder.getType().setText(dataSet.get(position).type);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView note;
        private final TextView date;
        private final TextView type;

        public ViewHolder(View view) {
            super(view);
            note = view.findViewById(R.id.note_text_view);
            date = view.findViewById(R.id.date_text_view);
            type = view.findViewById(R.id.type_text_view);
        }

        public TextView getNote() {
            return note;
        }

        public TextView getDate() {
            return date;
        }

        public TextView getType() {
            return type;
        }
    }
}