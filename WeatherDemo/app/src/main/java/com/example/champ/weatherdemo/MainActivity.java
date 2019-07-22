package com.example.champ.weatherdemo;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;


import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;



public class MainActivity extends Activity {
    RequestQueue queue = null;
    TextView tv_city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        tv_city = (TextView) findViewById(R.id.id_tv_city);

        OnShowList();
    }

    protected void OnShowList(){
        String url = "https://www.acoolnet.com/data.txt";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                System.out.println(jsonObject);
                try {
                    JSONArray resultJsonArray = jsonObject.getJSONArray("activity");
                    //遍历
                    JSONObject jsonObject1 = resultJsonArray.getJSONObject(0);
                    //JSONObject jsonObject2 = jsonObject1.getJSONObject("basic");
                    String d1 = jsonObject1.getString("name");
                    tv_city.setText( d1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println(volleyError);
            }
        });
        queue.add(request);
    }



}
