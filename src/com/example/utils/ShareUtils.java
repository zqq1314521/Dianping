package com.example.utils;

import android.content.Context;
import android.content.SharedPreferences.Editor;

public class ShareUtils {

	private static final String FILE_NAME = "dianping";
	private static final String MODE_NAME = "welcome";

	public static boolean getSharePreferences(Context context) {
		return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
				.getBoolean(MODE_NAME, false);
	}

	public static void setSharePreferences(Context context, boolean value) {
		Editor editor = context.getSharedPreferences(FILE_NAME,
				Context.MODE_APPEND).edit();
		
		editor.putBoolean(MODE_NAME, value);
		editor.commit();

	}

}