package brent.com.intenttest.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.PowerManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import brent.com.intenttest.DialogActivity;
import brent.com.intenttest.MainActivity;
import brent.com.intenttest.R;
import brent.com.intenttest.utility.L;
import brent.com.intenttest.utility.NetworkChangeReceiver;
import brent.com.intenttest.utility.NetworkUtil;

public class InternetStateDetectService extends Service {
    public InternetStateDetectService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        addConnectionChangeReceiver();
        L.d();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.d();
        removeConnectionChangeUnReceiver();
    }

    /**
     * 設定網路狀態監聽器 (when connect state change. such as wifi,3g,no internet)
     */
    private void addConnectionChangeReceiver() {
        IntentFilter netFilter = new IntentFilter();//android.net.conn.CONNECTIVITY_CHANGE
        netFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
//        netFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        NetworkChangeReceiver.instance().addListener(networkChangeReceiverListener);
        registerReceiver(NetworkChangeReceiver.instance(), netFilter);
    }

    /**
     * 移除網路狀態監聽器 (when connect state change. such as wifi,3g,no internet)
     */
    private void removeConnectionChangeUnReceiver() {
        NetworkChangeReceiver.instance().removeListener(networkChangeReceiverListener);
        unregisterReceiver(NetworkChangeReceiver.instance());
    }

    /**
     * 網路狀態監聽器
     */
    private NetworkChangeReceiver.NetworkChangeReceiverListener networkChangeReceiverListener = new NetworkChangeReceiver.NetworkChangeReceiverListener(){

        @Override
        public void onChange(Object object) {
            if(!(object instanceof NetworkUtil.TYPE_NETWORK))return;

            NetworkUtil.TYPE_NETWORK typeNetwork = (NetworkUtil.TYPE_NETWORK)object;
            L.d(typeNetwork.toString());
            switch(typeNetwork){
                case NOT_CONNECTED:
                    //沒有網路的時候
                    openDialog();
                    L.d();
                    break;
                case WIFI:
                    //連上wifi時
                    L.d();
                    break;
                case MOBILE:
                    //連上3G時
                    L.d();
                    break;
            }
        }
    };

    /**
     * 打開dialog
     */
    private void openDialog(){
        if(MainActivity.isUseDialogFragment()){
            //Open dialog with acitivy
            if(!DialogActivity.isInstanciated()){
                startActivity(DialogActivity.class,getBaseContext());				//start activity
            }else{
                //1.activity in background時 把它帶到front
                DialogActivity.instance().wakeUpActivityWhileIncomingCall();
            }
        }else{
            //open dialog with WindowManager
            showAlertWindow(getApplicationContext(),  "這是WindowManager , 現在沒有網路");
        }
    }

    private void startActivity(Class<?> cls,Context context){
        L.d();
        Intent dialogIntent = new Intent(context.getApplicationContext(), cls);
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(dialogIntent);
    }

    /**
     * open dialog with WindowManager
     * @param context
     * @param transDate
     */
    private void showAlertWindow(Context context, String transDate) {

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        //保持CPU運轉，屏幕和鍵盤燈有可能是關閉的。
        final PowerManager.WakeLock wl=pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, this.getClass().getCanonicalName());
//        final PowerManager.WakeLock wl=pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK | PowerManager.ON_AFTER_RELEASE, "wallet");
        wl.acquire();

        final WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();

        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        params.flags = WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;

        LayoutInflater inflater = LayoutInflater.from(context.getApplicationContext());

        final View popupView = inflater.inflate(R.layout.alert_window, null);

        final TextView tv_date = (TextView)popupView.findViewById(R.id.tv_date);
        tv_date.setText(transDate);
        tv_date.setVisibility(View.VISIBLE);

        Button btn_dismiss = (Button)popupView.findViewById(R.id.btn_dismiss);
        btn_dismiss.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                tv_date.setVisibility(View.VISIBLE);
                wm.removeView(popupView);
                wl.release();

            }
        });
        wm.addView(popupView, params);

    }
}
