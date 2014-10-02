/**
 * NetworkManager.java
 * Copyright (c) 2010-2013 E-Soft TechnoLvoipies Inc.
 * All Rights Reserved.
 * --
 * Attention!
 * The code in this file cannot be modified or accessed in any conditions except:
 * Modify 
 * 		-- import org.esoft.esoftvoice.R : package path
 * --
 * $v 1.07 2013/07/19 $
 * --
 * 
 */
package brent.com.intenttest.utility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


/**
 * 
 * Intercept network state changes and update facetone core through FacetoneManager.
 *
 */
public class NetworkManager extends BroadcastReceiver {

	
	public static String localIpAddress = null;
	private String emtpy = "";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		@SuppressWarnings("deprecation")
		NetworkInfo lNetworkInfo = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
		Log.d("Network info [", lNetworkInfo.toString() + "]");
		Boolean lNoConnectivity = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,false);
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo eventInfo = cm.getActiveNetworkInfo();


//		if (!LinphoneService.isReady()) {
//			L.d("Network broadcast received while Facetone service not ready");
//			return;
//		}
//
//		if (lNoConnectivity || eventInfo == null || ((lNetworkInfo.getState() == NetworkInfo.State.DISCONNECTED))) {
//			L.d("Network Disconnected");
//		} else if (eventInfo.getState() == NetworkInfo.State.CONNECTED){
//			L.d("Network Connected");
//
//			LinphoneCall call = LinphoneManager.getLc().getCurrentCall();
//			String newLocalIP=getLocalIpAddress();
//			L.d("NetworkManager: old IP = " + localIpAddress + " current IP = " + newLocalIP);
//
//			if( localIpAddress != null && newLocalIP != null && !newLocalIP.equals(localIpAddress)){
//				localIpAddress = newLocalIP;
//				if(call != null) reinviteCall(call);
//			}
//
//		} else {
//			 // Other unhandled events
//		}

	}
	
//    private String getLocalIpAddress() {
//    	String LocalIpAddress = emtpy;
//    	try {
//            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
//                NetworkInterface intf = en.nextElement();
//                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
//                    InetAddress inetAddress = enumIpAddr.nextElement();
//                    if (!inetAddress.isLoopbackAddress()) {
//                    	LocalIpAddress = inetAddress.getHostAddress().toString();
//                    }
//                }
//            }
//            L.d("getLocalIpAddress=" + LocalIpAddress);
//    	} catch (Exception e) {
//    		L.d(e.getMessage());
//    	}
//    	return LocalIpAddress;
//    }
//
//    private void reinviteCall(LinphoneCall call){
//    	LinphoneCallParams params = call.getCurrentParamsCopy();
//
//		Lvoip.d("NetworkManager: reinvite Call");
//		params.setAudioBandwidth(40);
//		boolean isSuccess = (LinphoneManager.getLc().updateCall(call, params)==0)?true:false;
//		Lvoip.d("updateCall Success:"+isSuccess);
//    }
}
