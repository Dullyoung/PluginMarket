package com.dullyoung.pluginmarket;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/*
 * Created by　Dullyoung on 2021/3/3
 */
public class HookManager {
    private static final HookManager ourInstance = new HookManager();
    private Resources resources;
    private DexClassLoader loader;
    public PackageInfo packageInfo;

    public static HookManager getInstance() {
        return ourInstance;
    }

    private HookManager() {
    }


    //用来加载插件
    public void loadPlugin(Activity activity) {
        // 假如这里是从网络获取的插件 我们直接从sd卡获取 然后读取到我们的cache目录
        String pluginName = "plugin.apk";
        File filesDir = activity.getExternalCacheDir();
        String filePath = new File(filesDir, pluginName).getAbsolutePath();
        Log.i("aaaa", "loadPlugin: " + filePath);


        FileInputStream is = null;
        FileOutputStream os = null;
        //读取的目录
        try {
            is = new FileInputStream(new File(activity.getExternalCacheDir(), pluginName));
            //要输入的目录
            os = new FileOutputStream(filesDir + "qqq.apk");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            File f = new File(filePath);
            if (f.exists()) {
                Toast.makeText(activity, "dex overwrite", Toast.LENGTH_SHORT).show();
            }
            loadPathToPlugin(activity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public void startProxy(Activity activity) {
        Intent intent = new Intent(activity, ProxyActivity.class);//这里就是一个占坑的activity
        //这里是拿到我们加载的插件的第一个activity的全类名
        intent.putExtra("ClassName", packageInfo.activities[0].name);
        activity.startActivity(intent);
    }


    private void loadPathToPlugin(Activity activity) {
        File filesDir = activity.getExternalCacheDir();
        String name = "plugin.apk";
        String path = new File(filesDir, name).getAbsolutePath();
        //然后我们开始加载我们的apk 使用DexClassLoader
        File dexOutDir = activity.getExternalCacheDir();
        loader = new DexClassLoader(path, dexOutDir.getAbsolutePath(), null, activity.getClassLoader());

        //通过PackAgemanager 来获取插件的第一个activity是哪一个
        PackageManager packageManager = activity.getPackageManager();
        packageInfo = packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);


        //然后开始加载我们的资源 肯定要使用Resource 但是它是AssetManager创建出来的 就是AssertManager 有一个addAssertPath 这个方法 但是私有的 所有使用反射
        Class<?> assetManagerClass = AssetManager.class;
        try {
            AssetManager assertManagerObj = (AssetManager) assetManagerClass.newInstance();
            Method addAssetPathMethod = assetManagerClass.getMethod("addAssetPath", String.class);
            addAssetPathMethod.setAccessible(true);
            addAssetPathMethod.invoke(assertManagerObj, path);
            //在创建一个Resource
            resources = new Resources(assertManagerObj, activity.getResources().getDisplayMetrics(), activity.getResources().getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //对外提供插件的classLoader
    public ClassLoader getClassLoader() {
        return loader;
    }

    //插件中的Resource
    public Resources getResource() {
        return resources;
    }
}
