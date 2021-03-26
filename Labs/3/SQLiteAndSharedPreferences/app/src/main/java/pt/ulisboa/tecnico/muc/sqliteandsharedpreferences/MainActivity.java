package pt.ulisboa.tecnico.muc.sqliteandsharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(getSharedPreferences(
                getString(R.string.app_preferences), Context.MODE_PRIVATE).getInt(
                getString(R.string.app_preferences_theme), AppCompatDelegate.getDefaultNightMode()));
    }

    public void onSQLite(View view) {
        startActivity(new Intent(this, SQLiteActivity.class));
    }

    public void onSharedPreferences(View view) {
        startActivity(new Intent(this, SharedPreferencesActivity.class));
    }
}
