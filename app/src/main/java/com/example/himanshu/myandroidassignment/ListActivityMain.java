package com.example.himanshu.myandroidassignment;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        setTitle("My List");

        String[] items = { "First", "Second"};

        //ListView list = (ListView) findViewById(R.id.@android_id/list)

        Adapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        getListView().setAdapter((ListAdapter) adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Toast.makeText(getBaseContext(),"clicked", Toast.LENGTH_LONG);
    }
}
