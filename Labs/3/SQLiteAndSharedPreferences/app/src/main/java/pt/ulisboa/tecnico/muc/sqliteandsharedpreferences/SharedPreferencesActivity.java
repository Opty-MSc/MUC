package pt.ulisboa.tecnico.muc.sqliteandsharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SharedPreferencesActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        this.sharedPreferences = getSharedPreferences(
                getString(R.string.app_preferences), Context.MODE_PRIVATE);
    }

    public void setDefaultNightMode(int nightMode) {
        this.sharedPreferences.edit()
                .putInt(getString(R.string.app_preferences_theme), nightMode)
                .apply();
        AppCompatDelegate.setDefaultNightMode(nightMode);
    }

    public void onLightMode(View view) {
        this.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    public void onNightMode(View view) {
        this.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }
}
