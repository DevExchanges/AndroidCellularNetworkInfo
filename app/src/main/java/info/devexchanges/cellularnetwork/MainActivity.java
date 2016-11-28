package info.devexchanges.cellularnetwork;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final int NETWORK_TYPE_EHRPD = 14; // Level 11
    public static final int NETWORK_TYPE_EVDO_B = 12; // Level 9
    public static final int NETWORK_TYPE_HSPAP = 15; // Level 13
    public static final int NETWORK_TYPE_IDEN = 11; // Level 8
    public static final int NETWORK_TYPE_LTE = 13; // Level 11

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Locating the TextView and set data (network feature)
        TextView tvNetworkInfo = (TextView) findViewById(R.id.network);
        String type = isConected(this);
        tvNetworkInfo.setText(type);

    }

    /**
     * Check if there is a connectivity
     *
     * @param context
     * @return
     */
    public static String isConected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();

        if ((info != null && info.isConnected())) {
            return isConnectionFast(info.getType(),
                    info.getSubtype());
        } else
            return "No NetWork Access";

    }

    /**
     * Check speed of the connection
     */
    public static String isConnectionFast(int type, int subType) {
        if (type == ConnectivityManager.TYPE_WIFI) {
            return "CONNECTED VIA WIFI";
        } else if (type == ConnectivityManager.TYPE_MOBILE) {
            switch (subType) {
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                    return "NETWORK TYPE 1xRTT - Speed: ~50 - 100 Kbps";
                case TelephonyManager.NETWORK_TYPE_CDMA:
                    return "NETWORK TYPE CDMA (3G) Speed: ~14-64 Kbps";
                case TelephonyManager.NETWORK_TYPE_EDGE:
                    return "NETWORK TYPE EDGE (2.75G) Speed: 100-120 Kbps";
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    return "NETWORK TYPE EVDO_0 Speed: ~400-1000 Kbps";
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    return "NETWORK TYPE EVDO_A Speed: ~600-1400 Kbps";
                case TelephonyManager.NETWORK_TYPE_GPRS:
                    return "NETWORK TYPE GPRS (2.5G) Speed: ~100 Kbps";
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                    return "NETWORK TYPE HSDPA (4G) Speed: 2-14 Mbps";
                case TelephonyManager.NETWORK_TYPE_HSPA:
                    return "NETWORK TYPE HSPA (4G) Speed: 0.7-1.7 Mbps";
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                    return "NETWORK TYPE HSUPA (3G) Speed: 1-23 Mbps";
                case TelephonyManager.NETWORK_TYPE_UMTS:
                    return "NETWORK TYPE UMTS (3G) Speed: 0.4-7 Mbps";

                // API level 7 not supported this type
                case NETWORK_TYPE_EHRPD:
                    return "NETWORK TYPE EHRPD Speed: ~1-2 Mbps";
                case NETWORK_TYPE_EVDO_B:
                    return "NETWORK_TYPE_EVDO_B Speed: ~5 Mbps";
                case NETWORK_TYPE_HSPAP:
                    return "NETWORK TYPE HSPA+ (4G) Speed: 10-20 Mbps";
                case NETWORK_TYPE_IDEN:
                    return "NETWORK TYPE IDEN Speed: ~25 Kbps";
                case NETWORK_TYPE_LTE:
                    return "NETWORK TYPE LTE (4G) Speed: 10+ Mbps";

                // Unknown type
                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                    return "NETWORK TYPE UNKNOWN";
                default:
                    return "";
            }
        } else {
            return "";
        }
    }
}
