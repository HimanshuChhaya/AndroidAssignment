package com.example.himanshu.myandroidassignment;

import android.app.ListActivity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
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

        String[] items = { "Play Audio", "Call Himanshu", "Show Notification", "Current Location", "About"};
        player = new MediaPlayer();
//        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
//        getListView().setAdapter((ListAdapter) adapter);

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

                break;
        }

    }

    private void CreateNotificationChannel()
    {
        /*NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // The id of the channel.
        String id = "my_channel_01";
        // The user-visible name of the channel.
        CharSequence name = getString(R.string.channel_name);
        // The user-visible description of the channel.
        String description = getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = new NotificationChannel(id, name, importance);
        // Configure the notification channel.
        mChannel.setDescription(description);
        mChannel.enableLights(true);
        // Sets the notification light color for notifications posted to this
        // channel, if the device supports this feature.
        mChannel.setLightColor(Color.RED);
        mChannel.enableVibration(true);
        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        mNotificationManager.createNotificationChannel(mChannel);*/

    }
}
