package com.bradleyeaton.simplewatchface.config;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bradleyeaton.simplewatchface.R;
import org.jetbrains.annotations.NotNull;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private ListItem[] dataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.list_text_view);
            imageView = itemView.findViewById(R.id.list_image_view);
        }
    }

    public ListAdapter(ListItem[] dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View tv = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder vh = new ViewHolder(tv);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.textView.setText(dataSet[position].getLabel());
        holder.imageView.setBackground(dataSet[position].getBackground());
        holder.imageView.setForeground(dataSet[position].getForeground());
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }

}
