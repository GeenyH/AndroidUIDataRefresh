package com.geeny.alluidatarefresh.Sync;

import java.lang.reflect.ParameterizedType;

/**
 * 刷新回调类
 *
 * @param <T> 这里的泛型就是用来标识数据类型的，为SyncedObject的子类
 *            比如学生数据类型我们就指定T为SyncedObject.Student，教师数据类型就指定T为SyncedObject.Teacher
 */
public abstract class OnRefreshDataListener<T extends SyncedObject> {

    /**
     * 返回泛型的类型
     * 假如这一个监听是用来刷新学生信息的，按上面的注释，这个方法会返回SyncedObject.Student的Class类型值
     */
    public final Class<?> getSyncedObjectClass() {
        return (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void onRefresh(T t) {
    }
}

