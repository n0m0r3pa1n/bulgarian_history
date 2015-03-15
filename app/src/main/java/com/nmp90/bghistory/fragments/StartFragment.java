package com.nmp90.bghistory.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nmp90.bghistory.BaseDrawerActivity;
import com.nmp90.bghistory.CapitalsActivity;
import com.nmp90.bghistory.R;
import com.nmp90.bghistory.YearsActivity;
import com.nmp90.bghistory.utils.AdManager;


/**
 * Created by georgi.mirchev on 1/10/14.
 */
public class StartFragment extends Fragment {
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */


    public StartFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        setupAboutAppScreen(rootView);
        setupCapitalsScreen(rootView);
        setupYearsScreen(rootView);
        setupPeriodsScreen(rootView);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.add(Menu.NONE, R.id.menu_search_event , Menu.NONE, "Търсене по събитие");
        menu.add(Menu.NONE, R.id.menu_search_year , Menu.NONE, "Търсене по година");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SearchDialogFragment dialogFragment = null;
        switch(item.getItemId()){
            case R.id.menu_search_year:
                dialogFragment = new SearchDialogFragment();
                dialogFragment.setSearchCriteria(2);
                break;
            case (R.id.menu_search_event):
                dialogFragment = new SearchDialogFragment();
                dialogFragment.setSearchCriteria(1);
                break;
        }

        if(dialogFragment != null) {
            dialogFragment.show(getActivity().getSupportFragmentManager(), null);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setupPeriodsScreen(View rootView) {
        LinearLayout btnPeriods = (LinearLayout)rootView.findViewById(R.id.btn_periods);
        ImageView ivPeriods = (ImageView)rootView.findViewById(R.id.iv_periods);
        ivPeriods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOrCloseDrawer();
            }
        });
        btnPeriods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrCloseDrawer();
            }
        });
    }

    private void openOrCloseDrawer(){
        BaseDrawerActivity mNavigationDrawerFragment = (BaseDrawerActivity)
                getActivity();

        if(mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            mNavigationDrawerFragment.openDrawer();
    }

    private void setupYearsScreen(View rootView){
        LinearLayout btnYears = (LinearLayout)rootView.findViewById(R.id.btn_years);
        ImageView ivYears = (ImageView)rootView.findViewById(R.id.iv_years);
        ivYears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), YearsActivity.class);
                startActivity(i);
            }
        });
        btnYears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), YearsActivity.class);
                startActivity(i);
            }
        });
    }

    private void setupCapitalsScreen(View rootView) {
        LinearLayout btnCapitals = (LinearLayout)rootView.findViewById(R.id.btn_capitals);
        ImageView ivCapitals = (ImageView)rootView.findViewById(R.id.iv_capitals);

        btnCapitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CapitalsActivity.class);
                startActivity(i);
            }
        });

        ivCapitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), CapitalsActivity.class);
                startActivity(i);
            }
        });
    }
    private void setupAboutAppScreen(View rootView) {
        LinearLayout btnAbout = (LinearLayout)rootView.findViewById(R.id.btn_about_app);
        ImageView ivAbout = (ImageView)rootView.findViewById(R.id.iv_about_app);
        ivAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAboutAppDialog();
            }
        });
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAboutAppDialog();
            }
        });

    }

    public void createAboutAppDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_about, null));
        builder.setTitle("За приложението").setMessage(R.string.app_info);
        builder.setPositiveButton("ОК", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
