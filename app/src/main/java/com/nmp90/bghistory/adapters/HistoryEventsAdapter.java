package com.nmp90.bghistory.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nmp90.bghistory.DB.HistoryEventModel;
import com.nmp90.bghistory.R;

import java.util.ArrayList;

/**
 * Created by georgi.mirchev on 1/10/14.
 */
public class HistoryEventsAdapter extends ArrayAdapter<HistoryEventModel> {
    Context context;
    int layoutResourceId;
    ArrayList<HistoryEventModel> data = null;

    public HistoryEventsAdapter(Context context, int layoutResourceId, ArrayList<HistoryEventModel> data){
        super(context, layoutResourceId, data);

        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        HistoryEventHolder holder = null;
        HistoryEventModel event = data.get(position);

        if(row == null) {

            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new HistoryEventHolder();
            holder.eventTitle = (TextView)row.findViewById(R.id.eventTitle);
            holder.eventYear = (TextView)row.findViewById(R.id.eventYear);
            holder.eventId = event.getId();

            row.setTag(holder);
        }
        else {
            holder = (HistoryEventHolder)row.getTag();
        }

        //We set the id which we pass alter to the main activity
        holder.eventTitle.setText(event.getTitle());
        holder.eventYear.setText(event.getYear());
        holder.eventId = event.getId();

        return row;
    }

    public static class HistoryEventHolder
    {
        public TextView eventTitle;
        public TextView eventYear;
        public int eventId;
    }


}
