package com.qianlong.android.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import android.content.Context;

public class AssetUtils {
	
	public static String getAssetString(Context ct,String name){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int ch = -1;
		byte[] byteData = null;
		InputStream is = null;
		try {
			is = ct.getAssets().open(name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

        // Read the entire asset into a local byte buffer.  
		try {
			while ((ch = is.read(buf)) != -1) {
				baos.write(buf, 0, ch);//
			}
			byteData = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String data = null;
		try {
			data = new String(byteData, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

}
