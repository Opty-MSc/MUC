package pt.ulisboa.tecnico.muc.imagedownload;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public static final String IMAGE_MSG = "IMAGE";
    private static final CompressFormat IMAGE_FORMAT = CompressFormat.PNG;
    private static final String IMAGE_URL = "https://RicardoGrade.github.io/Portfolio.png";

    private final Handler imageHandler = new Handler(this::handleImage);
    private ImageDownloadApp appContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.appContext = (ImageDownloadApp) this.getApplicationContext();
    }

    @Override
    protected void onResume() {
        super.onResume();
        appContext.attachMainActivity(this);
    }

    @Override
    protected void onPause() {
        this.appContext.detachMainActivity(this);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        this.appContext.detachMainActivity(this);
        super.onDestroy();
    }

    /*******************
     | Click Listeners |
     *******************/

    public void onDownloadImageThread(View view) {
        new Thread(() -> this.sendImage(downloadImage(IMAGE_URL))).start();
        ((TextView) findViewById(R.id.download_status)).setText(getString(R.string.download_started));
    }

    public void onDownloadImageAsyncTask(View view) {
        new DownloadImageAsyncTask(this).execute(IMAGE_URL);
        ((TextView) findViewById(R.id.download_status)).setText(getString(R.string.download_started));
    }

    /******************
     | Download Image |
     ******************/

    public Bitmap downloadImage(String urlStr) {
        try {
            return BitmapFactory.decodeStream(new URL(urlStr).openStream());
        } catch (Exception e) {
            return null;
        }
    }

    /*************
     | Messaging |
     *************/

    public void sendImage(Bitmap image) {
        Message message = new Message();
        Bundle bundle = new Bundle();
        if (image != null) {
            ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
            image.compress(IMAGE_FORMAT, 100, imageStream);
            bundle.putByteArray(IMAGE_MSG, imageStream.toByteArray());
        }
        message.setData(bundle);
        imageHandler.sendMessage(message);
    }

    public boolean handleImage(Message message) {
        byte[] imageBytes = message.getData().getByteArray(IMAGE_MSG);
        if (imageBytes != null) {
            ((TextView) findViewById(R.id.download_status)).setText(getString(R.string.download_success));
            Bitmap image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            ((ImageView) findViewById(R.id.downloaded_image)).setImageBitmap(image);
        } else {
            ((TextView) findViewById(R.id.download_status)).setText(getString(R.string.download_failed));
        }
        return true;
    }
}
