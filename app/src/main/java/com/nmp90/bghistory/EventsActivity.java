package com.nmp90.bghistory;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nmp90.bghistory.DB.HistoryEventModel;
import com.nmp90.bghistory.DB.HistoryOpenHelper;
import com.nmp90.bghistory.adapters.HistoryEventsAdapter;

import java.util.ArrayList;

/**
 * Created by georgi.mirchev on 1/10/14.
 */
public class EventsActivity extends BaseActivity {

    ListView mEventsListView;
    ArrayList<HistoryEventModel> events = null;
    HistoryEventsAdapter eventAdapter = null;
    String[] periods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        int selectedPeriod = getIntent().getIntExtra(Constants.EXTRA_SELECTED_PERIOD, 1);
        mEventsListView = (ListView)findViewById(R.id.list_view_events);

        HistoryOpenHelper historyDbHelper = new HistoryOpenHelper(this);
        historyDbHelper.openDataBase();
        events = historyDbHelper.getHistoryEvents(selectedPeriod);
        historyDbHelper.close();

        eventAdapter = new HistoryEventsAdapter(this, R.layout.event_item_row, events);
        mEventsListView.setAdapter(eventAdapter);
        mEventsListView.setCacheColorHint(Color.parseColor("#2F2F2F"));
        mEventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HistoryEventsAdapter.HistoryEventHolder eventHolder = (HistoryEventsAdapter.HistoryEventHolder)view.getTag();
                mEventsListView.setItemChecked(i, true);

                Intent intent = new Intent(getApplicationContext(), DetailedEventActivity.class);
                intent.putExtra(Constants.EXTRA_EVENT_ID, eventHolder.eventId);
                startActivity(intent);


            }
        });

        periods = getResources().getStringArray(R.array.history_periods);
        setUpActionBar(selectedPeriod);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpActionBar(int selectedPeriod) {
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(periods[selectedPeriod]);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
