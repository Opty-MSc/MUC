package pt.ulisboa.tecnico.muc.sqliteandsharedpreferences;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import pt.ulisboa.tecnico.muc.sqliteandsharedpreferences.domain.UserCollection;
import pt.ulisboa.tecnico.muc.sqliteandsharedpreferences.sampledb.SampleContract.UsersEntry;
import pt.ulisboa.tecnico.muc.sqliteandsharedpreferences.sampledb.SampleDbHelper;

public class SQLiteActivity extends AppCompatActivity {

    private SampleDbHelper dbHelper;
    private EditText editUsernameView;
    private EditText editAgeView;
    private UserFragment userFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        this.dbHelper = new SampleDbHelper(this);
        this.editUsernameView = (EditText) findViewById(R.id.edit_username);
        this.editAgeView = (EditText) findViewById(R.id.edit_age);
        this.userFragment = (UserFragment) getSupportFragmentManager()
                .findFragmentById(R.id.user_fragment);

        SQLiteDatabase db = this.dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                UsersEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            long uid = cursor.getLong(cursor.getColumnIndex(UsersEntry._ID));
            String username = cursor.getString(cursor.getColumnIndex(UsersEntry.COLUMN_NAME_USERNAME));
            int age = cursor.getInt(cursor.getColumnIndex(UsersEntry.COLUMN_NAME_AGE));
            UserCollection.addUser(uid, username, age);
        }
        cursor.close();
    }

    public void onInsert(View view) {

        if (this.editUsernameView.getText().toString().equals("")
                || this.editAgeView.getText().toString().equals("")) {
            Toast.makeText(this, "There are Fields to Fill!", Toast.LENGTH_SHORT).show();
            return;
        }

        String username = this.editUsernameView.getText().toString();
        int age = Integer.parseInt(this.editAgeView.getText().toString());

        ContentValues contentValues = new ContentValues();
        contentValues.put(UsersEntry.COLUMN_NAME_USERNAME, username);
        contentValues.put(UsersEntry.COLUMN_NAME_AGE, age);

        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        long uid = db.insert(UsersEntry.TABLE_NAME, null, contentValues);

        if (uid < 0) {
            Toast.makeText(this, "User Insertion Failed!", Toast.LENGTH_SHORT).show();
            return;
        }

        int position = UserCollection.ITEMS.size();
        UserCollection.addUser(uid, username, age);
        this.userFragment.recyclerAdapter.notifyItemInserted(position);
        this.userFragment.recyclerView.scrollToPosition(position);

        this.editUsernameView.getText().clear();
        this.editAgeView.getText().clear();

        Toast.makeText(this, "User Inserted Successfully!", Toast.LENGTH_SHORT).show();
    }

    public void onDelete(View view) {

        int position = this.userFragment.recyclerView
                .getChildAdapterPosition((LinearLayout) view.getParent());

        String selection = String.format("%s = ?", UsersEntry._ID);
        String[] selectionArgs = {
                String.valueOf(UserCollection.ITEMS.get(position).getUid())
        };

        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        int deletedRows = db.delete(UsersEntry.TABLE_NAME, selection, selectionArgs);

        if (deletedRows < 0) {
            Toast.makeText(this, "User Deletion Failed!", Toast.LENGTH_SHORT).show();
            return;
        }

        this.userFragment.recyclerAdapter.notifyItemRemoved(position);
        UserCollection.ITEMS.remove(position);

        Toast.makeText(this, "User Deleted Successfully!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        this.dbHelper.close();
        super.onDestroy();
    }
}
