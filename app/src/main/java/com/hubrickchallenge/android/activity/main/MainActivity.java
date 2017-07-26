package com.hubrickchallenge.android.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import com.hubrickchallenge.android.R;
import com.hubrickchallenge.android.activity.BaseActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    public static Intent getStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    public static Intent getStartIntentFromNotification(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    // LIFECYCLE // ************************************************************************************************************************

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    // MENU // *****************************************************************************************************************************

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // CLICK EVENTS // *********************************************************************************************************************

    @OnClick(R.id.fab)
    void onFabClick() {
        snackbar().show(R.string.snackbar_text);
    }

}
