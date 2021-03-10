package pt.ulisboa.tecnico.muc.itemlist;

import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnKeyListener {

    private ArrayAdapter<String> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.itemsAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_checked, new ArrayList<>()
        );
        ((ListView) findViewById(R.id.list_items)).setAdapter(itemsAdapter);
        findViewById(R.id.input).setOnKeyListener(this);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() != KeyEvent.ACTION_DOWN) {
            Editable input = ((EditText) findViewById(R.id.input)).getText();
            if (!input.toString().equals("")) {
                this.itemsAdapter.add(input.toString());
                input.clear();
            }
        }
        return false;
    }
}
