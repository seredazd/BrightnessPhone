package com.pedandroid.brightnessphone;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class BrightnessPhoneReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        intent = new Intent(context, BrightnessPhoneService.class);
        context.startService(intent);
        Config.log("Receiver create");
    }

}
