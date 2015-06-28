package com.appsagainst.humanity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.appsagainst.humanity.POJO.WhiteCard;
import com.appsagainst.humanity.R;

import java.util.List;

import butterknife.ButterKnife;

public class CardAdapter extends ArrayAdapter<WhiteCard> {

    public CardAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public CardAdapter(Context context, int resource, List<WhiteCard> items) {
        super(context, resource, items);
    }

    @Override public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(getContext()).inflate(R.layout.whatever, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.name.setText("John Doe");
        // etc...

        return view;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    static class ViewHolder {
        @Bind(R.id.title)
        TextView name;
        @Bind(R.id.job_title) TextView jobTitle;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}