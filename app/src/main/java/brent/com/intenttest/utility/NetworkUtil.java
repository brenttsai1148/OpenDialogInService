package brent.com.intenttest.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {
	
	public enum TYPE_NETWORK{
		WIFI,MOBILE,NOT_CONNECTED
	}
     
    public static TYPE_NETWORK getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
 
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_NETWORK.WIFI;
             
            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_NETWORK.MOBILE;
        } 
        return TYPE_NETWORK.NOT_CONNECTED;
    }
    
    public static TYPE_NETWORK getNetworkType(Context context){
    	return getConnectivityStatus(context);
    }
     

}
