package com.pedandroid.brightnessphone;

import android.app.Activity;
import android.content.Intent;

public class BrightnessPhone extends Activity {

	@Override
	protected void onResume() {
		super.onResume();
//		Intent intent = new Intent(this, BrightnessPhoneService.class);
		startService(new Intent(this, BrightnessPhoneService.class));
		finish();
		Config.log("APP Start");

	}
}
