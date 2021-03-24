package pt.ulisboa.tecnico.muc.imagedownload;

import android.app.Application;

public class ImageDownloadApp extends Application {

    private MainActivity mainActivity = null;

    public MainActivity getMainActivity() {
        return this.mainActivity;
    }

    public void attachMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void detachMainActivity(MainActivity mainActivity) {
        if (this.mainActivity.equals(mainActivity)) {
            this.mainActivity = null;
        }
    }
}
