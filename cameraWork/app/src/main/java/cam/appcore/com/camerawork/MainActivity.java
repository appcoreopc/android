package cam.appcore.com.camerawork;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
                        + "/myFolder/");
                intent.setDataAndType(uri, "image/*");
                //startActivity(Intent.createChooser(intent, "Open folder"));
                startActivityForResult(Intent.createChooser(intent, "Open folder"), 1);
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
            Uri uriImage = data.getData();

            RetroFitService retroFitService = new RetroFitService("http://192.168.229.1/apitest");
            FileUploadService svc = retroFitService.createService(FileUploadService.class);

            /*
            Call<List<Employee>> p = svc.getProduct();
            try {
                Response<List<Employee>> result = p.execute();
                if (result != null){
                    List<Employee> list = result.body();
                    for (Employee emp : list) {
                        System.out.println("Employee" + emp.getName() + " email:" + emp.getEmail());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            */

            String txt = "Demo program for a developer";
            //File uploadFile = new File(uriImage.getPath());
            File uploadFile = new File("/data/local/1.jpg");
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), uploadFile);
            Call<String> results = svc.upload(requestBody, txt);

            results.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Response<String> response, Retrofit retrofit) {
                    Log.v("success", "success");
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.v("fail", "fail");
                }
            });

            //try {
            //Bitmap bitmap =  MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgSelected);
            // new FileUploader("www.local:com").upload(bitmap);
            //} catch (IOException e) {
            //    e.printStackTrace();
            // }
            // Setting the image to selected image//
            //imageView.setImageBitmap(MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedimg));
        }
    }
}
