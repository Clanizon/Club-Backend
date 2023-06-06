package com.booking.service;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiHelper {
    private static ApiHelper helper;

    private final String userName="oneassist.net";
    private final String password="34902283";
    private final String source="ONEATS";
    private final String dltentityid="1501573880000019506";
    private final String dltheaderid="1505160854309326523";
    private final String dlttempid="1507161743048084576";
    private final String messageurl="https://www.txtguru.in/imobile/api.php";

    private String currentDate = "2021-03-01";

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsInJvbGVzIjoiUk9MRV9BRE1JTiIsImlhdCI6MTYxODM3NTEzOSwiZXhwIjoxODE2MTgzNzUxMzl9.NHc53mzb8MrPZy9cxtbveFFdWias8sRIcDOJnRW_tZg";
    public static final MediaType JSON
            = MediaType.parse("application/json");
    private OkHttpClient client;

    private ApiHelper(){
       
        client = new OkHttpClient();
    }

    public static ApiHelper getInstance(){
        if(helper == null){
            return new ApiHelper();
        }
        return helper;
    }

    

   
    public void SendMessage( ResponseListener listener,String mobile,String message){
        try {
            HttpUrl.Builder urlBuilder = HttpUrl.parse(messageurl).newBuilder();
            urlBuilder.addQueryParameter("username",userName);
            urlBuilder.addQueryParameter("password",password);
            urlBuilder.addQueryParameter("source",source);
            urlBuilder.addQueryParameter("dltheaderid",dltheaderid);
            urlBuilder.addQueryParameter("dltentityid",dltentityid);
            urlBuilder.addQueryParameter("dlttempid",dlttempid);
            urlBuilder.addQueryParameter("dmobile",mobile);
            urlBuilder.addQueryParameter("message",message);
            final Request request = new Request.Builder()
                    .url(urlBuilder.build().toString())
                    .addHeader("Authorization",
                            "Bearer "+token)
                    .build();
            System.out.println(urlBuilder);
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    call.cancel();
                    System.out.println("Send message Failure ****");
                    listener.onResponse(null);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String myResponse = response.body().string();
                    System.out.println("Send Message Success ****");
                    System.out.println(myResponse);
                    listener.onResponse(myResponse);
                }
            });
        }
        catch (Exception e){
e.printStackTrace();        }
    }

   


}

