package com.geeny.alluidatarefresh.UI.BaseActivityOrFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.geeny.alluidatarefresh.R;
import com.geeny.alluidatarefresh.Sync.FragmentSyncManager;
import com.geeny.alluidatarefresh.Sync.OnRefreshDataListener;
import com.geeny.alluidatarefresh.Sync.SyncedObject;
import com.geeny.alluidatarefresh.UI.FragmentNavigator;

import java.util.List;

/**
 * Created by Leslie Huang on 2018/10/13
 */
public abstract class BaseActivity extends AppCompatActivity implements OnFragmentSyncedListener, FragmentNavigator {

    private static final int ACTION_ADD = 1;
    private static final int ACTION_REPLACE = 2;
    private static final int ACTION_ADD_AND_HIDE_OTHERS = 3;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    public void registerListener(OnRefreshDataListener onRefreshUIListener) {
        FragmentSyncManager.getInstance().register(onRefreshUIListener);
    }

    @Override
    public void unRegisterListener(OnRefreshDataListener onRefreshUIListener) {
        FragmentSyncManager.getInstance().deregister(onRefreshUIListener);
    }

    @Override
    public void onSynced(SyncedObject syncedObject) {
        FragmentSyncManager.getInstance().synced(syncedObject);
    }

    private void operateFragmentTransaction(BaseFragment plannerFragment, int action, boolean executePending, boolean addToBackStack) {
        final int containId = getContainId();
        if (containId > 0) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();

            if (plannerFragment.enableAnimation()) {
                transaction.setCustomAnimations(plannerFragment.getEnterAnimation(), plannerFragment.getExitAnimation(), plannerFragment.getPopEnterAnimation(), plannerFragment.getPopExitAnimation());
            }

            if (ACTION_ADD_AND_HIDE_OTHERS == action) {
                List<Fragment> childFragments = mFragmentManager.getFragments();
                for (Fragment childFragment : childFragments) {
                    if (childFragment.isVisible()) {
                        transaction.hide(childFragment);
                    }
                }
                transaction.add(containId, plannerFragment, plannerFragment.getTransactionTag());
            }

            if (ACTION_ADD == action) {
                transaction.add(containId, plannerFragment, plannerFragment.getTransactionTag());
            } else if (ACTION_REPLACE == action) {
                transaction.replace(containId, plannerFragment, plannerFragment.getTransactionTag());
            }

            if (addToBackStack) {
                transaction.addToBackStack(plannerFragment.getTransactionTag());
            }
            transaction.commitAllowingStateLoss();
            if (executePending) {
                try {
                    mFragmentManager.executePendingTransactions();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private int getContainId() {
        return R.id.cl_content;
    }

    @Override
    public void addFragment(BaseFragment baseFragment) {
        operateFragmentTransaction(baseFragment, ACTION_ADD, true, true);
    }

    protected int getFragmentCountToFinish() {
        return 1;
    }

    @Override
    public void onBackPressed() {
        BaseFragment currentFragment = (BaseFragment) mFragmentManager.findFragmentById(getContainId());
        final int count = mFragmentManager.getBackStackEntryCount();
        if (count > getFragmentCountToFinish()) {
            if (currentFragment != null && currentFragment.isPageCanGoBack()) {
                popBackStackImmediate();
                currentFragment = (BaseFragment) mFragmentManager.findFragmentById(getContainId());
                if (currentFragment != null) {
                    currentFragment.onHandleGoBack();
                }
            }
        } else {
            onFinishActivity();
        }
    }

    public boolean popBackStackImmediate() {
        if (mFragmentManager.getBackStackEntryCount() < 1) {
            return false;
        }
        try {
            return mFragmentManager.popBackStackImmediate();
        } catch (IllegalStateException e) {
            return false;
        }
    }

    protected void onFinishActivity() {
        finish();
        overridePendingTransition(0, 0);
    }
}
