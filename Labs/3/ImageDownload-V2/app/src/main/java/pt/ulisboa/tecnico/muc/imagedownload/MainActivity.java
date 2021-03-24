package pt.ulisboa.tecnico.muc.imagedownload;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String IMAGE_URL = "https://RicardoGrade.github.io/Portfolio.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*******************
     | Click Listeners |
     *******************/

    public void onDownloadImageThread(View view) {
        TextView downloadStatus = findViewById(R.id.download_status);
        downloadStatus.setText(getString(R.string.download_started));

        new Thread(() -> this.runDownloadImage(IMAGE_URL)).start();
    }

    public void onDownloadImageAsyncTask(View view) {
        TextView downloadStatus = findViewById(R.id.download_status);
        downloadStatus.setText(getString(R.string.download_started));

        ImageView downloadedImage = findViewById(R.id.downloaded_image);
        new DownloadImageAsyncTask(
                downloadStatus,
                downloadedImage,
                getString(R.string.download_started),
                getString(R.string.download_success),
                getString(R.string.download_failed)
        ).execute(IMAGE_URL);
    }

    /******************
     | Download Image |
     ******************/

    public void runDownloadImage(String urlStr) {
        Bitmap bmp;
        try {
            bmp = BitmapFactory.decodeStream(new URL(urlStr).openStream());
        } catch (Exception e) {
            bmp = null;
        }
        TextView downloadStatus = findViewById(R.id.download_status);
        if (bmp != null) {
            ImageView downloadedImage = findViewById(R.id.downloaded_image);
            final Bitmap finalBmp = bmp;
            runOnUiThread(() -> {
                downloadedImage.setImageBitmap(finalBmp);
                downloadStatus.setText(getString(R.string.download_success));
            });
        } else {
            downloadStatus.post(() -> downloadStatus.setText(getString(R.string.download_failed)));
        }
    }
}
