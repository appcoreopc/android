package cam.appcore.com.camerawork;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import java.io.File;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    private static final int REQUEST_TAKE_PHOTO = 1;
    private ImageView imageView;
    private CameraResult cameraResult = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button);
        Button uploadBtn = (Button) findViewById(R.id.uploadBtn);
        imageView = (ImageView) findViewById(R.id.imageView);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
                        + "/myFolder/");
                intent.setDataAndType(uri, "image/*");
                startActivityForResult(Intent.createChooser(intent, "Open folder"), 1); */

                Intent intent = CameraHelper.getCameraCaptureIntent();
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_TAKE_PHOTO);
                }
            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RetroFitService retroFitService = new RetroFitService("http://192.168.229.1/apitest");
                FileUploadService svc = retroFitService.createService(FileUploadService.class);

                String txt = "Demo program for a developer";
                //File uploadFile = new File(uriImage.getPath());
                if (cameraResult != null) {
                    File uploadFile = new File(cameraResult.getPath());
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), uploadFile);
                    Call<String> results = svc.upload(requestBody, txt);

                    results.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Response<String> response, Retrofit retrofit) {
                            Log.v("success", "file upload successful");
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            Log.v("fail", "file upload fail");
                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "ops... you cancelled!", Toast.LENGTH_SHORT).show();
        } else if (resultCode == RESULT_OK) {
            cameraResult = CameraHelper.handleActivityResultSaveImage(data, getFilesDir());
            imageView.setImageBitmap(cameraResult.getBitmap());
        }
    }
}
