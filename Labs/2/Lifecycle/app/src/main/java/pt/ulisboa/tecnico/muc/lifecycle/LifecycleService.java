package pt.ulisboa.tecnico.muc.lifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class LifecycleService extends Service {
    private int counter = -1;

    public LifecycleService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Service Created!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.counter = intent.getIntExtra(MainActivity.COUNTER_EXTRA, -1);
        Toast.makeText(this, "Service Started [" + this.counter + "]", Toast.LENGTH_SHORT).show();
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Destroyed [" + this.counter + "]", Toast.LENGTH_SHORT).show();
    }
}
