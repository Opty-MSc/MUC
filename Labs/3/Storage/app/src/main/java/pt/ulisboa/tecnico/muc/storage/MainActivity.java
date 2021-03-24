package pt.ulisboa.tecnico.muc.storage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            findViewById(R.id.external_storage).setEnabled(false);
        }
    }

    public void onInternalStorage(View view) {
        startActivity(new Intent(this, InternalStorage.class));
    }

    public void onExternalStorage(View view) {
        startActivity(new Intent(this, ExternalStorage.class));
    }
}
