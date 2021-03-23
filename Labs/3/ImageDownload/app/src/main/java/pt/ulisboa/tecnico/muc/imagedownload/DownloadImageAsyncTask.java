package pt.ulisboa.tecnico.muc.imagedownload;

import android.graphics.Bitmap;
import android.os.AsyncTask;

public class DownloadImageAsyncTask extends AsyncTask<String, Integer, Bitmap> {

    private final ImageDownloadApp appContext;

    public DownloadImageAsyncTask(MainActivity context) {
        this.appContext = (ImageDownloadApp) context.getApplicationContext();
    }

    @Override
    protected Bitmap doInBackground(String... urlStrs) {
        if (this.appContext.getMainActivity() != null) {
            return this.appContext.getMainActivity().downloadImage(urlStrs[0]);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap image) {
        if (this.appContext.getMainActivity() != null) {
            this.appContext.getMainActivity().sendImage(image);
        }
    }
}
