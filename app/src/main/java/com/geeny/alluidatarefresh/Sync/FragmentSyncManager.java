package com.geeny.alluidatarefresh.Sync;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentSyncManager {

    private static FragmentSyncManager sFragmentSyncManager = new FragmentSyncManager();
    private Map<Class, List<OnRefreshDataListener>> mOnUIThreadSyncedMap = new HashMap<>();

    public static FragmentSyncManager getInstance() {
        return sFragmentSyncManager;
    }

    public void register(OnRefreshDataListener onRefreshDataListener) {
        if (onRefreshDataListener != null) {

            Class syncedObjectClass = onRefreshDataListener.getSyncedObjectClass();

            List<OnRefreshDataListener> onRefreshDataListeners = mOnUIThreadSyncedMap.get(syncedObjectClass);
            if (onRefreshDataListeners == null) {
                onRefreshDataListeners = new ArrayList<>();
            }
            onRefreshDataListeners.add(onRefreshDataListener);
            mOnUIThreadSyncedMap.put(syncedObjectClass, onRefreshDataListeners);
        }
    }

    public void deregister(OnRefreshDataListener onRefreshDataListener) {
        mOnUIThreadSyncedMap.get(onRefreshDataListener.getSyncedObjectClass()).remove(onRefreshDataListener);
    }

    /**
     * 传入特定的数据类型，对这个数据类型对应的所有刷新监听全部执行刷新操作
     *
     * @param syncedObject 传进来的要刷新的数据类型。我们把SyncedObject作为所有的数据类型的父类，方便管理。
     */
    public void synced(SyncedObject syncedObject) {
        if (syncedObject != null && mOnUIThreadSyncedMap.containsKey(syncedObject.getClass())) {
            List<OnRefreshDataListener> onRefreshDataListeners = mOnUIThreadSyncedMap.get(syncedObject.getClass());
            for (int index = 0; index < onRefreshDataListeners.size(); index++) {
                OnRefreshDataListener onRefreshUIListener = onRefreshDataListeners.get(index);
                if (onRefreshUIListener != null) {

                    onRefreshUIListener.onRefresh(syncedObject);

                }
            }
        }
    }
}
