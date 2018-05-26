package com.tiny.rougeappmanager;

import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;

public class AppInfo {
    private PackageInfo packageInfo;
    private String appName;
    private String packageName;
    private String versionName;
    private String sysOrData; //是否系统应用
    private String permissions[];
    private int versionCode;
    private Drawable icon;

    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }



    public String getSysOrData() {
        return sysOrData;
    }

    public void setSysOrData(String sysOrData) {
        this.sysOrData = sysOrData;
    }


    public AppInfo(PackageInfo packageInfo){
        setPackageInfo(packageInfo);
    }

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }

    public void setPackageInfo(PackageInfo packageInfo) {
        this.packageInfo = packageInfo;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }




}
