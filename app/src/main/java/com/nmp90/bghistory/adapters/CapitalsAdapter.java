package com.nmp90.bghistory.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.nmp90.bghistory.DB.CapitalSimpleModel;
import com.nmp90.bghistory.R;

import java.util.ArrayList;

/**
 * Created by georgi.mirchev on 1/14/14.
 */
public class CapitalsAdapter extends ArrayAdapter<CapitalSimpleModel> {
        Context context;
        int layoutResourceId;
        ArrayList<CapitalSimpleModel> data = null;

public CapitalsAdapter(Context context, int layoutResourceId, ArrayList<CapitalSimpleModel> data){
        super(context, layoutResourceId, data);

        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        }

@Override
public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
    CapitalHolder holder = null;
        CapitalSimpleModel capital = data.get(position);

        if(row == null) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);

        holder = new CapitalHolder();
        holder.tvCapitalName = (TextView)row.findViewById(R.id.tv_capital_name);
        holder.capitalId = capital.getId();

        row.setTag(holder);
        }
        else {
        holder = (CapitalHolder)row.getTag();
        }

        //We set the id which we pass alter to the main activity
        holder.tvCapitalName.setText(capital.getName() + " (" + capital.getPeriod() + ")");
        holder.capitalId = capital.getId();

        return row;
        }

public static class CapitalHolder
{
    public TextView tvCapitalName;
    public int capitalId;
}


}