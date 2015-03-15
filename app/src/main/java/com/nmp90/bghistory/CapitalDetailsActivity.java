package com.nmp90.bghistory;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nmp90.bghistory.DB.CapitalModel;
import com.nmp90.bghistory.DB.HistoryOpenHelper;
import com.nmp90.bghistory.utils.AdManager;

/**
 * Created by NMP on 1/19/14.
 */
public class CapitalDetailsActivity extends BaseActivity {
    TextView mTvName, mTvPeriod, mTvCitizens, mTvDescription;
    ImageView mBtnLocation, mIvCapitalPic;

    HistoryOpenHelper dbHelper;
    CapitalModel capital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capital_details);
        setUpActionBar();
        setupViews();
        int capitalId = getIntent().getIntExtra(Constants.EXTRA_EVENT_ID, 1);
        dbHelper = new HistoryOpenHelper(this);
        try {
            dbHelper.openDataBase();
            capital =  dbHelper.getSingleCapital(capitalId);
        }
        finally{
            dbHelper.close();
        }

        mTvName.setText(capital.getName());
        mTvCitizens.setText("Население: " + (capital.getCitizens() == 0 ? "Няма данни" : capital.getCitizens()) + "");
        mTvPeriod.setText("(" + capital.getPeriod() + ")");
        mTvDescription.setText(Html.fromHtml(capital.getContent()));
        mBtnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?q=" + capital.getLat() + "," + capital.getLng() + ""));

                startActivity(intent);
            }
        });
        String uri = "drawable/" + capital.getPicture();
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable image = getResources().getDrawable(imageResource);
        mIvCapitalPic.setImageDrawable(image);

        AdManager.showAppWallAd(this);
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
        actionBar.setTitle(getResources().getString(R.string.activity_capital_detail));
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setupViews() {
        mBtnLocation = (ImageView)findViewById(R.id.btn_capital_details_location);
        mIvCapitalPic = (ImageView)findViewById(R.id.iv_capital_pic);
        mTvName = (TextView)findViewById(R.id.tv_capital_detail_name);
        mTvCitizens = (TextView)findViewById(R.id.tv_capital_detail_people);
        mTvPeriod = (TextView)findViewById(R.id.tv_capital_detail_period);
        mTvDescription = (TextView)findViewById(R.id.tv_capital_detail_desc);
    }
}
