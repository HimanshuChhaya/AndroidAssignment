package com.example.himanshu.myandroidassignment;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListActivityMain extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);

        setTitle("Please Choose Option");

        String[] items = { "Play Audio", "Play Video", "Current Location"};

        Adapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        getListView().setAdapter((ListAdapter) adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String itemSelected = getListView().getItemAtPosition(position).toString();
        switch (itemSelected)
        {
            case "Play Audio":
                Log.d("MyApp", "Play Audio clicked");
                break;
            case "Play Video":
                Log.d("MyApp", "Play Audio clicked");
                break;
        }
    }
}
