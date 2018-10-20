package com.geeny.alluidatarefresh.UI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geeny.alluidatarefresh.R;
import com.geeny.alluidatarefresh.Sync.SyncedObject;
import com.geeny.alluidatarefresh.TeacherRepository;
import com.geeny.alluidatarefresh.UI.BaseActivityOrFragment.BaseFragment;

/**
 * Created by Leslie Huang on 2018/10/16
 */
public class FragmentTeacher extends BaseFragment {

    private TextView mTvTeacherName;
    private TextView mTvTeacherAge;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_teacher, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTvTeacherName = view.findViewById(R.id.tv_teacher_name);
        mTvTeacherAge = view.findViewById(R.id.tv_teacher_age);

        showTeacherData();

        view.findViewById(R.id.btn_back_to_home).setOnClickListener(v -> {
            backToPreviousPage();
        });

        view.findViewById(R.id.btn_update_teacher).setOnClickListener(v -> {
            TeacherRepository.getInstance().getTeacher().setName("Anson Ngan");
            showTeacherData();
            syncedRefreshDataListener(new SyncedObject.Teacher());
        });
    }

    @Override
    protected void onCustomDestroy() {

    }

    @Override
    public String getTransactionTag() {
        return null;
    }

    @Override
    public boolean enableAnimation() {
        return false;
    }

    @Override
    public int getEnterAnimation() {
        return 0;
    }

    @Override
    public int getExitAnimation() {
        return 0;
    }

    @Override
    public int getPopEnterAnimation() {
        return 0;
    }

    @Override
    public int getPopExitAnimation() {
        return 0;
    }

    private void showTeacherData() {
        mTvTeacherName.setText(TeacherRepository.getInstance().getTeacher().getName());
        mTvTeacherAge.setText(String.valueOf(TeacherRepository.getInstance().getTeacher().getAge()));
    }
}
