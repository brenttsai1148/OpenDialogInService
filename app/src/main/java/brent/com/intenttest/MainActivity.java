package brent.com.intenttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import brent.com.intenttest.service.InternetStateDetectService;


public class MainActivity extends Activity {

    private static boolean isUseDialogFragment = true;

    Intent intent;

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup)findViewById(R.id.rgroup);
        radioGroup.setOnCheckedChangeListener(listener);
        //network listener
        intent = new Intent();
        intent.setClass(MainActivity.this, InternetStateDetectService.class);
        startService(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void exit(View view){
        stopService(intent);
        finish();;
    }

    /**
     * is use dialog fragment
     * @return
     */
    public static boolean isUseDialogFragment(){
        return isUseDialogFragment;
    }

    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            // TODO Auto-generated method stub

            int p = group.indexOfChild((RadioButton) findViewById(checkedId));
            int count = group.getChildCount();
            switch (checkedId) {
                case R.id.windows_manager:
                    isUseDialogFragment = false;
                    break;
                case R.id.dialog_fragment:
                    isUseDialogFragment = true;
                    break;
            }
        }
    };

}

