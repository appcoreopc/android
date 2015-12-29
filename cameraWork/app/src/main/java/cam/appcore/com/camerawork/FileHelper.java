package cam.appcore.com.camerawork;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jeremyw on 29/12/2015.
 */
public class FileHelper {

    public static File createImageFile(File directory) throws IOException {
        String timestamp = new SimpleDateFormat("yyymmdd_HHmmss").format(new Date());
        String imageFileName = "img" +
                "_" + timestamp + "_";
        //File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", directory);
        return image;
    }

    public static String createImageFile() {
        String timestamp = new SimpleDateFormat("yyymmdd_HHmmss").format(new Date());
        String randomFileName = "img" +
                "_" + timestamp + ".jpg";
        return randomFileName;
    }
}
