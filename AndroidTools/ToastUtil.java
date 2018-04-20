
package com.qianlong.android.util;


import com.qianlong.android.R;
import com.qianlong.android.base.QLApplication;

import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ToastUtil {
    private Toast mToastInstance;
    private Context mContext;
    private  static ToastUtil sInstance;
    public static ToastUtil getInstance(){
    	if(sInstance==null){
    		sInstance=new ToastUtil(QLApplication.getApp().getApplicationContext());
    	}
    	return sInstance;
    }
    private ToastUtil(Context context) {
        mContext = context;
    }

    public void showToast(String message) {
        makeText(mContext, message, Toast.LENGTH_SHORT,false).show();
    }
    
    public void showToastCenter(String message) {
        makeText(mContext, message, Toast.LENGTH_SHORT,true).show();
    }

    public void showToast(String message, int showTime) {
        makeText(mContext, message, showTime,false).show();
    }

   

    private Toast makeText(Context context, String message, int showTime,boolean isCenter) {
        if (mToastInstance == null) {

            mToastInstance = new Toast(context);

            LayoutInflater inflate = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View v = inflate.inflate(R.layout.toast_content_view, null);
            mToastInstance.setView(v);

        }

        ((TextView) mToastInstance.getView().findViewById(R.id.message))
                .setText(message);

        mToastInstance.setDuration(showTime);
        if(isCenter){
        	mToastInstance.setGravity(Gravity.CENTER, 0, 0);
        }

        return mToastInstance;
    }
    
    

}
