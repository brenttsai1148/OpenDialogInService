package brent.com.intenttest.utility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;


public class NetworkChangeReceiver extends BroadcastReceiver {
	
	private List<NetworkChangeReceiverListener> listeners;
	private static NetworkChangeReceiver networkChangeReceiver;
	
	public interface NetworkChangeReceiverListener{
		void onChange(Object object);
	}
	
	public NetworkChangeReceiver(){
		listeners = new ArrayList<NetworkChangeReceiverListener>();
	}
	
	public void addListener(NetworkChangeReceiverListener networkChangeReceiverListener){
		listeners.add(networkChangeReceiverListener);
	}
	
	public void removeListener(NetworkChangeReceiverListener networkChangeReceiverListener){
		listeners.remove(networkChangeReceiverListener);
	}
	
	public static NetworkChangeReceiver instance(){
		if(networkChangeReceiver==null)
			networkChangeReceiver = new NetworkChangeReceiver();
		return networkChangeReceiver;
	}

    static boolean firstCreate = true;

	@Override
    public void onReceive(final Context context, final Intent intent) {
		NetworkUtil.TYPE_NETWORK status = NetworkUtil.getNetworkType(context);
		for(NetworkChangeReceiverListener listener:listeners){
			listener.onChange(status);
//            if(!firstCreate){
//                L.d();
//
//                if(MainActivity.isUseDialogFragment()){
//                    //F
//                    if(!BrowserActivity.isInstanciated()){
//                        startActivity(BrowserActivity.class,context);				//start activity
//                    }else{
//                        //1.activity in background時 把它帶到front
//                        BrowserActivity.instance().wakeUpActivityWhileIncomingCall();
//                    }
//                }else{
//                    showAlertWindow(context, "11", "22", "33");
//                }
//            }else{
//                firstCreate = false;
//            }
		}
	}

//    private void showAlertWindow(Context context, String hiddenCardNo, String transResult, String transDate) {
//
//        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
//        //保持CPU運轉，屏幕和鍵盤燈有可能是關閉的。
//        final PowerManager.WakeLock wl=pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, this.getClass().getCanonicalName());
////        final PowerManager.WakeLock wl=pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, "wallet");
//        wl.acquire();
//
//        final WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
//
//        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
//        params.flags = WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
//
//        LayoutInflater inflater = LayoutInflater.from(context.getApplicationContext());
//
//        final View popupView = inflater.inflate(R.layout.alert_window, null);
//
//        final TextView tv_cardNo = (TextView)popupView.findViewById(R.id.tv_cardNo);
//        tv_cardNo.setText(hiddenCardNo);
//        tv_cardNo.setVisibility(View.VISIBLE);
//
//        final TextView tv_amount = (TextView)popupView.findViewById(R.id.tv_amount);
//        tv_amount.setText(transResult);
//        tv_amount.setVisibility(View.VISIBLE);
//
//        final TextView tv_date = (TextView)popupView.findViewById(R.id.tv_date);
//        tv_date.setText(transDate);
//        tv_date.setVisibility(View.VISIBLE);
//
//        Button btn_dismiss = (Button)popupView.findViewById(R.id.btn_dismiss);
//        btn_dismiss.setOnClickListener(new Button.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                tv_cardNo.setVisibility(View.VISIBLE);
//                tv_amount.setVisibility(View.VISIBLE);
//                tv_date.setVisibility(View.VISIBLE);
//                wm.removeView(popupView);
//                wl.release();
//
//            }
//        });
//        wm.addView(popupView, params);
//
//    }

//    private void startActivity(Class<?> cls,Context context){
//        L.d();
//        Intent dialogIntent = new Intent(context.getApplicationContext(), cls);
//        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(dialogIntent);
//    }
}
