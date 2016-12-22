package com.example.sumit.graphtutorial;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private String TAG ="res";
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private CustomAdapter adapter;
    private List<Data> data_list;
    private ProgressDialog pd;
    PieChart mPieChart;
    int red ,blue,green;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("Loading . . . ");
        data_list  = new ArrayList<>();
        load_data_from_server();
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new CustomAdapter(this,data_list);
        recyclerView.setAdapter(adapter);
        mPieChart = (PieChart) findViewById(R.id.piechart);


        mPieChart.startAnimation();
    }

    private void load_data_from_server() {
        String url = "http://vga.ramstertech.com/demodata/readdata.php";
       pd.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONArray jsonarray = new JSONArray(response);
                            for(int i=0; i < jsonarray.length(); i++) {

                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                String id       = jsonobject.getString("id");
                                String sub       = jsonobject.getString("subject");
                                String mark =jsonobject.getString("marks");
                                int smark =Integer.parseInt(mark);
//RANDOM COLORS
                                Random rnd = new Random();
                                red=rnd.nextInt(256);
                                blue=rnd.nextInt(256);
                                green=rnd.nextInt(256);

                                Data data = new Data(id.toString(),sub.toString(),mark.toString(),red,blue,green);
                                mPieChart.addPieSlice(new PieModel(sub.toString(), smark, Color.argb(255,red,blue,green)));
                                data_list.add(data);
                                adapter.notifyDataSetChanged();
                                pd.hide();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                                pd.hide();

                        }




                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error != null){
                            Log.d(TAG, error.toString());
                            Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_LONG).show();
                        }
                    }
                }

        );

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
