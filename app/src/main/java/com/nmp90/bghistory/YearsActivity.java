package com.nmp90.bghistory;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by georgi.mirchev on 1/13/14.
 */
public class YearsActivity extends BaseActivity {

    ListView lv;
    ArrayAdapter<String> adapter;
    EditText inputSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_years);

        Context context = this;
        String[] eventsYears = getResources().getStringArray(R.array.historical_events_and_years);
        lv = (ListView)findViewById(R.id.list_view_events_years);
        lv.setCacheColorHint(Color.parseColor("#2F2F2F"));

        inputSearch = (EditText)findViewById(R.id.et_search_event_year);
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                adapter.getFilter().filter(arg0);

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub

            }
        });

        adapter = new ArrayAdapter<String>(this, R.layout.year_item_row, R.id.years_single_item, eventsYears);
        lv.setAdapter(adapter);

        setUpActionBar();

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
        actionBar.setTitle(getResources().getString(R.string.activity_years));
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
