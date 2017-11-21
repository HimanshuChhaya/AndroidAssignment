package com.example.himanshu.myandroidassignment;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String _welcomeMessage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("SDPD Assignment");

        _welcomeMessage = "\n\nWelcome!";

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.linearLayoutMain);

        TextView view = (TextView) findViewById(R.id.textViewTop);
        view.setText(_welcomeMessage);

        TextView viewMiddle = (TextView) findViewById(R.id.textViewMiddle);
        viewMiddle.setText("\nName: Himanshu Nanajibhai Chhaya \nID: 2016HT13336");

        Button btn = (Button) findViewById(R.id.startButton);

        //Center the button
        int buttonPading = (layout.getWidth() - btn.getWidth())/2;
        btn.layout(buttonPading,0,0,0);

        btn.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {

                                       Intent intent;
                                       intent = new Intent(getApplicationContext(),ListActivityMain.class);
                                       startActivity(intent);
                                       //Toast.makeText(getApplicationContext(),"Hi",Toast.LENGTH_LONG).show();
                                   }
                               }
        );

    }
}
