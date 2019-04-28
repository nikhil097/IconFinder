package com.app.nikhil.iconfinderapp.Rest;


import com.app.nikhil.iconfinderapp.ResponseBody.IconResponseBody;
import com.app.nikhil.iconfinderapp.ResponseBody.IconSetResponseBody;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    public static final String BASE_URL="https://api.iconfinder.com/";

    IconFinderApi iconFinderApi;

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }

    public ApiService()
    {
        iconFinderApi=ApiService.getClient().create(IconFinderApi.class);
    }


    public void getIconsets(String apiClientId,String apiClientSecret, String count,String afterIconsetId,final ResponseCallback<IconSetResponseBody> callback)
    {
        Call<IconSetResponseBody> call=iconFinderApi.getIconsets(apiClientId,apiClientSecret,count,afterIconsetId);

        call.enqueue(new Callback<IconSetResponseBody>() {

            @Override
            public void onResponse(Call<IconSetResponseBody> call, Response<IconSetResponseBody> response) {
                if(response.isSuccessful())
                {
                    callback.success(response.body());
                }
                else{
                    IconSetResponseBody iconSetResponseBody=new IconSetResponseBody();
                    iconSetResponseBody.setCode(response.code());
                    iconSetResponseBody.setMessage(response.message());
                    callback.failure(iconSetResponseBody);
                }

            }

            @Override
            public void onFailure(Call<IconSetResponseBody> call, Throwable t) {

                IconSetResponseBody iconSetResponseBody=new IconSetResponseBody();
                iconSetResponseBody.setMessage("Network Error");
                callback.failure(iconSetResponseBody);
            }
        });
    }

    public void getIcons(String iconsetIdentifer, String apiClientId, String apiClientSearch, final ResponseCallback<IconResponseBody> callback)
    {
        Call<IconResponseBody> call=iconFinderApi.getIcons(iconsetIdentifer,apiClientId,apiClientSearch);

        call.enqueue(new Callback<IconResponseBody>() {
            @Override
            public void onResponse(Call<IconResponseBody> call, Response<IconResponseBody> response) {
                if(response.isSuccessful())
                {
                    callback.success(response.body());
                }
                else{
                        IconResponseBody iconResponseBody=new IconResponseBody();
                        iconResponseBody.setCode(response.code());
                        iconResponseBody.setMessage(response.message());
                        callback.failure(iconResponseBody);
                    }
            }

            @Override
            public void onFailure(Call<IconResponseBody> call, Throwable t) {

                IconResponseBody iconResponseBody=new IconResponseBody();
                iconResponseBody.setMessage("Network Error");
                callback.failure(iconResponseBody);
            }
        });

    }

}
