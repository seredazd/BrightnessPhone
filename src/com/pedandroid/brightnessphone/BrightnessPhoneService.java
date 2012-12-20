package com.pedandroid.brightnessphone;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class BrightnessPhoneService extends Service {
	private static final String PHONE_STATE = "android.intent.action.PHONE_STATE";
	private static final BrightnessPhoneReceiver r = new BrightnessPhoneReceiver();
	boolean mStartMode;
	IBinder mBinder;
	private static boolean OFF;     
	private static final int HELLO_ID = 1;
	private static NotificationManager mNotificationManager;
	private static PendingIntent mContentIntent;
	private static final int icon_on = R.drawable.ic_launcher;
	private static final int icon_off = R.drawable.ic_launcher_off;

    @Override
    public void onCreate() {
        Config.log("onCreate");
    }
    public int onStartCommand(Intent intent, int flag, int startId) {
    	Config.log("onStartCommand");
		int phoneStateTelephony = Config
				.phoneState(getSystemService(Context.TELEPHONY_SERVICE));
		if (startId == 1) {
			IntentFilter filter = new IntentFilter(PHONE_STATE);
			registerReceiver(r, filter);
//			Config.SERVICE();
			OFF = false;
			Config.log("Register receiver: " + r);
			Config.log("intent: " + intent);
			Config.log("State Phone: " + phoneStateTelephony + "  startId: "
					+ startId + OFF);
			setNotifi(phoneStateTelephony);
			
		} else if (phoneStateTelephony == 1 && OFF) {
			AppWindow(intent);
			OFF = false;
			Config.log("State Phone: " + phoneStateTelephony + "  startId: "
					+ startId + OFF);
			setNotifi(phoneStateTelephony);
			

		} else if (phoneStateTelephony == 3) {
			AppWindow(intent);
			OFF = true;
			Config.log("State Phone: " + phoneStateTelephony + "  startId: "
					+ startId + OFF);
			setNotifi(phoneStateTelephony);
			

		} else if (phoneStateTelephony == 2) {
			AppWindow(intent);
			OFF = true;
			Config.log("State Phone: " + phoneStateTelephony + "  startId: "
					+ startId + OFF);
			setNotifi(phoneStateTelephony);
			

		} else if (phoneStateTelephony != 2 && startId > 1 && intent != null && OFF != true) {
			Config.log("State Phone: " + phoneStateTelephony + "  startId: "
					+ startId + " receiver: " + r + " HELLO_ID: " + HELLO_ID + OFF);
			Config.log("intent: " + intent);
			stopSelf();

		} else if (intent == null) {
			IntentFilter filter = new IntentFilter(PHONE_STATE);
			registerReceiver(r, filter);
			OFF = false;
//			Config.SERVICE();
			Config.log("Register2 receiver: " + r);
			Config.log("intent2: " + intent);
			Config.log("State Phone2: " + phoneStateTelephony + "  startId: "
					+ startId + OFF);
			setNotifi(phoneStateTelephony);
			
		}
//		return mStartMode ? START_STICKY_COMPATIBILITY : START_STICKY;
//		Tady je zmnìna
		
		return super.onStartCommand(intent,flag,startId);
//		return mStartMode;
    }
    @Override
    public IBinder onBind(Intent intent) {
        // A client is binding to the service with bindService()
    	Config.log("onBind");
        return mBinder;
    }
    @Override
    public void onDestroy() {
    	mNotificationManager.cancel(HELLO_ID);
		Config.log("Cancel Notification: " + HELLO_ID);
 		unregisterReceiver(r);
		Config.log("Unregister receiver: " + r);
		super.onDestroy();
		Config.log("Destroy");
    }
	// phone notification


	private void setNotifi(int phoneStateTelephony) {
		if (phoneStateTelephony != 2) {
			setIcon(icon_off);
		} else {
			setIcon(icon_on);
		}

	}

	private void setIcon(int icon) {
		Intent notificationIntent = new Intent(this, BrightnessPhone.class);
		mContentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
				0);
		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(icon, null, 0);
		notification.flags |= Notification.FLAG_NO_CLEAR;
		notification.setLatestEventInfo(this, getText(R.string.app_name), null,
				mContentIntent);
		mNotificationManager.notify(HELLO_ID, notification);
		Config.log("Notification: " + HELLO_ID);
	}
	public void StartBrightnessIntent(Intent intent, String pack, String clas) {
		Context c = getBaseContext();
		Intent i = new Intent();
		i.setClassName(pack, clas);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		c.startActivity(i);
		return;

	}
	private static String className1;
	private static String className2;
	protected void AppWindow(Intent intent) {
		int phoneStateTelephony = Config
				.phoneState(getSystemService(Context.TELEPHONY_SERVICE));
		if (phoneStateTelephony == 2){
		Context ccc = getApplicationContext();
		ActivityManager am = (ActivityManager) ccc
				.getSystemService(Activity.ACTIVITY_SERVICE);
		int i = 0;
		while (i < 1000) {
			className1 = am.getRunningTasks(1).get(0).baseActivity
					.getPackageName();
			Config.log("AppWindow = " + className1 + " i: " + i);
			className2 = am.getRunningTasks(1).get(0).topActivity
					.getPackageName();
			Config.log("AppWindow = " + className2 + " i: " + i);
			if (className2.equals("com.android.phone")) {
				StartBrightnessIntent(intent, "com.pedandroid.brightnessphone",
						 "com.pedandroid.brightnessphone.SetBrightness");
				Config.log("AppWindow = " + className1 + " return i: " + i);
//				new CountDownTimer(10000, 1000) {
//				     public void onTick(long millisUntilFinished) {
//				    	 Config.log("Start Timer"); 
//				     }
//				     public void onFinish() {
//				    	 Config.log("Create Timer");
//				    	 return;
//				     }
//				  }.start();
				return;
			}
			i++;
		}}else{
			StartBrightnessIntent(intent, "com.pedandroid.brightnessphone",
					 "com.pedandroid.brightnessphone.StopBrightness");
			Config.log("AppWindow = " + className1 + " return: ");
			return;
		}
		
	}
}
