package com.geeny.alluidatarefresh.UI.BaseActivityOrFragment;

import com.geeny.alluidatarefresh.Sync.OnRefreshDataListener;
import com.geeny.alluidatarefresh.Sync.SyncedObject;

/**
 * Created by simonlee on 10/29/2015.
 */
public interface OnFragmentSyncedListener {
    void registerListener(OnRefreshDataListener onRefreshUIListener);

    void unRegisterListener(OnRefreshDataListener onRefreshUIListener);

    void onSynced(SyncedObject syncedObject);
}
