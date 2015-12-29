package cam.appcore.com.camerawork;

import android.graphics.Bitmap;

public class CameraResult {

    private Bitmap bitmap;
    private String path;

    public CameraResult(Bitmap bmp, String path)
    {
        this.bitmap = bmp;
        this.path = path;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
