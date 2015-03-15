package com.nmp90.bghistory;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nmp90.bghistory.DB.CapitalSimpleModel;
import com.nmp90.bghistory.DB.HistoryOpenHelper;
import com.nmp90.bghistory.adapters.CapitalsAdapter;

import java.util.ArrayList;

/**
 * Created by georgi.mirchev on 1/14/14.
 */
public class CapitalsActivity extends BaseActivity {

    ListView mLvCapitals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capitals);

        mLvCapitals = (ListView)findViewById(R.id.lv_capitals);

        HistoryOpenHelper historyHelper = new HistoryOpenHelper(this);
        ArrayList<CapitalSimpleModel> capitals = null;
        try {
            historyHelper.openDataBase();
            capitals = historyHelper.getCapitals();
        }
        finally {
            historyHelper.close();
        }

        CapitalsAdapter adapter = new CapitalsAdapter(this, R.layout.capital_item_row, capitals);
        mLvCapitals.setAdapter(adapter);
        mLvCapitals.setCacheColorHint(Color.parseColor("#2F2F2F"));

        mLvCapitals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CapitalsAdapter.CapitalHolder eventHolder = (CapitalsAdapter.CapitalHolder)view.getTag();
                mLvCapitals.setItemChecked(i, true);

                Intent intent = new Intent(getApplicationContext(), CapitalDetailsActivity.class);
                intent.putExtra(Constants.EXTRA_EVENT_ID, eventHolder.capitalId);
                startActivity(intent);
            }
        });

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
        actionBar.setTitle(getResources().getString(R.string.activity_capitals));
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
