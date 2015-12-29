package cam.appcore.com.camerawork;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IntentFactory {

    private Context ctx;

    public IntentFactory(Context context) {
        ctx = context;
    }

    public Intent getContentFolderIntent() {

        if (isExternalStorageAvailable()) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            Uri uri = Uri.parse(Environment.DIRECTORY_PICTURES);
            intent.setDataAndType(uri, "image/*");
            return intent;
        }
        return null;
    }

    public Intent getCameraCaptureIntent() {
        return new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    }

    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}
