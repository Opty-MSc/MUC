package pt.ulisboa.tecnico.muc.storage;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExternalStorage extends AppCompatActivity {

    private File myExternalFile;
    private EditText input;
    private TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);

        myExternalFile = new File(getExternalFilesDir("MyFileStorage"), "SampleFile.txt");

        input = findViewById(R.id.input);
        output = findViewById(R.id.output);
    }

    public void onWrite(View view) {
        new Thread(() -> {
            try (FileOutputStream fos = new FileOutputStream(myExternalFile)) {
                fos.write(input.getText().toString().getBytes());
            } catch (IOException e) {
                runOnUiThread(() -> Toast.makeText(this, "Write Failed!", Toast.LENGTH_LONG).show());
                return;
            }
            runOnUiThread(() -> Toast.makeText(this, "Write Successful!", Toast.LENGTH_LONG).show());
            input.post(() -> input.setText(""));
        }).start();
    }

    public void onRead(View view) {
        new Thread(() -> {
            StringBuilder sb = new StringBuilder();
            try (DataInputStream dis = new DataInputStream(new FileInputStream(myExternalFile))) {
                BufferedReader br = new BufferedReader(new InputStreamReader(dis));
                String lineSeparator = System.getProperty("line.separator");
                String line;
                while ((line = br.readLine()) != null) sb.append(line).append(lineSeparator);
            } catch (IOException e) {
                runOnUiThread(() -> Toast.makeText(this, "Read Failed!", Toast.LENGTH_LONG).show());
                return;
            }
            runOnUiThread(() -> Toast.makeText(this, "Read Successful!", Toast.LENGTH_LONG).show());
            output.post(() -> output.setText(sb.toString()));
        }).start();
    }
}
