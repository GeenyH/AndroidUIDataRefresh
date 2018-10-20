package com.geeny.alluidatarefresh.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.geeny.alluidatarefresh.R;
import com.geeny.alluidatarefresh.UI.BaseActivityOrFragment.BaseActivity;

public class MainActivity extends BaseActivity {

    private FragmentManager mFragmentManager;

    private Fragment mFragmentMain;

    private FragmentTransaction mTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFragment(new FragmentMain());
    }
}
