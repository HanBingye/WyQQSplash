package com.bing.player.tool;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

public class MetaDataUtil {

    public  static int getMetaDataValue(Context context) {
        int value=0;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            value = info.metaData.getInt("playerType");
            Log.d("getMetaDataValue", "value:"+value);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }
}
