package com.geeny.alluidatarefresh.UI.BaseActivityOrFragment;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.geeny.alluidatarefresh.Sync.OnRefreshDataListener;
import com.geeny.alluidatarefresh.Sync.SyncedObject;
import com.geeny.alluidatarefresh.UI.FragmentNavigator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leslie Huang on 2018/10/13
 */
public abstract class BaseFragment extends Fragment {

    private OnFragmentSyncedListener mOnFragmentSyncedListener;

    private List<OnRefreshDataListener> onRefreshDataListeners = new ArrayList<>();
    private FragmentNavigator mFragmentNavigator;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        mOnFragmentSyncedListener = activity instanceof OnFragmentSyncedListener ? (OnFragmentSyncedListener) activity : null;
        mFragmentNavigator = activity instanceof FragmentNavigator ? (FragmentNavigator) activity : null;
    }

    protected void registerRefreshDataListener(OnRefreshDataListener onRefreshDataListener) {
        if (mOnFragmentSyncedListener != null) {
            mOnFragmentSyncedListener.registerListener(onRefreshDataListener);
            onRefreshDataListeners.add(onRefreshDataListener);
        }
    }

    protected void syncedRefreshDataListener(SyncedObject syncedObject) {
        if (mOnFragmentSyncedListener != null) {
            mOnFragmentSyncedListener.onSynced(syncedObject);
        }
    }

    private void unRegisterRefreshDataListeners(List<OnRefreshDataListener> onRefreshDataListeners) {
        if (mOnFragmentSyncedListener != null) {
            for (int index = 0; index < onRefreshDataListeners.size(); index++) {
                if (onRefreshDataListeners.get(index) != null) {
                    mOnFragmentSyncedListener.unRegisterListener(onRefreshDataListeners.get(index));
                }
            }
        }
    }

    protected void unRegisterALLRefreshDataListeners() {
        unRegisterRefreshDataListeners(onRefreshDataListeners);
    }

    @Override
    public final void onDestroy() {
        unRegisterALLRefreshDataListeners();
        onCustomDestroy();
        super.onDestroy();
    }

    protected abstract void onCustomDestroy();

    public void backToPreviousPage() {
        Activity activity = getActivity();
        if (activity != null) {
            activity.onBackPressed();
        }
    }

    public void navigateFragmentWithAdd(BaseFragment fragment) {
        if (mFragmentNavigator != null) {
            mFragmentNavigator.addFragment(fragment);
        }
    }

    public abstract String getTransactionTag();

    public abstract boolean enableAnimation();

    public boolean isPageCanGoBack() {
        return true;
    }

    public abstract int getEnterAnimation();

    public abstract int getExitAnimation();

    public abstract int getPopEnterAnimation();

    public abstract int getPopExitAnimation();

    public void onHandleGoBack() {

    }
}
