package com.pedandroid.brightnessphone;

import android.app.Activity;
import android.view.WindowManager;

public class StopBrightness extends Activity{
	@Override
	public void onStart() {
		mBridghest((float)-1);
		Config.log("Brightness Stop");
		finish();
		super.onStart();
	}
	public void mBridghest(float scBrid){
		 WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
		 localLayoutParams.screenBrightness = scBrid;
	     getWindow().setAttributes(localLayoutParams);
	     Config.log("Brightness: " + scBrid);
	}

}
