package pt.ulisboa.tecnico.muc.imagedownload;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;

public class DownloadImageAsyncTask extends AsyncTask<String, Integer, Bitmap> {

    @SuppressLint("StaticFieldLeak")
    private final TextView downloadStatus;
    @SuppressLint("StaticFieldLeak")
    private final ImageView downloadedImage;

    private final String downloadStarted;
    private final String downloadSuccess;
    private final String downloadFailed;

    public DownloadImageAsyncTask(
            TextView downloadStatus,
            ImageView downloadedImage,
            String downloadStarted,
            String downloadSuccess,
            String downloadFailed) {
        this.downloadStatus = downloadStatus;
        this.downloadedImage = downloadedImage;
        this.downloadStarted = downloadStarted;
        this.downloadSuccess = downloadSuccess;
        this.downloadFailed = downloadFailed;
    }

    @Override
    protected void onPreExecute() {
        downloadStatus.setText(downloadStarted);
    }

    @Override
    protected Bitmap doInBackground(String... urlStrs) {
        try {
            return BitmapFactory.decodeStream(new URL(urlStrs[0]).openStream());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap bmp) {
        if (bmp != null) {
            downloadStatus.setText(downloadSuccess);
            downloadedImage.setImageBitmap(bmp);
        } else {
            downloadStatus.setText(downloadFailed);
        }
    }
}
