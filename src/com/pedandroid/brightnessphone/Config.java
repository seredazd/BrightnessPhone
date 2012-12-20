package com.pedandroid.brightnessphone;

import android.telephony.TelephonyManager;
import android.util.Log;

public class Config {
	// log
	public final static boolean DEBUG = true;
	// state
	private static TelephonyManager telephonyManager;

	// britghess

	public static void log(String message) {
		if (DEBUG) {
			String fullClassName = Thread.currentThread().getStackTrace()[3]
					.getClassName();
			String className = fullClassName.substring(fullClassName
					.lastIndexOf(".") + 1);
			String methodName = Thread.currentThread().getStackTrace()[3]
					.getMethodName();
			int lineNumber = Thread.currentThread().getStackTrace()[3]
					.getLineNumber();

			Log.i(lineNumber + ": " + className + "." + methodName + " ",
					message);
		}
	}

	static int phoneState(Object getContext) {
		telephonyManager = (TelephonyManager) getContext;
		if (telephonyManager.getCallState() == TelephonyManager.CALL_STATE_IDLE) {
			return 1;
		}
		if (telephonyManager.getCallState() == TelephonyManager.CALL_STATE_RINGING) {
			return 2;
		}
		if (telephonyManager.getCallState() == TelephonyManager.CALL_STATE_OFFHOOK) {
			return 3;
		}
		return 0;
	}
	
}
