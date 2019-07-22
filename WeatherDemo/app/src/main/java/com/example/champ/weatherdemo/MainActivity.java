package com.example.champ.weatherdemo;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

//import com.google.gson.Gson;

public class MainActivity extends Activity {
    RequestQueue queue = null;
    EditText et_city;
    TextView tv_city, tv_date, tv_temp, tv_cond, tv_qlty, tv_dir;
    Editable city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        et_city = (EditText) findViewById(R.id.et_city);
        tv_city = (TextView) findViewById(R.id.id_tv_city);
        tv_date = (TextView) findViewById(R.id.id_tv_date);
        tv_temp = (TextView) findViewById(R.id.id_tv_temp);
    }


    public void weatherClick(View view) {
        //String url = " https://free-api.heweather.com/s6/weather/now?location=" + et_city.getText().toString() + "&key=8dbd55a3e97041a098742d61c8448f4a";
        String url = "https://www.acoolnet.com/data.txt";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                System.out.println(jsonObject);
                try {
                    /*JSONArray ja = jsonObject.getJSONArray("HeWeather6");
                    JSONObject jsonObject1 = ja.getJSONObject(0);
                    JSONObject jsonObject2 = jsonObject1.getJSONObject("basic");
                    String d1 = jsonObject2.getString("location");
                    String d2 = jsonObject2.getString("parent_city");
                    String d3 = jsonObject2.getString("admin_area");
                    tv_city.setText(d3 + " " + d2 + " " + d1);

                    JSONObject jsonObject3 = jsonObject1.getJSONObject("now");
                    String wd = jsonObject3.getString("tmp");
                    String tq = jsonObject3.getString("cond_txt");
                    String fx = jsonObject3.getString("wind_dir");//风向
                    String fl = jsonObject3.getString("wind_sc");//风力
                    tv_temp.setText(" " + wd + "度 " + tq + " " + fx + " 风力" + fl + "级");

                    JSONObject jsonObject4 = jsonObject1.getJSONObject("update");
                    String loc = jsonObject4.getString("loc");
                    tv_date.setText("更新时间：" + loc);*/

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
