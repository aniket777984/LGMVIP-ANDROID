package com.example.covid_track_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class DataFragment extends Fragment {

    private RecyclerView rv_view;
    ArrayList<Model> modelClassArrayList;
    Model model;
    Adapter adapter;


    public DataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_view = view.findViewById(R.id.rv_view);
        modelClassArrayList = new ArrayList<>();
        rv_view.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new Adapter(getContext(),modelClassArrayList);
        rv_view.setAdapter(adapter);


        fetchData();
    }

    private void fetchData() {

        String url = "https://data.covid19india.org/state_district_wise.json";

        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONObject object = new JSONObject(response);
                    Iterator<String> keys=object.keys();

                    keys.next();

                    while(keys.hasNext()){
                        String key=keys.next();
                        JSONObject obj1 = object.getJSONObject(key);
                        JSONObject obj2 = obj1.getJSONObject("districtData");
                        Iterator<String> subkeys=obj2.keys();

                        while(subkeys.hasNext()){
                            String subkey=subkeys.next();
                            JSONObject obj3 = obj2.getJSONObject(subkey);
                            JSONObject obj4 = obj3.getJSONObject("delta");

                            String active = obj3.getString("active");
                            String confirmed = obj3.getString("confirmed");
                            String migratedother = obj3.getString("migratedother");
                            String deceased = obj3.getString("deceased");
                            String recovered = obj3.getString("recovered");

                            String dconfirmed = obj4.getString("confirmed");
                            String ddeceased = obj4.getString("deceased");
                            String drecovered = obj4.getString("recovered");

                            model = new Model(subkey+" | "+key, active, confirmed, migratedother,deceased,recovered,
                                    dconfirmed,ddeceased,drecovered);
                            modelClassArrayList.add(model);
                        }
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Error on Response", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // In case of error it will run
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue reqQueue = Volley.newRequestQueue(getContext());
        reqQueue.add(req);
    }


}