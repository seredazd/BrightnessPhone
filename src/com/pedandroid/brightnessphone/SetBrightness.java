package com.pedandroid.brightnessphone;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SetBrightness extends Activity {
	final Handler handler = new Handler();
	final int text = R.id.textBrightness;
	final String text1 = text + " 100%";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Config.log(" ");
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onDestroy() {
		Config.log(" ");
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		Config.log(" ");
		super.onPause();
	}

	@Override
	protected void onRestart() {
		Config.log(" ");
		super.onRestart();
	}

	@Override
	protected void onStart() {
		Config.log(" ");
		super.onStart();
	}

	@Override
	protected void onStop() {
		Config.log(" ");
		super.onStop();
	}

	@Override
	protected void onResume() {
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
//		getWindow().addFlags(WindowManager.LayoutParams.TYPE_BASE_APPLICATION);
//		getWindow().addFlags(WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG);
//		getWindow().addFlags(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//		getWindow().addFlags(WindowManager.LayoutParams.TYPE_WALLPAPER);
//		getWindow().addFlags(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//		 getWindow().addFlags(WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY);
//		 getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
//		Window window = this.getWindow();
//		window.addFlags (WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//		window.addFlags (WindowManager.LayoutParams.FIRST_SUB_WINDOW);
//		window.setBackgroundDrawableResource(R.color.blue);
//		window.setBackgroundDrawableResource(android.R.color.transparent);
		
		int phoneStateTelephony = Config
				.phoneState(getSystemService(Context.TELEPHONY_SERVICE));
		if (phoneStateTelephony == 2) {
			Config.log("Start");
			setContentView(R.layout.activity_brightness_phone);
			SetTimer();
		} else {
			mBridghest((float) -1);
			Config.log("Stop");
			finish();
		}

		super.onResume();

	}

	public void mBridghest(float scBrid) {
		WindowManager.LayoutParams localLayoutParams = getWindow()
				.getAttributes();
		localLayoutParams.screenBrightness = scBrid;
		getWindow().setAttributes(localLayoutParams);
		Config.log("Brightness: " + scBrid);
	}

	public void SetTimer() {
		final TextView Scren_text = (TextView) findViewById(R.id.textBrightness);
		Scren_text.setText(Scren_text.getText() + " 100%");
		Config.log(" ");
		RelativeLayout but_display = (RelativeLayout) findViewById(R.id.display);
		Config.log(" ");
		but_display.setOnLongClickListener(new View.OnLongClickListener() {
			public boolean onLongClick(View v) {
				handler.postDelayed(new Runnable() {
					public void run() {
						getWindow().addFlags(
								WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
						Config.log("FLAG_NOT_TOUCHABLE");
						mBridghest((float) 0.99);
						Config.log(" ");
						Scren_text.setVisibility(View.VISIBLE);
					}
				}, 100);
				return true;
			}
		});
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
		Config.log("FLAG_TURN_SCREEN_ON");
	}
}
