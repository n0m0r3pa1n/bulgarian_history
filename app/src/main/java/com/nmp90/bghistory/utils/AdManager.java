package com.nmp90.bghistory.utils;

import android.app.Activity;

import com.somkjebmua.aefdzebbln127275.Prm;


/**
 * Created by NMP on 6/2/2014.
 */
public class AdManager {


    public static void showAppWallAd(Activity activity){
        Prm ma=new Prm(activity, null, false);
        ma.runAppWall();
    }

}
