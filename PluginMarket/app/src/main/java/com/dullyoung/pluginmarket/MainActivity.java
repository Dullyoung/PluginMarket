package com.dullyoung.pluginmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private Context getContext() {
        return this;
    }

    //      * 加载插件
//     */
    public void loadPlugin(View v) {
        PermissionHelper permissionHelper = new PermissionHelper();
        permissionHelper.checkAndRequestPermission(this, new PermissionHelper.OnRequestPermissionsCallback() {
            @Override
            public void onRequestPermissionSuccess() {
                HookManager.getInstance().loadPlugin(MainActivity.this);
                Toast.makeText(getContext(), "加载完成", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRequestPermissionError() {

            }
        });
    }

    /**
     * 跳转插件
     */
    public void startProxy(View v) {
        HookManager.getInstance().startProxy(this);
    }
}