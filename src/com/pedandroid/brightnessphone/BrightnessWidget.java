package com.pedandroid.brightnessphone;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

public class BrightnessWidget extends FrameLayout {

	public BrightnessWidget(Context context) {
		super(context);
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.activity_brightness_phone, this, true);
	}
}
