package com.example.uploadimages;

import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ServiceAPI {  // Chuyển từ class thành interface
    public static final String BASE_URL = "http://app.iotstar.vn:8081/appfoods/";

    @Multipart
    @POST("upload.php")
    Call<List<ImageUpload>> upload(@Part(Const.MY_USERNAME) RequestBody username,
                                   @Part MultipartBody.Part avatar);

    @Multipart
    @POST("upload1.php")
    Call<Message> upload1(@Part(Const.MY_USERNAME) RequestBody username,
                          @Part MultipartBody.Part avatar);

    public static ServiceAPI create() {
        // Tạo interceptor để log các yêu cầu và phản hồi HTTP
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);  // Log body request và response

        // Tạo OkHttpClient và thêm interceptor vào
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        // Tạo Retrofit instance với OkHttpClient đã cấu hình
        Gson gson = new GsonBuilder().setDateFormat("yyyy MM dd HH:mm:ss").create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)  // Đặt OkHttpClient với interceptor vào Retrofit
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(ServiceAPI.class);  // Tạo instance của ServiceAPI
    }
}
