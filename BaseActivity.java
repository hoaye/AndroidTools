package com.houmananger.root.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gaoyuan4122.appmarket.utils.UIUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by GAOYUAN on 2015/7/7.
 * 所有 Activity 的父类, 统一的初始化页面的方法, 获取当前前台 Activity 的方法.
 * 原先的代码是继承 ActionBarActivity, 后此类过时, 所以改为 AppCompatActivity, 效果一样
 */
public class BaseActivity extends AppCompatActivity {
    /**
     * 记录前台 Activity
     */
    public static BaseActivity sForegroundActivity;
    /**
     * 记录所有没有销毁的 Activity
     */
    public LinkedList<BaseActivity> mActivities = new LinkedList<BaseActivity>();

    /**
     * 获取前台 Activity
     */
    public static BaseActivity getForegroundActivity() {
        return sForegroundActivity;
    }

    /**
     * 启动一个 Activity
     *
     * @param intent
     */
    public static void startAnActivity(Intent intent) {
        if (sForegroundActivity != null) {
            sForegroundActivity.startActivity(intent);
        } else {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            UIUtil.getAppContext().startActivity(intent);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initActionBar();
        mActivities.add(this);
    }

    /**
     * 填充布局, 需要子类调用 setContentView, 做控件的初始化和设置事件
     */
    protected void initView() {
    }

    /**
     * 设置 ActionBar, 需要子类重写
     */
    protected void initActionBar() {
    }

    @Override
    protected void onResume() {
        sForegroundActivity = this;
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (sForegroundActivity == this) {
            sForegroundActivity = null;
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mActivities.remove(this);
        super.onDestroy();
    }

    /**
     * 结束所有没有销毁的 Activity, 结束当前进程
     */
    public void killAll() {
        // 复制了一份mActivities 集合
        List<BaseActivity> copy;
        synchronized (mActivities) {
            copy = new LinkedList<BaseActivity>(mActivities);
        }
        for (BaseActivity activity : copy) {
            // 结束当前 Activity, 也可以使用广播
            activity.finish();
        }
        // 杀死当前的进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
