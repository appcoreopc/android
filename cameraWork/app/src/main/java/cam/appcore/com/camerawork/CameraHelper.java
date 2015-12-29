package cam.appcore.com.camerawork;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class CameraHelper {

    public static Intent getCameraCaptureIntent() {
        return new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    }

    public static Bitmap handleActivityResultEvent(Intent intent) {
        Bundle bundle = intent.getExtras();
        Bitmap bitmap = (Bitmap) bundle.get("data");
        return bitmap;
    }

    public static CameraResult handleActivityResultSaveImage(Intent intent, File targetDestination) {
        String path = null;
        Bundle bundle = intent.getExtras();

        Bitmap bitmap = (Bitmap) bundle.get("data");
        try {

            path = targetDestination.getPath() + "/" + FileHelper.createImageFile();
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new CameraResult(bitmap, path);
    }
}
