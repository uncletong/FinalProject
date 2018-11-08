package hk.polyu.dc.wifimeasure;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class Wifi {
    private WifiInfo wifiInfo;
    private WifiManager wifiManager;

    public Wifi(Context context) {
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }

    public int getLevel(){
        wifiInfo = wifiManager.getConnectionInfo();
        return wifiInfo.getRssi();
    }


}
