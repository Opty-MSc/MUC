package pt.ulisboa.tecnico.muc.sqliteandsharedpreferences.sampledb;

import android.provider.BaseColumns;

public final class SampleContract {
    private SampleContract() {
    }

    public static class UsersEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_AGE = "age";
    }
}
