package com.nmp90.bghistory.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.nmp90.bghistory.DB.HistoryOpenHelper;
import com.nmp90.bghistory.R;
import com.nmp90.bghistory.SearchActivity;

import java.util.ArrayList;

/**
 * Created by NMP on 1/25/14.
 */
public class SearchDialogFragment extends DialogFragment {

    public int searchCriteria = 0;
    final String KEY_SEARCH_CRITERIA = "SEARCH_CRITERIA";

    public SearchDialogFragment() { searchCriteria = 1; }
    public void setSearchCriteria(int searchCriteria){
        this.searchCriteria = searchCriteria;
    }



    /* (non-Javadoc)
     * @see android.app.DialogFragment#onCreateDialog(android.os.Bundle)
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        Context context = getActivity();
        ArrayList<String> hints = null;
        if(savedInstanceState != null){
            searchCriteria = savedInstanceState.getInt(KEY_SEARCH_CRITERIA);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_search_dialog, null);

        builder.setView(v);

        final AutoCompleteTextView etSearchedVal = (AutoCompleteTextView)v.findViewById(R.id.dialog_entered_val);

        if(searchCriteria == 2){
            etSearchedVal.setInputType(InputType.TYPE_CLASS_NUMBER);
        }


        HistoryOpenHelper historyDbHelper = new HistoryOpenHelper(context);
        try {
            historyDbHelper.openDataBase();
            //Search by title
            if(searchCriteria == 1) {
                hints = historyDbHelper.getYearsOrTitles("Title");
                etSearchedVal.setHint("Заглавие на събитие");
            }
            //Search by year
            else if(searchCriteria == 2) {
                hints = historyDbHelper.getYearsOrTitles("Year");
                etSearchedVal.setHint("Година на събитие");
            }
        } catch (Exception e){ }
        finally {
            historyDbHelper.close();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, hints);

        etSearchedVal.setAdapter(adapter);

        builder.setMessage("Търсене на събитие")
                .setPositiveButton("Търси", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String fieldSearched = null;
                        if(searchCriteria == 1) {
                            fieldSearched = "e.Title";
                        }
                        else if(searchCriteria == 2) {
                            fieldSearched = "e.Year";
                        }
                        String searchedEvent = etSearchedVal.getText().toString();

                        Intent i = new Intent(getActivity(), SearchActivity.class);
                        i.putExtra("FIELD_SEARCHED", fieldSearched);
                        i.putExtra("SEARCHED_EVENT", searchedEvent);

                        startActivity(i);



                    }
                })
                .setNegativeButton("Отказ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        return builder.create();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SEARCH_CRITERIA, searchCriteria);
    }
}
