package cam.appcore.com.camerawork;

import com.squareup.okhttp.RequestBody;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;

/**
 * Created by jeremyw on 28/12/2015.
 */
public interface FileUploadService {

    @Multipart
    @POST("apitest/api/test/upload")
    Call<String> upload(@Part("myfile\"; filename=\"file.png\" ") RequestBody file, @Part("description") String description);

    @GET("/apitest/api/test")
    Call<List<Employee>> getProduct();
}
