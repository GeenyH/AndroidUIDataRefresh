package com.geeny.alluidatarefresh.UI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.geeny.alluidatarefresh.R;
import com.geeny.alluidatarefresh.StudentRepository;
import com.geeny.alluidatarefresh.Sync.OnRefreshDataListener;
import com.geeny.alluidatarefresh.Sync.SyncedObject;
import com.geeny.alluidatarefresh.TeacherRepository;
import com.geeny.alluidatarefresh.UI.BaseActivityOrFragment.BaseFragment;

/**
 * Created by Leslie Huang on 2018/10/16
 */
public class FragmentMain extends BaseFragment {

    private Button mBtnResetData;
    private Button mBtnGoToStudentInfo;
    private Button mBtnGoToTeacherInfo;

    private TextView mTvStudentName;
    private TextView mTvStudentNum;
    private TextView mTvTeacherName;
    private TextView mTvTeacherAge;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBtnResetData = view.findViewById(R.id.btn_reset);
        mBtnGoToStudentInfo = view.findViewById(R.id.btn_go_to_student_info);
        mBtnGoToTeacherInfo = view.findViewById(R.id.btn_go_to_teacher_info);
        mTvStudentName = view.findViewById(R.id.tv_student_name);
        mTvStudentNum = view.findViewById(R.id.tv_student_num);
        mTvTeacherName = view.findViewById(R.id.tv_teacher_name);
        mTvTeacherAge = view.findViewById(R.id.tv_teacher_age);

        initData();

        mBtnResetData.setOnClickListener(v -> {
            StudentRepository.getInstance().getStudent().setNum("2013314229");
            TeacherRepository.getInstance().getTeacher().setName("Fred Chen");
            syncedRefreshDataListener(new SyncedObject.Student());
            syncedRefreshDataListener(new SyncedObject.Teacher());
        });

        mBtnGoToStudentInfo.setOnClickListener(v -> {
            navigateFragmentWithAdd(new FragmentStudent());
        });

        mBtnGoToTeacherInfo.setOnClickListener(v -> {
            navigateFragmentWithAdd(new FragmentTeacher());
        });

        registerRefreshDataListener(new OnRefreshDataListener<SyncedObject.Student>() {
            @Override
            public void onRefresh(SyncedObject.Student o) {
                showStudentData();
            }
        });

        registerRefreshDataListener(new OnRefreshDataListener<SyncedObject.Teacher>() {
            @Override
            public void onRefresh(SyncedObject.Teacher teacher) {
                showTeacherData();
            }
        });
    }

    private void initData() {
        showStudentData();
        showTeacherData();
    }

    private void showStudentData() {
        mTvStudentName.setText(StudentRepository.getInstance().getStudent().getName());
        mTvStudentNum.setText(StudentRepository.getInstance().getStudent().getNum());
    }

    private void showTeacherData() {
        mTvTeacherName.setText(TeacherRepository.getInstance().getTeacher().getName());
        mTvTeacherAge.setText(String.valueOf(TeacherRepository.getInstance().getTeacher().getAge()));
    }
}
