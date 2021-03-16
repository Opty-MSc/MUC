package pt.ulisboa.tecnico.muc.phonecalllogger;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.stream.Stream;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {

    private static final int PHONE_STATUS_REQUEST_CODE = 1;
    private static final Stream<String> requiredPhonePermissions = Stream.of(
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_CALL_LOG
    );
    protected static ArrayAdapter<String> phoneCallsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneCallsAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, new ArrayList<>()
        );
        ((ListView) findViewById(R.id.phone_calls_list)).setAdapter(phoneCallsAdapter);
    }

    protected void onStart() {
        super.onStart();
        this.requestPhonePermissions();
    }

    private void requestPhonePermissions() {
        String[] phonePermissions = requiredPhonePermissions
                .filter((phonePermission) ->
                        ContextCompat.checkSelfPermission(this, phonePermission)
                                != PackageManager.PERMISSION_GRANTED
                ).toArray(String[]::new);
        if (phonePermissions.length > 0) {
            ActivityCompat.requestPermissions(this, phonePermissions, PHONE_STATUS_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PHONE_STATUS_REQUEST_CODE) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.i(this.getClass().getSimpleName(), "Permission [" + permissions[i] + "] Granted!");
                } else {
                    Log.i(this.getClass().getSimpleName(), "Permission [" + permissions[i] + "] Denied!");
                }
            }
        }
    }
}
