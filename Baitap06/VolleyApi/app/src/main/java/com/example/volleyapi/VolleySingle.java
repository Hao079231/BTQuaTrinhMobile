package com.example.volleyapi;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingle {
    private static VolleySingle instance;
    private RequestQueue requestQueue;
    private Context context;

    public VolleySingle(Context context ) {
        this.context = context;
        this.requestQueue = getRequestQueue();

    }
    public static synchronized VolleySingle getInstance(Context ct){
        if(instance == null){
            instance = new VolleySingle(ct);
        }
        return instance;
    }
    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        }
        return requestQueue;
    }
    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }
}
