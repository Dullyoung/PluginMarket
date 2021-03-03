package com.dullyoung.myplugin;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dullyoung.pluginstanded.PluginActivityInterface;

/*
 * Created by　Dullyoung on 2021/3/2
 */
public class BasePluginActivity extends Activity implements PluginActivityInterface {

    public Activity mActivity;

    @Override
    public void attachPlugin(Activity proxyActivity) {
        mActivity = proxyActivity;
        Log.i(TAG, "attachPlugin: ");
    }

    @Override
    public void setContentView(View view) {
        if (mActivity != null) {
            Log.i("aaaa", "mActivity setContentView: ");
            mActivity.setContentView(view);
        } else {
            Log.i("aaaa", "super setContentView: ");
            super.setContentView(view);
        }
    }


    @Override
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }

    @Override
    public Intent getIntent() {
        if (mActivity != null) {
            return mActivity.getIntent();
        }
        return super.getIntent();
    }

    @Override
    public ClassLoader getClassLoader() {
        return mActivity.getClassLoader();
    }


    @Override
    public void setContentView(int layoutResID) {
        Log.i("aaaa", "setContentView: " + layoutResID);
        mActivity.setContentView(layoutResID);
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return mActivity.getLayoutInflater();
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        return mActivity.getApplicationInfo();
    }

    @Override
    public Window getWindow() {
        return mActivity.getWindow();
    }


    @Override
    public WindowManager getWindowManager() {
        return mActivity.getWindowManager();
    }


    @Override
    public void onPluginCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onPluginCreate: ");

    }

   private String TAG="aaaaa";
    @Override
    public void onPluginStart() {
        Log.i(TAG, "onPluginStart: ");
    }

    @Override
    public void onPluginResume() {
        Log.i(TAG, "onPluginResume: ");
    }

    @Override
    public void onPluginPause() {
        Log.i(TAG, "onPluginPause: ");
    }

    @Override
    public void onPluginStop() {
        Log.i(TAG, "onPluginStop: ");
    }

    @Override
    public void onPluginDestroy() {
        Log.i(TAG, "onPluginDestroy: ");
    }

    @Override
    public void onPluginSaveInstanceState(Bundle outState) {
        Log.i(TAG, "onPluginSaveInstanceState: ");
    }

    @Override
    public boolean onPluginTouchEvent(MotionEvent event) {
         return false;
    }

    @Override
    public void onPluginBackPressed() {
        Log.i(TAG, "onPluginBackPressed: ");
    }

    @Override
    public void startActivity(Intent intent) {
//        ProxyActivity --->className
        Intent m = new Intent();
        m.putExtra("ClassName", intent.getComponent().getClassName());
        mActivity.startActivity(m);
    }

}
