package com.nmp90.bghistory;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import com.nmp90.bghistory.DB.HistoryEventModel;
import com.nmp90.bghistory.DB.HistoryOpenHelper;

/**
 * Created by NMP on 1/13/14.
 */
public class DetailedEventActivity extends BaseActivity {
    TextView tvEventYear,
     tvEventTitle,
     tvEventDesc,
     tvEventPlace,
     tvEventResult,
     tvEventLeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_event);
        bindViews();

        int eventId = getIntent().getIntExtra(Constants.EXTRA_EVENT_ID, 0);

        HistoryOpenHelper historyDbHelper = new HistoryOpenHelper(this);
        historyDbHelper.openDataBase();
        HistoryEventModel event = historyDbHelper.getSingleEvent(eventId);
        historyDbHelper.close();
        Resources resources = getResources();

        tvEventYear.setText(resources.getString(R.string.event_year) + " " + event.getYear());
        tvEventTitle.setText("\"" + event.getTitle() + "\"");
        tvEventDesc.setText(Html.fromHtml(event.getDescription()));
        tvEventPlace.setText("\n" +  resources.getString(R.string.event_place) + " "  + event.getPlace());
        tvEventLeader.setText("\n" + resources.getString(R.string.event_leaders) + " "  + event.getLeader());
        tvEventResult.setText("\n" + resources.getString(R.string.event_result) + " "  + Html.fromHtml(event.getResult()));
        tvEventDesc.setText(Html.fromHtml(event.getDescription()));

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
        actionBar.setTitle(getResources().getString(R.string.event_details));
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void bindViews() {
        tvEventYear = (TextView)findViewById(R.id.sEvent_Year);
        tvEventTitle = (TextView)findViewById(R.id.sEvent_Title);
        tvEventDesc = (TextView)findViewById(R.id.sEvent_Description);
        tvEventPlace = (TextView)findViewById(R.id.sEvent_Place);
        tvEventResult = (TextView)findViewById(R.id.sEvent_Result);
        tvEventLeader = (TextView)findViewById(R.id.sEvent_Leader);
    }
}
