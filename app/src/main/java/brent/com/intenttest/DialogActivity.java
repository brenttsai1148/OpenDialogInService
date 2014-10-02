package brent.com.intenttest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import brent.com.intenttest.utility.L;


public class DialogActivity extends Activity {

    static DialogActivity instance;

    public static final boolean isInstanciated() {
        return instance != null;
    }

    public static final DialogActivity instance() {
        if (instance != null)
            return instance;
        throw new RuntimeException("MainActivity not instantiated yet");
    }

    /**
     *  當activity在onPause情況下 , 使用wakeUpActivityWhileIncomingCall來launcher app
     */
    public void wakeUpActivityWhileIncomingCall() {
        L.d();
        // 在instance存在的情況下
        if (!isInstanciated()) {
            L.e("isInstanciated:" + isInstanciated());
            return;
        }
        Intent intent = new Intent(this, DialogActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // You need this if starting
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Fragment1 dialogFragment = Fragment1.newInstance(
                "這是DialogFragment , 現在沒有網路");
        dialogFragment.show(getFragmentManager(), "dialog");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.browser, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void doPositiveClick() {
        //---perform steps when user clicks on OK---
        Log.d("DialogFragmentExample", "User clicks on OK");
        finish();
    }

    public void doNegativeClick() {
        //---perform steps when user clicks on Cancel---
        Log.d("DialogFragmentExample", "User clicks on Cancel");
        finish();
    }


    public static class Fragment1 extends DialogFragment {

        static Fragment1 newInstance(String title) {
            Fragment1 fragment = new Fragment1();
            Bundle args = new Bundle();
            args.putString("title", title);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            String title = getArguments().getString("title");
            return new AlertDialog.Builder(getActivity())
                    .setIcon(R.drawable.ic_launcher)
                    .setTitle(title)
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {
                                ((DialogActivity)
                                        getActivity()).doPositiveClick();
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {
                                ((DialogActivity)
                                        getActivity()).doNegativeClick();
                                }
                            }).create();
        }

    }
}


