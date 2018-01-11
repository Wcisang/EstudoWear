package com.gwr.estudowear;

import android.content.Intent;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActionActivity extends AppCompatActivity {

    TextView tvAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        tvAction = (TextView) findViewById(R.id.tvAction);
        tvAction.setText(getMessageText(getIntent()));

    }

    public CharSequence getMessageText(Intent intent){
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if(remoteInput != null){
            return remoteInput.getCharSequence("extra_voice_reply");
        }
        return null;
    }
}
