package com.nmp90.bghistory;

import android.content.Intent;
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
 * Created by NMP on 1/25/14.
 */
public class SearchActivity extends BaseActivity {
    ArrayList<HistoryEventModel> event = null;
    ListView lvYears;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_events);
        setUpActionBar();

        HistoryEventsAdapter eventAdapter = null;
        HistoryOpenHelper historyDbHelper = new HistoryOpenHelper(this);
        String fieldSearched = getIntent().getStringExtra("FIELD_SEARCHED");
        String searchedEvent = getIntent().getStringExtra("SEARCHED_EVENT");


        try {
            historyDbHelper.openDataBase();
            event = historyDbHelper.getDialogEvents(fieldSearched, searchedEvent);
            //Getting view from a custom listadapter
            eventAdapter = new HistoryEventsAdapter(this, R.layout.event_item_row, event);
            lvYears = (ListView) findViewById(R.id.list_view_search_events);
            lvYears.setAdapter(eventAdapter);
            lvYears.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    HistoryEventsAdapter.HistoryEventHolder eventHolder = (HistoryEventsAdapter.HistoryEventHolder) view.getTag();
                    lvYears.setItemChecked(i, true);

                    Intent intent = new Intent(getApplicationContext(), DetailedEventActivity.class);
                    intent.putExtra(Constants.EXTRA_EVENT_ID, eventHolder.eventId);
                    startActivity(intent);


                }
            });

        } catch(Exception e) {

        } finally {
            historyDbHelper.close();
        }
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

    private void setUpActionBar() {
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Търсене на събитие");
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}