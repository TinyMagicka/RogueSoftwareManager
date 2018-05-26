package com.tiny.rougeappmanager;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<AppInfo> appList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getInstalledApps();
        initUI();
    }

    private void initUI(){
        listView = findViewById(R.id.listView);
        listView.setAdapter(new BaseAdapter(){

            @Override
            public int getCount() {
                return appList.size();
            }

            @Override
            public Object getItem(int position) {
                return appList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                //第一个参数表示位置，第二个参数表示缓存布局，第三个表示绑定的view对象
                class ViewHolder{
                    ImageView app_icon;
                    TextView app_name;
                    TextView app_package;
                }
                //实例ViewHolder，当程序第一次运行，保存获取到的控件，提高效率
                ViewHolder viewHolder;
                //convertView为空代表布局没有被加载过，即getView方法没有被调用过，需要创建
                if(convertView==null){
                    convertView = View.inflate(getApplicationContext(), R.layout.applist_item, null);
                    viewHolder=new ViewHolder();
                    //获取控件,只需要调用一遍，调用过后保存在ViewHolder中
                    viewHolder.app_icon     = convertView.findViewById(R.id.app_icon);
                    viewHolder.app_name     = convertView.findViewById(R.id.app_name);
                    viewHolder.app_package  = convertView.findViewById(R.id.app_package);
                    convertView.setTag(viewHolder);
                }else
                    //convertView不为空代表布局被加载过，只需要将convertView的值取出即可
                    viewHolder=(ViewHolder) convertView.getTag();
                AppInfo app = (AppInfo) getItem(position);
                viewHolder.app_icon.setImageDrawable(app.getIcon());
                viewHolder.app_name.setText(app.getAppName());
                viewHolder.app_package.setText(app.getPackageName());
                return convertView;
            }
        });
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AppInfo app = appList[position];
                Intent intent = new Intent(MainActivity.this,AppDetial.class);
                intent.putExtra("package", app.getPackageName());
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
            }
        });
    }

    private void getInstalledApps() {
        appList= new ArrayList<AppInfo>();
        // 获取已经安装的所有应用, PackageInfo　系统类，包含应用信息
        List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
        for (PackageInfo packageInfo : packages) {
            AppInfo appInfo = new AppInfo(packageInfo);
            if((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) //非系统应用
                appInfo.setSysOrData("data");
            else  // 系统应用
                appInfo.setSysOrData("sys");
            appInfo.setAppName(packageInfo.applicationInfo.loadLabel(getPackageManager()).toString());//获取应用名称
            appInfo.setPackageName(packageInfo.packageName); //获取应用包名，可用于卸载和启动应用
            appInfo.setVersionName(packageInfo.versionName);//获取应用版本名
            appInfo.setVersionCode(packageInfo.versionCode);//获取应用版本号
            appInfo.setIcon(packageInfo.applicationInfo.loadIcon(getPackageManager()));//获取应用图标
            appInfo.setPermissions(packageInfo.requestedPermissions);
            appList.add(appInfo);

        }
    }
}
