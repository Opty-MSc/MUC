package pt.ulisboa.tecnico.muc.sqliteandsharedpreferences.sampledb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import pt.ulisboa.tecnico.muc.sqliteandsharedpreferences.sampledb.SampleContract.UsersEntry;

public class SampleDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Sample.db";

    private static final String SQL_CREATE_ENTRIES =
            String.format("CREATE TABLE %s (" +
                            "%s INTEGER PRIMARY KEY, " +
                            "%s TEXT, " +
                            "%s INTEGER" +
                            ")",
                    UsersEntry.TABLE_NAME,
                    UsersEntry._ID,
                    UsersEntry.COLUMN_NAME_USERNAME,
                    UsersEntry.COLUMN_NAME_AGE
            );

    private static final String SQL_DELETE_ENTRIES =
            String.format("DROP TABLE IF EXISTS %s",
                    UsersEntry.TABLE_NAME
            );

    public SampleDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
