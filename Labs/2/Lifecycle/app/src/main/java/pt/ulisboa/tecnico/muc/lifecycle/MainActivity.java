package pt.ulisboa.tecnico.muc.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    protected static final String COUNTER_EXTRA = "pt.ulisboa.tecnico.muc.lifecycle.COUNTER";
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MainActivity", "onCreate");
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity", "onDestroy");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity", "onRestart");
    }

    public void startService(View view) {
        Intent intent = new Intent(this, LifecycleService.class);
        intent.putExtra(COUNTER_EXTRA, ++this.counter);
        startService(intent);
    }

    public void stopService(View view) {
        Intent intent = new Intent(this, LifecycleService.class);
        stopService(intent);
    }

    public void finishButtonPressed(View view) {
        finish();
    }
}
