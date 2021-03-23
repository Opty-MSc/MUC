package pt.ulisboa.tecnico.muc.basicthreading;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Thread incrementCounterThread = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartCounter(View view) {
        if (this.incrementCounterThread == null) {
            this.incrementCounterThread = new Thread(this::incrementCounter);
            this.incrementCounterThread.start();
        }
    }

    public void onStopCounter(View view) {
        if (this.incrementCounterThread != null) {
            this.incrementCounterThread.interrupt();
            this.incrementCounterThread = null;
        }
    }

    private void incrementCounter() {
        try {
            for (long counter = 0; ; counter++) {
                Log.d("MainActivity", "Counter: " + ++counter);
                Thread.sleep(1000);
            }
        } catch (InterruptedException ignored) {
        }
    }
}
