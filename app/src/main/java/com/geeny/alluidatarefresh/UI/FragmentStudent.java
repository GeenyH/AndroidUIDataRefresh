package com.geeny.alluidatarefresh.UI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geeny.alluidatarefresh.R;
import com.geeny.alluidatarefresh.StudentRepository;
import com.geeny.alluidatarefresh.Sync.SyncedObject;
import com.geeny.alluidatarefresh.UI.BaseActivityOrFragment.BaseFragment;

/**
 * Created by Leslie Huang on 2018/10/16
 */
public class FragmentStudent extends BaseFragment {


    private TextView mTvStudentName;
    private TextView mTvStudentNum;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mTvStudentName = view.findViewById(R.id.tv_student_name);
        mTvStudentNum = view.findViewById(R.id.tv_student_num);

        showStudentData();

        view.findViewById(R.id.btn_back_to_home).setOnClickListener(v -> {
            backToPreviousPage();
        });

        view.findViewById(R.id.btn_update_student).setOnClickListener(v -> {
            StudentRepository.getInstance().getStudent().setNum("1234567890");
            showStudentData();
            syncedRefreshDataListener(new SyncedObject.Student());
        });
    }

    private void showStudentData() {
        mTvStudentName.setText(StudentRepository.getInstance().getStudent().getName());
        mTvStudentNum.setText(StudentRepository.getInstance().getStudent().getNum());
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
}
