package com.nmp90.bghistory.DB;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class HistoryOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "BGHISTORY_v3";
    private static final String DATABASE_PATH = "/data/data/com.nmp90.bghistory/databases/";

    private SQLiteDatabase bgHistoryDB;

    private Context myContext;

    public HistoryOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //createDatabase();

    }

    private void deleteDbFile(String name) {
        File file = new File(DATABASE_PATH, name);
        if (file.exists()) {
            myContext.deleteDatabase(name);
        }
    }

    //Check if database exists otherwise copies it from the assets folder

    public void createDatabase() {
        deleteDbFile("BGHISTORY");
        deleteDbFile("BGHISTORY_v2");

        File file = new File(DATABASE_PATH, DATABASE_NAME);
        boolean dbExist = file.exists();

        if (dbExist) {

            //Do nothing
        } else {
            //Creating an empty database
            this.getReadableDatabase();

            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database!");
            }
            this.close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        myContext.deleteDatabase("BGHISTORY");
        myContext.deleteDatabase("BGHISTORY_v2");
        createDatabase();
    }


    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = DATABASE_PATH + DATABASE_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    //Open the database
    public void openDataBase() throws SQLException {
        //Open the database
        String myPath = DATABASE_PATH + DATABASE_NAME;
        bgHistoryDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);

    }

    @Override
    public synchronized void close() {

        if (bgHistoryDB != null)
            bgHistoryDB.close();

        super.close();

    }

    public ArrayList<HistoryEventModel> getHistoryEvents(int topicID) {
        String sql = "select e.ID, e.Title, e.Year, e.Place, e.Leader, e.Result, e.Description, t.Name, t.ID from Events e, " +
                "Topics t where e.Topic = t.ID and t.ID = " + topicID + " ORDER BY e.Year asc";

        ArrayList<HistoryEventModel> events = new ArrayList<HistoryEventModel>();
        Cursor c = bgHistoryDB.rawQuery(sql, null);

        while (c.moveToNext()) {

            HistoryEventModel historyEvent = new HistoryEventModel(c.getInt(0), c.getString(1), c.getString(2), c.getString(3),
                    c.getString(4), c.getString(5), c.getString(6), c.getString(7), c.getInt(8));

            events.add(historyEvent);
        }
        c.close();
        return events;
    }

    public ArrayList<CapitalSimpleModel> getCapitals(){
        ArrayList<CapitalSimpleModel> results = new ArrayList<CapitalSimpleModel>();
        Cursor c = null;

        String sql = "select e.ID, e.Name, e.Period, e.Lat, e.Lng, e.Picture, e.Content, e.Citizens from Capitals e " +
                "ORDER BY e.ID asc";
        try {

            c = bgHistoryDB.rawQuery(sql, null);

            while (c.moveToNext()) {
                results.add(new CapitalSimpleModel(c.getInt(0), c.getString(1), c.getString(2)));
            }
        } catch (Exception e) {
        } finally {
            c.close();
        }

        return results;
    }

    public CapitalModel getSingleCapital(int id){
        CapitalModel capital = null;
        Cursor c = null;
        String sql = "select e.ID, e.Name, e.Period, e.Lat, e.Lng, e.Picture, e.Content, e.Citizens from Capitals e " +
                "where e.ID = " + id +
                " ORDER BY e.ID asc";
        try {

            c = bgHistoryDB.rawQuery(sql, null);

            while (c.moveToNext()) {

                capital = new CapitalModel(c.getInt(0), c.getString(1), c.getString(2),
                        c.getString(3), c.getString(4), c.getString(5), c.getString(6), c.getInt(7));

            }
        } catch (Exception e) {
        } finally {
            c.close();
        }

        return capital;
    }

    public HistoryEventModel getSingleEvent(int id) {

        HistoryEventModel historyEvent = null;
        Cursor c = null;

        String sql = "select e.ID, e.Title, e.Year, e.Place, e.Leader, e.Result, e.Description, t.Name, t.ID from Events e, " +
                "Topics t where e.Topic = t.ID and e.ID = " + id + " ORDER BY e.Year asc";
        try {

            c = bgHistoryDB.rawQuery(sql, null);

            while (c.moveToNext()) {

                historyEvent = new HistoryEventModel(c.getInt(0), c.getString(1), c.getString(2), c.getString(3),
                        c.getString(4), c.getString(5), c.getString(6), c.getString(7), c.getInt(8));

            }
        } catch (Exception e) {
        } finally {
            c.close();
        }

        return historyEvent;
    }

    public ArrayList<String> getYearsOrTitles(String searchedItem) {
        Cursor c = null;
        ArrayList<String> result = new ArrayList<String>();

        String sql = "select " + searchedItem + " from Events ORDER BY " + searchedItem + " ASC;";
        try {

            c = bgHistoryDB.rawQuery(sql, null);

            while (c.moveToNext()) {
                result.add(c.getString(0));
            }
        } catch (Exception e) {
        } finally {
            c.close();
        }

        return result;
    }

    public ArrayList<HistoryEventModel> getDialogEvents(String fieldSearched, String valueSearched) {

        String sql = "select e.ID, e.Title, e.Year, e.Place, e.Leader, e.Result, e.Description, t.Name, t.ID from Events e, " +
                "Topics t where e.Topic = t.ID and " + fieldSearched + " like '%" + valueSearched + "%'ORDER BY e.Year asc";

        ArrayList<HistoryEventModel> events = new ArrayList<HistoryEventModel>();
        Cursor c = bgHistoryDB.rawQuery(sql, null);

        while (c.moveToNext()) {

            HistoryEventModel historyEvent = new HistoryEventModel(c.getInt(0), c.getString(1), c.getString(2), c.getString(3),
                    c.getString(4), c.getString(5), c.getString(6), c.getString(7), c.getInt(8));

            events.add(historyEvent);
        }
        c.close();
        return events;
    }

    public int getDatabaseVersion() {

        SQLiteDatabase checkDB = null;

        try {

            String path = DATABASE_PATH + DATABASE_NAME;

            checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);

        } catch (SQLiteException e) {
            //
        }

        int version = checkDB.getVersion();

        if (checkDB != null) {
            checkDB.close();
        }
        return version;

    }

}

