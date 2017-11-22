package com.example.himanshu.myandroidassignment;

import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class ListActivityMain extends ListActivity {

    MediaPlayer player = null;
    ArrayAdapter adapter = null;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);

        setTitle("Please Choose Option");

        String[] items = { "Play Audio", "Call Himanshu", "Show Notification", "Current Location", "Check Network", "About"};
        player = new MediaPlayer();
        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, items) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                text = (TextView) view.findViewById(android.R.id.text1);
                return view;
            }
        };

        getListView().setAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String itemSelected = getListView().getItemAtPosition(position).toString();
        TextView textview = (TextView) v.findViewById(android.R.id.text1);
        switch (itemSelected) {
            case "Play Audio":
                if(player.isPlaying()) {
                    textview.setText("Play Audio");
                    player.stop();
                    player.reset();
                } else {
                    textview.setText("Stop Audio");
                    //Log.d("MyApp", "Play Audio clicked");
                    AssetFileDescriptor afd = null;
                    try {
                        afd = getAssets().openFd("SampleAudio.mp3");

                        player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                        player.prepare();
                        player.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "Call Himanshu":
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+919886462008"));
                startActivity(callIntent);
                break;
            case "Play Video":
                Log.d("MyApp", "Play Audio clicked");
                break;
            case "Show Notification":
                ShowNotification();
                break;
            case "Check Network":
                if (IsNetworkAvailable()) {
                    Toast.makeText(getApplicationContext(), "Internet available", Toast.LENGTH_LONG)
                            .show();
                } else {
                    Toast.makeText(getApplicationContext(), "Internet not available", Toast.LENGTH_LONG)
                    .show();
                }
                break;
        }

    }

    private Boolean IsNetworkAvailable()
    {
        Boolean returnValue = false;
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        returnValue = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        return returnValue;
    }

    private void ShowNotification()
    {
        // prepare intent which is triggered if the
        // notification is selected

        //Intent intent = new Intent(this, NotificationReceiver.class);
        // use System.currentTimeMillis() to have a unique ID for the pending intent
        //PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        // build notification
        // the addAction re-use the same intent to keep the example short
        Notification n  = new Notification.Builder(this)
                .setContentTitle("Message from Himanshu")
                .setContentText("Thank you Sir. Enjoyed Your Class!")
                .setSmallIcon(R.drawable.cloud)
                //.setContentIntent(pIntent)
                .setAutoCancel(true)
                //.addAction(R.drawable.icon, "Call", pIntent)
                //.addAction(R.drawable.icon, "More", pIntent)
                //.addAction(R.drawable.icon, "And more", pIntent)
                .getNotification();


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        try {
            notificationManager.notify(0, n);
        }catch (Exception ex) {
            Log.d("MyApp", "Exception Occured!");
        }
    }
}
