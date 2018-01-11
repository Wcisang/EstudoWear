package com.gwr.estudowear.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.WearableListenerService;
import com.gwr.estudowear.Steps;

import org.greenrobot.eventbus.EventBus;

public class MyWearableService extends WearableListenerService {

    private static final String COUNT_KEY = "com.example.key.count";

    @Override
    public void onDataChanged(DataEventBuffer dataEventBuffer) {
        Log.i("LOGWILLIAM","MyWearableService.onDataChanged()");
        for(DataEvent dataEvent: dataEventBuffer){
            if(dataEvent.getType() == DataEvent.TYPE_CHANGED){
                Log.i("LOGWILLIAM","DataEvent.TYPE_CHANGED");
                DataMap dataMap = DataMapItem.fromDataItem(dataEvent.getDataItem()).getDataMap();
                String path = dataEvent.getDataItem().getUri().getPath();
                if(path.equals("/count")){
                    int steps = dataMap.getInt(COUNT_KEY);
                    Log.i("LOGWILLIAM","Steps: "+steps);
                    EventBus.getDefault().post(new Steps(steps));
                }
            }
        }
    }
}
