package com.yinyxn.myjobjson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 本人只是使用Log打印出解析后的数据，需要的人可以在实体类中加入get set方法获取想要的数据再通过控件显示出来
 * I just use the Log to print out the parsed data，Need to join in the entity class get and set method to obtain want data displayed by the control again
 */
public class MainActivity extends AppCompatActivity {

    String url = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Volley获取网络请求，Gson进行数据解析；
        VGson();

        // 解析本地Json数据
        new JsonTask().execute();

        // 通过Gson实现Json与对象互转
        GJO();

    }

    private void GJO() {

        // 2种把数据转换成Json格式方式

        String text = String.format("{'name':'tom','aeg':23}");
        Log.d("把数据转换成Json格式方式1",text);

        JSONObject object = new JSONObject();
        try {
            object.put("name", "tom");
            object.put("age", 23);
            object.put("single", true);

            String json = object.toString();
            Log.d("把数据转换成Json格式方式2", json);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        People p = new People();
        p.firstName = "jack";
        p.lastName = "lee";
        p.age = 23;
        p.sex = "男";
        p.address.city = "cs";

        People.Phone phone = new People.Phone();
        phone.type = "work";
        phone.number = "164";

        p.phoneNumber.add(phone);

        Gson gson = new Gson();
        String data = gson.toJson(p);
        Log.d("把数据转换成Json格式方式3", data);




        //由 集合类型的实体类 转换成 Json类型
        Gson mgson = new Gson();
        List<Person> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Person person = new Person("小飞飞"+i,"男",23,"上海");
            list.add(person);
        }
        String json = mgson.toJson(list);
        Log.d("集合类型的实体类转成Json", json);


        /**
         * 转换一个集合 的实体Bean
         * 注意前面加上[]方括号，代表一个数组
         * **/


        String jBList = "[{\"name\":\"小灰灰\",\"sex\":\"狼\",\"age\":23,\"address\":上海},{\"name\":\"小灰灰\",\"sex\":\"狼\",\"age\":23,\"address\":上海}]";
        List<Person> lists = mgson.fromJson(jBList, new TypeToken<List<Person>>() {
        }.getType());
        for (Person person : lists) {

            Log.d("转对象:", person.toString());
        }
    }

    private void VGson() {
        url = "http://114.80.231.178:18080/openDataTest/weatherAction/getWeatherInfo";
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        StringRequest mStringRequest = new StringRequest(
                StringRequest.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {// 这里获得字符串不会自动转义

                        // 去""；
                        s = s.substring(1, s.length() - 1);// 截取""中的内容
                        String st = s.replace("\\", "");// 用空替换\

                        Log.d("网络获取的数据", st);

                        // 自动转义
                        String test = "{\"error\":0,\"status\":\"success\",\"date\":\"2016-04-08\",\"results\":[{\"currentCity\":\"上海\",\"pm25\":\"83\",\"index\":[{\"title\":\"穿衣\",\"zs\":\"较舒适\",\"tipt\":\"穿衣指数\",\"des\":\"建议着薄外套、开衫牛仔衫裤等服装。年老体弱者应适当添加衣物，宜着夹克衫、薄毛衣等。\"},{\"title\":\"洗车\",\"zs\":\"较适宜\",\"tipt\":\"洗车指数\",\"des\":\"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。\"},{\"title\":\"旅游\",\"zs\":\"适宜\",\"tipt\":\"旅游指数\",\"des\":\"天气较好，但丝毫不会影响您出行的心情。温度适宜又有微风相伴，适宜旅游。\"},{\"title\":\"感冒\",\"zs\":\"易发\",\"tipt\":\"感冒指数\",\"des\":\"天冷空气湿度大，易发生感冒，请注意适当增加衣服，加强自我防护避免感冒。\"},{\"title\":\"运动\",\"zs\":\"较适宜\",\"tipt\":\"运动指数\",\"des\":\"天气较好，但考虑气温较低，推荐您进行室内运动，若户外运动请适当增减衣物并注意防晒。\"},{\"title\":\"紫外线强度\",\"zs\":\"弱\",\"tipt\":\"紫外线强度指数\",\"des\":\"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。\"}],\"weather_data\":[{\"date\":\"周五 04月08日 (实时：18℃)\",\"dayPictureUrl\":\"http://api.map.baidu.com/images/weather/day/duoyun.png\",\"nightPictureUrl\":\"http://api.map.baidu.com/images/weather/night/duoyun.png\",\"weather\":\"多云\",\"wind\":\"东风微风\",\"temperature\":\"20 ~ 14℃\"},{\"date\":\"周六\",\"dayPictureUrl\":\"http://api.map.baidu.com/images/weather/day/duoyun.png\",\"nightPictureUrl\":\"http://api.map.baidu.com/images/weather/night/yin.png\",\"weather\":\"多云转阴\",\"wind\":\"东南风微风\",\"temperature\":\"21 ~ 14℃\"},{\"date\":\"周日\",\"dayPictureUrl\":\"http://api.map.baidu.com/images/weather/day/xiaoyu.png\",\"nightPictureUrl\":\"http://api.map.baidu.com/images/weather/night/yin.png\",\"weather\":\"小雨转阴\",\"wind\":\"东北风微风\",\"temperature\":\"18 ~ 13℃\"},{\"date\":\"周一\",\"dayPictureUrl\":\"http://api.map.baidu.com/images/weather/day/yin.png\",\"nightPictureUrl\":\"http://api.map.baidu.com/images/weather/night/xiaoyu.png\",\"weather\":\"阴转小雨\",\"wind\":\"东风3-4级\",\"temperature\":\"17 ~ 13℃\"}]}]}";
                        Log.d("测试", test);

                        Gson gson = new Gson();
                        Test p = gson.fromJson(st, Test.class);
                        Log.d("对象", p.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.d("debug", volleyError.getMessage());
                    }
                }
        );
        mRequestQueue.add(mStringRequest);
        mRequestQueue.start();
    }

    class JsonTask extends AsyncTask<Void, Void, People> {

        private static final String TAG = "JsonTask";

        @Override
        protected People doInBackground(Void... params) {

            People people = new People();

            // Json(数据) 解析
            InputStream in = getResources().openRawResource(R.raw.data);

            BufferedReader reader = null;
            StringBuilder builder = null;//StringBuffer 功能差不多

            //字节流包装为字符流
            try {
                reader = new BufferedReader(new InputStreamReader(in, "utf-8"));

                builder = new StringBuilder();
                char[] buf = new char[1024];
                int size;
                while (-1 != (size = reader.read(buf))) {
                    builder.append(buf, 0, size);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            String data = builder.toString();
            Log.d(TAG, data);

            try {
                JSONObject json = new JSONObject(data);
                Log.d("ttt", json.toString());
                people.firstName = json.getString("firstName");
                people.lastName = json.getString("lastName");
                people.sex = json.getString("sex");
                people.age = json.getInt("age");

                JSONObject jsonAddress = json.getJSONObject("address");
                people.address.streetAddress = jsonAddress.getString("streetAddress");
                people.address.city = jsonAddress.getString("city");
                people.address.state = jsonAddress.getString("state");
                people.address.postalCode = jsonAddress.getString("postalCode");


                JSONArray jsonPhones = json.getJSONArray("phoneNumber");
                People.Phone phone = new People.Phone();// 因为是静态的所以才能这样创建
//                Person.Phone phone = person.new Phone();//Phone()去掉static

                for (int i = 0; i < jsonPhones.length(); i++) {
                    JSONObject jPhone = jsonPhones.getJSONObject(i);
                    phone.type = jPhone.getString("type");
                    phone.number = jPhone.getString("number");

                    people.phoneNumber.add(phone);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return people;
        }

        @Override
        protected void onPostExecute(People people) {
            super.onPostExecute(people);
            Log.d("JsonTask",people.toString());
        }
    }
}